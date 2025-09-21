package com.alfa.devbackend.tickets.domain;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "TICKET")
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_ID_CLIENT", nullable = false)
  private Client client;

  @Column(name = "OPENING_DATE", nullable = false)
  private LocalDate openingDate;

  @Column(name = "CLOSING_DATE")
  private LocalDate closingDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_ID_MODULE", nullable = false)
  private Module module;

  public Ticket() { }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public Client getClient() { return client; }
  public void setClient(Client client) { this.client = client; }

  public LocalDate getOpeningDate() { return openingDate; }
  public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }

  public LocalDate getClosingDate() { return closingDate; }
  public void setClosingDate(LocalDate closingDate) { this.closingDate = closingDate; }

  public Module getModule() { return module; }
  public void setModule(Module module) { this.module = module; }
}
