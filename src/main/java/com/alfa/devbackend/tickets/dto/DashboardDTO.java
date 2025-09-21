// PROBLEMA 01 — resposta do dashboard (período + lista + agrupamentos)
package com.alfa.devbackend.tickets.dto;

import java.util.List;

public record DashboardDTO(
    PeriodDTO period,
    List<TicketDTO> tickets,
    List<GroupCountDTO> groupByClient,
    List<GroupCountDTO> groupByModule
) {}
