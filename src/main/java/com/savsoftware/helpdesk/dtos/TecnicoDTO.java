package com.savsoftware.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.savsoftware.helpdesk.domain.Tecnico;
import com.savsoftware.helpdesk.domain.enums.Perfil;

import jakarta.validation.constraints.NotNull;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	@NotNull(message = "Necessario informar o nome!")
	protected String nome;	
	@NotNull(message = "Necessario informar o CPF!")
	protected String cpf;
	@NotNull(message = "Necessario informar o email!")
	protected String email;
	@NotNull(message = "Necessario informar a Senha!")
	protected String senha;		
	protected Set<Integer> perfil = new HashSet<>();	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate datacadastro = LocalDate.now();
	
	public TecnicoDTO() {
		super();
		addPerfil(Perfil.TECNICO);
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfil = obj.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());		
		this.datacadastro = obj.getDataCadastro();
		addPerfil(Perfil.TECNICO);
	}

	public void addPerfil(Perfil perfil) {
		this.perfil.add(perfil.getCodigo());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfil() {
		return perfil.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());		
	}

	public void setPerfil(Set<Integer> perfil) {
		this.perfil = perfil;
	}

	public LocalDate getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(LocalDate datacadastro) {
		this.datacadastro = datacadastro;
	}
		
}
