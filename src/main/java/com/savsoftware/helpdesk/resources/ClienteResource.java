package com.savsoftware.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.dtos.ClienteDTO;
import com.savsoftware.helpdesk.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findByid(@PathVariable Integer id) {
		
		Cliente cli = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cli));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> AllClientes(){
		
		List<ClienteDTO> clientes =  (service.AllClientes()).stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clientes);
		
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> Create(@Valid @RequestBody ClienteDTO cliDTO){
		
		Cliente ncli = service.create(cliDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(ncli.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	

}
