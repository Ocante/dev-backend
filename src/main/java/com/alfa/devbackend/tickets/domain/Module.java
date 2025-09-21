package com.alfa.devbackend.tickets.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MODULE")
public class Module {

  @Id
  private Long id;
  private String name;

  public Module() { }

  public Module(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
