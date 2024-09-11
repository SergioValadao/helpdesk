package com.savsoftware.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savsoftware.helpdesk.domain.enums.Perfil;
import com.savsoftware.helpdesk.dtos.TecnicoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore				//para evitar entrar em erro 
	@OneToMany(mappedBy = "tecnico" )
	private List<Chamados> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.TECNICO);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.TECNICO);		
	}

	public Tecnico(TecnicoDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfil = obj.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());		
		this.datacadastro = obj.getDatacadastro();
		addPerfil(Perfil.TECNICO);
	}
	
	public List<Chamados> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamados> chamados) {
		this.chamados = chamados;
	}
	
}
