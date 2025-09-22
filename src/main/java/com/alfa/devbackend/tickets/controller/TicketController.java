package com.alfa.devbackend.tickets.controller;

import com.alfa.devbackend.tickets.domain.Client;
import com.alfa.devbackend.tickets.domain.Module;
import com.alfa.devbackend.tickets.domain.Ticket;
import com.alfa.devbackend.tickets.dto.CreateTicketRequest;
import com.alfa.devbackend.tickets.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketRepository ticketRepository;

  @PostMapping
  public Ticket create(@RequestBody @Valid CreateTicketRequest req) {
    // Prepara o objeto a ser salvo (associa Client e Module pelos respectivos IDs)
    Ticket t = new Ticket();
    t.setTitle(req.title());
    t.setOpeningDate(req.openingDate());
    t.setClosingDate(req.closingDate());
    t.setClient(new Client(req.clientId(), null));
    t.setModule(new Module(req.moduleId(), null));
    return ticketRepository.save(t);
  }
}
