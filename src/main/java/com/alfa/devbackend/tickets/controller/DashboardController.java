package com.alfa.devbackend.tickets.controller;

import com.alfa.devbackend.tickets.dto.DashboardDTO;
import com.alfa.devbackend.tickets.service.TicketService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@Validated
public class DashboardController {

  private final TicketService ticketService;

  public DashboardController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  // GET /api/v1/dashboard?month=10&year=2025
  @GetMapping
  public DashboardDTO getDashboard(
      @RequestParam @Min(1) @Max(12) int month,
      @RequestParam @Positive int year
  ) {
    return ticketService.getDashboard(month, year);
  }
}
