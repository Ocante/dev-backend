package com.alfa.devbackend.tickets.service;

import com.alfa.devbackend.tickets.domain.Ticket;
import com.alfa.devbackend.tickets.dto.DashboardDTO;
import com.alfa.devbackend.tickets.dto.GroupCountDTO;
import com.alfa.devbackend.tickets.dto.PeriodDTO;
import com.alfa.devbackend.tickets.dto.TicketDTO;
import com.alfa.devbackend.tickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketService {

  private final TicketRepository ticketRepository;

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public DashboardDTO getDashboard(int month, int year) {
    YearMonth ym = YearMonth.of(year, month);
    LocalDate start = ym.atDay(1);
    LocalDate end   = ym.atEndOfMonth();

    List<Ticket> tickets = ticketRepository.findAllInMonth(start, end);

    List<TicketDTO> ticketDtos = tickets.stream()
        .sorted(Comparator.comparing(Ticket::getOpeningDate))
        .map(t -> new TicketDTO(
            t.getId(),
            t.getTitle(),
            t.getClient().getId(),
            t.getClient().getName(),
            t.getModule().getId(),
            t.getModule().getName(),
            t.getOpeningDate(),
            t.getClosingDate()
        ))
        .toList();

    Map<Long, Long> byClient = tickets.stream()
        .collect(Collectors.groupingBy(t -> t.getClient().getId(), Collectors.counting()));

    Map<Long, Long> byModule = tickets.stream()
        .collect(Collectors.groupingBy(t -> t.getModule().getId(), Collectors.counting()));

    List<GroupCountDTO> groupByClient = byClient.entrySet().stream()
        .map(e -> {
          Long id = e.getKey();
          String name = tickets.stream()
              .filter(t -> t.getClient().getId().equals(id))
              .findFirst()
              .map(t -> t.getClient().getName())
              .orElse("Desconhecido");
          return new GroupCountDTO(id, name, e.getValue());
        })
        .sorted(Comparator.comparing(GroupCountDTO::total).reversed())
        .toList();

    List<GroupCountDTO> groupByModule = byModule.entrySet().stream()
        .map(e -> {
          Long id = e.getKey();
          String name = tickets.stream()
              .filter(t -> t.getModule().getId().equals(id))
              .findFirst()
              .map(t -> t.getModule().getName())
              .orElse("Desconhecido");
          return new GroupCountDTO(id, name, e.getValue());
        })
        .sorted(Comparator.comparing(GroupCountDTO::total).reversed())
        .toList();

    return new DashboardDTO(new PeriodDTO(month, year), ticketDtos, groupByClient, groupByModule);
  }
}
