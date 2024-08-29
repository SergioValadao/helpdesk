package com.savsoftware.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savsoftware.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
