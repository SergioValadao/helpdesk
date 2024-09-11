package com.savsoftware.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savsoftware.helpdesk.domain.Tecnico;
import com.savsoftware.helpdesk.repositories.TecnicoRepository;
import com.savsoftware.helpdesk.services.Exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
				
		Optional<Tecnico> tec = repository.findById(id);
		return tec.orElseThrow(() -> new ObjectNotFoundException("Registro " + id + " não encontrado."));
	}
	
	public List<Tecnico> AllTecnicos(){
		
		return repository.findAll();		
	}

}
