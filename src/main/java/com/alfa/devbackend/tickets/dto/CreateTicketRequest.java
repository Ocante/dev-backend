package com.alfa.devbackend.tickets.dto;

import java.time.LocalDate;

// DTO usado para receber os dados do POST /tickets
public record CreateTicketRequest(
    String title,       // Título do ticket
    Long clientId,      // ID do cliente (deve existir no banco)
    Long moduleId,      // ID do módulo (deve existir no banco)
    LocalDate openingDate,  // Data de abertura
    LocalDate closingDate   // Data de fechamento (opcional)
) {}
