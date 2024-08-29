package com.savsoftware.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savsoftware.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
