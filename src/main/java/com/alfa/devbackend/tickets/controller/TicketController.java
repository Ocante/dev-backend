package com.alfa.devbackend.tickets.controller;

import com.alfa.devbackend.tickets.dto.CreateTicketRequest;
import com.alfa.devbackend.tickets.dto.CreateTicketResponse;
import com.alfa.devbackend.tickets.domain.Ticket;
import com.alfa.devbackend.tickets.service.TicketAppService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

  private final TicketAppService service;

  public TicketController(TicketAppService service) {
    this.service = service;
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<CreateTicketResponse> create(@RequestBody @Valid CreateTicketRequest req) {
    Ticket saved = service.create(req);

    // Mapeia para DTO sem tocar nos proxies (usa ids que já recebemos no request)
    CreateTicketResponse dto = new CreateTicketResponse(
        saved.getId(),
        saved.getTitle(),
        req.clientId(),
        req.moduleId(),
        saved.getOpeningDate(),
        saved.getClosingDate()
    );

    return ResponseEntity
        .created(URI.create("/api/v1/tickets/" + saved.getId()))
        .body(dto);
  }
}
