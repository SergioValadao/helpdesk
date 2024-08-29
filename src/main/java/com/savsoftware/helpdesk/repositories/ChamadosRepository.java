package com.savsoftware.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savsoftware.helpdesk.domain.Chamados;

public interface ChamadosRepository extends JpaRepository<Chamados, Integer> {	

}
