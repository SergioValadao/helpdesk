package com.savsoftware.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savsoftware.helpdesk.domain.Chamados;
import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.domain.Tecnico;
import com.savsoftware.helpdesk.domain.enums.Perfil;
import com.savsoftware.helpdesk.domain.enums.Prioridade;
import com.savsoftware.helpdesk.domain.enums.Status;
import com.savsoftware.helpdesk.repositories.ChamadosRepository;
import com.savsoftware.helpdesk.repositories.ClienteRepository;
import com.savsoftware.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadosRepository chamadosRepository;

	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Waldir Cesar", "087.397.060-87", "waldir@gmail.com", "123" );		
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "660.456.290-51", "linus@gmail.com", "123");
		Cliente cli2 = new Cliente(null, "Sergio Valadão", "478.889.977-91", "sergio@gmail.com", "123");
		cli2.addPerfil(Perfil.ADMIN);
		
		Chamados c1 = new Chamados(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		Chamados c2 = new Chamados(null, Prioridade.ALTA, Status.ABERTO, "Chamado 02", "Segundo chamado", tec1, cli1);
	
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		clienteRepository.saveAll(Arrays.asList(cli2));
		chamadosRepository.saveAll(Arrays.asList(c1));
		chamadosRepository.saveAll(Arrays.asList(c2));
	}
	
}