package com.savsoftware.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savsoftware.helpdesk.domain.Chamados;
import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.domain.Tecnico;
import com.savsoftware.helpdesk.domain.enums.Prioridade;
import com.savsoftware.helpdesk.domain.enums.Status;
import com.savsoftware.helpdesk.dtos.ChamadosDTO;
import com.savsoftware.helpdesk.repositories.ChamadosRepository;
import com.savsoftware.helpdesk.services.Exception.DataIntegrityViolationException;
import com.savsoftware.helpdesk.services.Exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadosServices {
	
	@Autowired
	private ChamadosRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamados findById(Integer id) {
		
		Optional<Chamados> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado " + id + " não encontrado!"));		
	}
	
	public List<Chamados> findAll(){
		
		List<Chamados> Lista = repository.findAll();				
		return Lista;		
	}
	
	public Chamados create(@Valid ChamadosDTO chamadoDTO) {
		
		Tecnico tec = tecnicoService.findById(chamadoDTO.getTecnico());
		Cliente cli = clienteService.findById(chamadoDTO.getCliente());
		
		Chamados cha = new Chamados();
		cha.setCliente(cli);
		cha.setTecnico(tec);
		cha.setObservacao(chamadoDTO.getObservacao());
		cha.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		cha.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		cha.setTitulo(chamadoDTO.getTitulo());
						
		return repository.save(cha);
	}

	public void delete(Integer id) {
		
		Optional<Chamados> chamado = repository.findById(id);
		
		if(chamado.equals(null)) {
			throw new DataIntegrityViolationException("Chamado não encontrado para exclusão! Verifique");
		}
		if(!chamado.get().getStatus().getCodigo().equals(2)) {
			throw new DataIntegrityViolationException("Somente Chamado com status encerrado pode ser excluido! Verifique");
		}
		repository.deleteById(id);
			
	}

	public Chamados update(Integer id, ChamadosDTO chaDTO) {		
		chaDTO.setId(id);
		Optional<Chamados> cha = repository.findById(id);
		
		if(cha.equals(null)) {
			throw new DataIntegrityViolationException("Registro não encontrado para exclusão! Verifique.");
		}
		
		if(chaDTO.getStatus().equals(2)) {
			cha.get().setDataFechamento(LocalDate.now());
		}
		
		Cliente cli = clienteService.findById(chaDTO.getCliente());
		Tecnico tec = tecnicoService.findById(chaDTO.getTecnico());
		
		cha.get().setCliente(cli);
		cha.get().setTecnico(tec);
		
		cha.get().setObservacao(chaDTO.getObservacao());
		cha.get().setPrioridade(Prioridade.toEnum(chaDTO.getPrioridade()));
		cha.get().setStatus(Status.toEnum(chaDTO.getStatus()));
		cha.get().setTitulo(chaDTO.getTitulo());
		return repository.save(cha.get());		
	}	
}
