// com.alfa.devbackend.tickets.repository.ModuleRepository
package com.alfa.devbackend.tickets.repository;
import com.alfa.devbackend.tickets.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ModuleRepository extends JpaRepository<Module, Long> {}
