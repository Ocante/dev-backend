// PROBLEMA 01 — endpoint GET que devolve lista + agrupamentos (uma única chamada)
// O PDF pede receber mês e ano como parâmetros. :contentReference[oaicite:5]{index=5}
package com.alfa.devbackend.tickets.controller;

import com.alfa.devbackend.tickets.dto.DashboardDTO;
import com.alfa.devbackend.tickets.service.TicketService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@Validated
public class DashboardController {

  private final TicketService ticketService;

  @GetMapping
  public DashboardDTO getDashboard(
      @RequestParam @Min(1) @Max(12) int month,
      @RequestParam @Positive int year
  ) {
    return ticketService.getDashboard(month, year);
  }
}
