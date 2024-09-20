package com.savsoftware.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savsoftware.helpdesk.domain.Chamados;
import com.savsoftware.helpdesk.dtos.ChamadosDTO;
import com.savsoftware.helpdesk.services.ChamadosServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadosResource  {
	
	
	@Autowired
	private ChamadosServices service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadosDTO> findById(@PathVariable Integer id){
		
		Chamados obj = service.findById(id); 		
		return ResponseEntity.ok().body(new ChamadosDTO(obj));
		
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadosDTO>> findAll(){
		
		List<Chamados> lista = service.findAll();
		
		List<ChamadosDTO> listaDTO = lista.stream().map(x -> new ChamadosDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);		
	}

	@PostMapping
	public ResponseEntity<ChamadosDTO> create(@Valid @RequestBody ChamadosDTO chamadoDTO){
				
		Chamados obj = service.create(chamadoDTO);
		//URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(obj.getId()).toUri();		
		//return ResponseEntity.created(uri).build();
		return ResponseEntity.ok().body(new ChamadosDTO(obj));		
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ChamadosDTO> delete(@PathVariable Integer id){
		
		Chamados cha = service.delete(id);
		return ResponseEntity.ok().body(new ChamadosDTO(cha));
	}
	
	@PutMapping (value = "/{id}")
	public ResponseEntity<ChamadosDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadosDTO chaDTO){
		
		Chamados cha = service.update(id, chaDTO);
		return ResponseEntity.ok().body(new ChamadosDTO(cha));		
		
	}
	
}
