package com.savsoftware.helpdesk.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.savsoftware.helpdesk.domain.Tecnico;
import com.savsoftware.helpdesk.dtos.TecnicoDTO;
import com.savsoftware.helpdesk.services.TecnicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findByid(@PathVariable Integer id){
				
		Tecnico tec = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tec));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> AllTecnicos(){
				
		List<Tecnico> tec = service.AllTecnicos();
		List<TecnicoDTO> tecDTO = tec.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(tecDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecDTO){
		
		Tecnico newtec = service.create(tecDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newtec.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO obj){
		
		Tecnico tec = service.Update(id, obj);		
		return ResponseEntity.ok().body(new TecnicoDTO(tec));
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
		
		Tecnico tec = service.findById(id);
		service.Delete(id);
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(new TecnicoDTO(tec));
		
	}

}
