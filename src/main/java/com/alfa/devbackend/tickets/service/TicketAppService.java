package com.alfa.devbackend.tickets.service;

import com.alfa.devbackend.tickets.domain.Ticket;
import com.alfa.devbackend.tickets.dto.CreateTicketRequest;
import com.alfa.devbackend.tickets.repository.ClientRepository;
import com.alfa.devbackend.tickets.repository.ModuleRepository;
import com.alfa.devbackend.tickets.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TicketAppService {

  private final TicketRepository ticketRepo;
  private final ClientRepository clientRepo;
  private final ModuleRepository moduleRepo;

  public TicketAppService(
      TicketRepository ticketRepo,
      ClientRepository clientRepo,
      ModuleRepository moduleRepo
  ) {
    this.ticketRepo = ticketRepo;
    this.clientRepo = clientRepo;
    this.moduleRepo = moduleRepo;
  }

  public Ticket create(CreateTicketRequest req) {
    if (req.clientId() == null || !clientRepo.existsById(req.clientId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ClientId inexistente");
    }
    if (req.moduleId() == null || !moduleRepo.existsById(req.moduleId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ModuleId inexistente");
    }

    try {
      var t = new Ticket();
      t.setTitle(req.title());
      t.setOpeningDate(req.openingDate());
      t.setClosingDate(req.closingDate());
      t.setClient(clientRepo.getReferenceById(req.clientId()));
      t.setModule(moduleRepo.getReferenceById(req.moduleId()));
      return ticketRepo.save(t);
    } catch (EntityNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ClientId ou ModuleId inexistente");
    }
  }
}
