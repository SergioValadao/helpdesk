package com.savsoftware.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.savsoftware.helpdesk.domain.Pessoa;
import com.savsoftware.helpdesk.domain.Tecnico;
import com.savsoftware.helpdesk.dtos.TecnicoDTO;
import com.savsoftware.helpdesk.repositories.PessoaRepository;
import com.savsoftware.helpdesk.repositories.TecnicoRepository;
import com.savsoftware.helpdesk.services.Exception.DataIntregrityViolationException;
import com.savsoftware.helpdesk.services.Exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
	
	@Autowired	
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	
	public Tecnico findById(Integer id) {
				
		Optional<Tecnico> tec = repository.findById(id);
		return tec.orElseThrow(() -> new ObjectNotFoundException("Registro " + id + " não encontrado."));
	}
	
	public List<Tecnico> AllTecnicos(){
		
		return repository.findAll();
		
	}

	public Tecnico create(TecnicoDTO tecDTO) {
		
		tecDTO.setId(null);		
		
		ValidarPessoa(tecDTO);
		Tecnico tecObj = new Tecnico(tecDTO);
		return repository.save(tecObj);		
		
	}

	public void ValidarPessoa(TecnicoDTO tecDTO) {

		Optional<Pessoa> p = pessoarepository.findByCpf(tecDTO.getCpf());
		
		if(p.isPresent() && p.get().getId() != tecDTO.getId()) {
			throw new DataIntregrityViolationException("CPF já cadastrado no sistema!"); 
		}
		
		p = pessoarepository.findByEmail(tecDTO.getEmail());
		
		if(p.isPresent() && p.get().getId() != tecDTO.getId()) {
			throw new DataIntregrityViolationException("Email já cadastrado no sistema!");
		}
		
	}

	public Tecnico Update(Integer id, @Valid TecnicoDTO obj) {
		
		obj.setId(id);
		Tecnico tec = findById(id);
		ValidarPessoa(obj);
		tec = new Tecnico(obj);
		return repository.save(tec);
	}
	
	public void Delete(Integer id) {
		
		Tecnico tec = findById(id);		
		if(tec.getChamados().size() > 0 ) {
			throw new DataIntegrityViolationException("Existe chamados para este técnico!"); 
		}
		repository.delete(tec);					
	}

}
