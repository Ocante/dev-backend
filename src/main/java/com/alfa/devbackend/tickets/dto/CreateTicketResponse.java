package com.alfa.devbackend.tickets.dto;

import java.time.LocalDate;

public record CreateTicketResponse(
    Long id,
    String title,
    Long clientId,
    Long moduleId,
    LocalDate openingDate,
    LocalDate closingDate
) {}
