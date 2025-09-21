// PROBLEMA 01 — formato para devolver tickets no JSON
package com.alfa.devbackend.tickets.dto;

import java.time.LocalDate;

public record TicketDTO(
    Long id,
    String title,
    Long clientId,
    String client,
    Long moduleId,
    String module,
    LocalDate openingDate,
    LocalDate closingDate
) {}
