// PROBLEMA 02 — normaliza a resposta para expor count e recipes
package com.alfa.devbackend.receitas.dto;

import java.util.List;
import java.util.Map;

public record RecipeSearchDTO(int count, List<Map<String, Object>> recipes) {}
