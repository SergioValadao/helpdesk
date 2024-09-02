package com.savsoftware.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.services.ClienteService;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> getMethodName(@PathVariable Integer id) {
		
		Cliente cli = service.findById(id);
		return ResponseEntity.ok().body(cli);
	}
	

}
