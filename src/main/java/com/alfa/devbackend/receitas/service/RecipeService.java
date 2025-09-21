// PROBLEMA 02 — regra de negócio da funcionalidade de receitas.
// Função: receber o prato (dish), chamar a API Forkify e devolver um DTO simples (count + recipes).
// Local: src/main/java/com/alfa/devbackend/receitas/service/RecipeService.java

package com.alfa.devbackend.receitas.service;

import com.alfa.devbackend.receitas.client.ForkifyClient;
import com.alfa.devbackend.receitas.dto.RecipeSearchDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service // Torna esta classe um "serviço" gerenciado pelo Spring (injeção de dependência)
public class RecipeService {

  private final ForkifyClient client;

  // Construtor usado pelo Spring para injetar o ForkifyClient automaticamente
  public RecipeService(ForkifyClient client) {
    this.client = client;
  }

  /**
   * Método principal que busca receitas.
   * Passos:
   * 1) Chama a API Forkify via ForkifyClient (v2 → fallback para v1).
   * 2) Normaliza a resposta (independente se veio no formato v1 ou v2).
   * 3) Retorna um DTO imutável (record) contendo "count" e "recipes".
   */
  public RecipeSearchDTO search(String dish) {
    // 1) Faz a chamada HTTP e obtém um Map com o JSON desserializado
    Map<String, Object> raw = client.search(dish);

    // 2) Extrai de forma tolerante o "count" e a lista de "recipes"
    int count = extractCount(raw);
    List<Map<String, Object>> recipes = extractRecipes(raw);

    // 3) Se não veio count explícito, usa o tamanho da lista como fallback
    if (count <= 0) count = recipes.size();

    // 4) Retorna o DTO que será usado pelo controller
    return new RecipeSearchDTO(count, recipes);
  }

  /**
   * Extrai a contagem de receitas da resposta.
   * Compatível tanto com v2 (results) quanto v1 (count).
   */
  @SuppressWarnings("unchecked")
  private int extractCount(Map<String, Object> raw) {
    if (raw == null) return 0;

    // ---- Forkify v2 ----  { "results": 30, "data": { "recipes": [...] } }
    Object results = raw.get("results");
    if (results instanceof Number n) return n.intValue();

    // ---- Forkify v1 ----  { "count": 30, "recipes": [...] }
    Object count = raw.get("count");
    if (count instanceof Number n2) return n2.intValue();

    // Pode vir como String em mocks ou APIs alternativas
    if (count instanceof String s) {
      try {
        return Integer.parseInt(s);
      } catch (NumberFormatException ignored) {
        // ignora e retorna 0 lá no final
      }
    }

    return 0;
  }

  /**
   * Extrai a lista de receitas da resposta.
   * Lida com os dois formatos de retorno da Forkify.
   */
  @SuppressWarnings("unchecked")
  private List<Map<String, Object>> extractRecipes(Map<String, Object> raw) {
    if (raw == null) return Collections.emptyList();

    // ---- Forkify v2 ----  { "data": { "recipes": [ {..}, .. ] } }
    Object dataObj = raw.get("data");
    if (dataObj instanceof Map<?, ?> data) {
      Object recipesObj = data.get("recipes");
      if (recipesObj instanceof List<?> list) {
        // Cast seguro para List<Map<String, Object>>
        return (List<Map<String, Object>>) (List<?>) new ArrayList<>(list);
      }
    }

    // ---- Forkify v1 ----  { "recipes": [ {..}, .. ] }
    Object recipesObj = raw.get("recipes");
    if (recipesObj instanceof List<?> list) {
      return (List<Map<String, Object>>) (List<?>) new ArrayList<>(list);
    }

    // Nenhuma receita encontrada → retorna lista vazia
    return Collections.emptyList();
  }
}
