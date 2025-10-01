// com.alfa.devbackend.tickets.repository.ClientRepository
package com.alfa.devbackend.tickets.repository;
import com.alfa.devbackend.tickets.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Long> {}
