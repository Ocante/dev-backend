// PROBLEMA 02 — endpoint GET que recebe 'dish' ou 'slug' e retorna receitas da Forkify
package com.alfa.devbackend.receitas.controller;

import com.alfa.devbackend.receitas.dto.RecipeSearchDTO;
import com.alfa.devbackend.receitas.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
@Validated
public class RecipeController {

  private final RecipeService service;

  // GET /api/v1/recipes?dish=pizza  (ou)  /api/v1/recipes?slug=pizza
  @GetMapping
  public RecipeSearchDTO search(
      @RequestParam(required = false, name = "dish") String dish,
      @RequestParam(required = false, name = "slug") String slug) {

    String q = (dish != null && !dish.isBlank()) ? dish : slug;

    if (q == null || q.isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Informe 'dish' ou 'slug' como parâmetro de query");
    }

    return service.search(q);
  }

  // Endpoint de diagnóstico para validar o mapeamento da rota
  // GET /api/v1/recipes/ping -> {"ok":"true"}
  @GetMapping("/ping")
  public Map<String, String> ping() {
    return Map.of("ok", "true");
  }
}
