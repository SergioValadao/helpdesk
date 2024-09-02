package com.savsoftware.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	public ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		
		Optional<Cliente> cli = repository.findById(id);
		return cli.orElse(null);
		
	}

}
