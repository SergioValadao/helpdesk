package com.savsoftware.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savsoftware.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
