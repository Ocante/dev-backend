// PROBLEMA 02 — regra de negócio da funcionalidade de receitas.
// Função: receber o prato (dish), chamar a API Forkify e devolver um DTO simples.
// Local do arquivo: src/main/java/com/alfa/devbackend/receitas/service/RecipeService.java
package com.alfa.devbackend.receitas.service;

import com.alfa.devbackend.receitas.client.ForkifyClient;
import com.alfa.devbackend.receitas.dto.RecipeSearchDTO;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service // Torna esta classe um "serviço" do Spring (gerenciado por injeção de dependência)
public class RecipeService {

  private final ForkifyClient client;

  // Construtor usado pelo Spring para injetar o ForkifyClient automaticamente
  public RecipeService(ForkifyClient client) {
    this.client = client;
  }

  /**
   * Busca receitas para o prato informado.
   * Passos:
   * 1) Chama a Forkify (client.search).
   * 2) Lê "count" de forma tolerante ao tipo (Number ou String).
   * 3) Lê "recipes" como lista de mapas (json bruto), com fallback para lista vazia.
   * 4) Retorna um DTO imutável (record) para a controller.
   */
  @SuppressWarnings("unchecked") // Suprime o aviso de cast do Map deserializado
  public RecipeSearchDTO search(String dish) {
    // Chamada HTTP externa → retorna um Map desserializado do JSON de resposta
    Map<?, ?> raw = client.search(dish);

    // Converte para Map<String, Object> para facilitar os gets com chaves String
    Map<String, Object> json = (Map<String, Object>) raw;

    // -------- tratar "count" de forma segura (pode vir Number ou String) --------
    Object countObj = json.getOrDefault("count", 0);
    int count = (countObj instanceof Number)
        ? ((Number) countObj).intValue()
        : Integer.parseInt(String.valueOf(countObj));

    // -------- obter a lista "recipes" com fallback em lista vazia --------
    List<Map<String, Object>> recipes =
        (List<Map<String, Object>>) json.getOrDefault("recipes", List.of());

    // Devolve um DTO enxuto para a camada web
    return new RecipeSearchDTO(count, recipes);
  }
}
