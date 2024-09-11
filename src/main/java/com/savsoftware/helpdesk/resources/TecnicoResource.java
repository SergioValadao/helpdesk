package com.savsoftware.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savsoftware.helpdesk.domain.Tecnico;
import com.savsoftware.helpdesk.dtos.TecnicoDTO;
import com.savsoftware.helpdesk.services.TecnicoService;

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
	
	@GetMapping(value = "/")
	public ResponseEntity<List<TecnicoDTO>> AllTecnicos(){
				
		List<Tecnico> tec = service.AllTecnicos();
		List<TecnicoDTO> tecDTO = tec.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(tecDTO);
	}
	
	

}
