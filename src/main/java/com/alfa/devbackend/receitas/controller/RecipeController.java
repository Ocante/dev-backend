// PROBLEMA 02 — endpoint GET que recebe 'dish' e retorna receitas da Forkify
package com.alfa.devbackend.receitas.controller;

import com.alfa.devbackend.receitas.dto.RecipeSearchDTO;
import com.alfa.devbackend.receitas.service.RecipeService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
@Validated
public class RecipeController {

  private final RecipeService service;

  @GetMapping
  public RecipeSearchDTO search(@RequestParam @NotBlank String dish) {
    // Ex.: /api/v1/recipes?dish=pizza
    return service.search(dish);
  }
}
