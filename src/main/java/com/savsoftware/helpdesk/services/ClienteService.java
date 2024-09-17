package com.savsoftware.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.domain.Pessoa;
import com.savsoftware.helpdesk.dtos.ClienteDTO;
import com.savsoftware.helpdesk.repositories.ClienteRepository;
import com.savsoftware.helpdesk.repositories.PessoaRepository;
import com.savsoftware.helpdesk.services.Exception.DataIntregrityViolationException;
import com.savsoftware.helpdesk.services.Exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {
	
	@Autowired
	public ClienteRepository repository;
	
	@Autowired
	public PessoaRepository pessoarepository;

	
	public Cliente findById(Integer id) {
		
		Optional<Cliente> cli = repository.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Registro " + id + " não encontrado!" ));		
	}
	
	public List<Cliente> AllClientes(){
		
		return repository.findAll();
		
	}
	
	
	public Cliente create(ClienteDTO objDTO) {
		
		objDTO.setId(null);
		ValidarCliente(objDTO);
		Cliente cli = new Cliente(objDTO);
		return repository.save(cli);				
		
	}

	private void ValidarCliente(ClienteDTO obj) {

		Optional<Pessoa> pessoa = pessoarepository.findByCpf(obj.getCpf());
		
		if(pessoa.isPresent() && pessoa.get().getId() != obj.getId()) {
			throw new DataIntregrityViolationException("CPF já cadastrado no sistema!");  
		}
		
		pessoa = pessoarepository.findByEmail(obj.getEmail());
		
		if(pessoa.isPresent() && pessoa.get().getId() != obj.getId()) {
			throw new DataIntregrityViolationException("Email já cadastrado no sistema!");
		}
				
	}

	public Cliente update(Integer id, @Valid ClienteDTO obj) {

		obj.setId(id);
		Cliente cli = findById(id);		
		this.ValidarCliente(obj);
		cli = new Cliente(obj);
		return repository.save(cli);
	}
	
	public void Delete(Integer id) {
		
		Cliente cli = findById(id);
		if(cli.getChamados().size() > 0 ) {
			throw new DataIntegrityViolationException("Cliente com chamados não podem ser excluidos");
		}
		repository.delete(cli);
		
	}

}
