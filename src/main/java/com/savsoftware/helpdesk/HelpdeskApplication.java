package com.savsoftware.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.savsoftware.helpdesk.domain.enums.Perfil;
import com.savsoftware.helpdesk.domain.enums.Prioridade;
import com.savsoftware.helpdesk.domain.enums.Status;

import com.savsoftware.helpdesk.domain.Chamados;
import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.domain.Tecnico;


import com.savsoftware.helpdesk.repositories.ClienteRepository;
import com.savsoftware.helpdesk.repositories.TecnicoRepository;
import com.savsoftware.helpdesk.repositories.ChamadosRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadosRepository chamadosRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Waldir Cesar", "087.397.060-87", "waldir@gmail.com", "123" );
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "660.456.290-51", "linus@gmail.com", "123");
		
		Chamados c1 = new Chamados(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
	
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadosRepository.saveAll(Arrays.asList(c1));
		
	}

}
