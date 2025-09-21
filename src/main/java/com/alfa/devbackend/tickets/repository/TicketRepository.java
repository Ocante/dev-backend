// PROBLEMA 01 — consultas de TICKET (sem agrupamento no SQL)
// O PDF pede que os agrupamentos sejam em MEMÓRIA (não usar SQL p/ isso). :contentReference[oaicite:4]{index=4}
package com.alfa.devbackend.tickets.repository;

import com.alfa.devbackend.tickets.domain.Ticket;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

  // Busca tickets cujo período cai dentro do mês consultado
  @Query("""
         select t from Ticket t
         where (t.openingDate >= :start and t.openingDate <= :end)
            or (t.closingDate  >= :start and t.closingDate  <= :end)
         """)
  List<Ticket> findAllInMonth(@Param("start") LocalDate start,
                              @Param("end")   LocalDate end);
}
