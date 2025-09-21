// PROBLEMA 01 — endpoint POST para criar um ticket (pedido no Enunciado ). :contentReference[oaicite:6]{index=6}
package com.alfa.devbackend.tickets.controller;

import com.alfa.devbackend.tickets.domain.Client;
import com.alfa.devbackend.tickets.domain.Module;
import com.alfa.devbackend.tickets.domain.Ticket;
import com.alfa.devbackend.tickets.repository.TicketRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketRepository ticketRepository;

  @PostMapping
  public Ticket create(@RequestBody CreateTicketRequest req) {
    // Prepara o objeto a ser salvo (associa Client e Module pelos respectivos IDs)
    Ticket t = new Ticket();
    t.setTitle(req.title);
    t.setOpeningDate(req.openingDate);
    t.setClosingDate(req.closingDate);
    t.setClient(new Client(req.clientId, null));
    t.setModule(new Module(req.moduleId, null));
    return ticketRepository.save(t);
  }

  @Data
  public static class CreateTicketRequest {
    @NotBlank public String title;
    @NotNull public Long clientId;
    @NotNull public Long moduleId;
    @NotNull public LocalDate openingDate;
    public LocalDate closingDate;
  }
}
