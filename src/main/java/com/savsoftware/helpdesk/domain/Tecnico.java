package com.savsoftware.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.savsoftware.helpdesk.domain.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "tecnico")
	private List<Chamados> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.TECNICO);		//Instrutor usou CLIENTE
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.TECNICO);		//Instrutor usou CLIENTE
	}

	public List<Chamados> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamados> chamados) {
		this.chamados = chamados;
	}	
	
}
