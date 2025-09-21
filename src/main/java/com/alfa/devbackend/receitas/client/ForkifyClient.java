// PROBLEMA 02 — cliente HTTP simples para consultar a Forkify
package com.alfa.devbackend.receitas.client;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ForkifyClient {

  private final RestTemplate rest = new RestTemplate();

  public Map<?, ?> search(String dish) {
    try {
      String url = "https://forkify-api.herokuapp.com/api/search?q=" + dish;
      // Recebe o JSON como Map (simples para didática)
      return rest.getForObject(url, Map.class);
    } catch (RestClientException e) {
      // Em caso de falha externa, lançamos uma exceção clara
      throw new RuntimeException("Falha ao consultar Forkify: " + e.getMessage(), e);
    }
  }
}
