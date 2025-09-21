package com.alfa.devbackend.receitas.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ForkifyClient {

  // === requestFactory com timeouts (forma não deprecada) ===
  private static SimpleClientHttpRequestFactory requestFactory(Duration connect, Duration read) {
    SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
    f.setConnectTimeout((int) connect.toMillis());
    f.setReadTimeout((int) read.toMillis());
    return f;
  }

  private final RestTemplate restTemplate = new RestTemplateBuilder()
      .requestFactory(() -> requestFactory(Duration.ofSeconds(5), Duration.ofSeconds(10)))
      .build();

  // v1 (pode falhar às vezes)
  private static final String URL_V1 =
      "https://forkify-api.herokuapp.com/api/search?q={dish}";
  // v2 (mais estável; JSON diferente)
  private static final String URL_V2 =
      "https://forkify-api.herokuapp.com/api/v2/recipes?search={dish}";

  public Map<String, Object> search(String dish) {
    try {
      // 1) tenta V2 primeiro (mais estável)
      ResponseEntity<Map<String, Object>> respV2 = restTemplate.exchange(
          URL_V2, HttpMethod.GET, null,
          new ParameterizedTypeReference<Map<String, Object>>() {}, dish);
      Map<String, Object> v2 = respV2.getBody();
      if (v2 != null) return v2;

      // 2) fallback para V1 se vier nulo (raro)
      ResponseEntity<Map<String, Object>> respV1 = restTemplate.exchange(
          URL_V1, HttpMethod.GET, null,
          new ParameterizedTypeReference<Map<String, Object>>() {}, dish);
      Map<String, Object> v1 = respV1.getBody();
      return v1 != null ? v1 : Map.of();

    } catch (HttpStatusCodeException e) {
      // 3) se a API externa der 4xx/5xx, tenta mock local
      Map<String, Object> fb = loadFallback(dish);
      if (fb != null) return fb;

      throw new ResponseStatusException(
          e.getStatusCode(),
          "Erro ao consultar a Forkify: " + e.getResponseBodyAsString()
      );

    } catch (ResourceAccessException e) {
      // timeout/offline → tenta mock
      Map<String, Object> fb = loadFallback(dish);
      if (fb != null) return fb;

      throw new ResponseStatusException(
          HttpStatus.SERVICE_UNAVAILABLE,
          "API externa indisponível e sem fallback local."
      );

    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR,
          "Falha inesperada ao consultar receitas", e
      );
    }
  }

  // Lê src/main/resources/mock/recipes-{dish}.json (se existir)
  private Map<String, Object> loadFallback(String dish) {
    String resource = "/mock/recipes-" + dish.toLowerCase() + ".json";
    try (InputStream is = getClass().getResourceAsStream(resource)) {
      if (is == null) return null;
      ObjectMapper om = new ObjectMapper();
      return om.readValue(is, new TypeReference<Map<String, Object>>() {});
    } catch (Exception ignore) {
      return null;
    }
  }
}