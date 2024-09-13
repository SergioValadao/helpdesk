package com.savsoftware.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.savsoftware.helpdesk.domain.Cliente;
import com.savsoftware.helpdesk.domain.enums.Perfil;

import jakarta.validation.constraints.NotNull;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	@NotNull(message = "Necessario informar o nome!")
	protected String nome;
	@NotNull(message = "Necessario informar o CPF!")
	protected String cpf;
	@NotNull(message = "Necessario informar o email!")
	protected String email;
	@NotNull(message = "Necessario informar a senha!")
	protected String senha;
	
	protected Set<Integer> perfil = new HashSet<>();	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate datacadastro = LocalDate.now();

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente cli) {
		super();
		this.id = cli.getId();
		this.nome = cli.getNome();
		this.cpf = cli.getCpf();
		this.email = cli.getEmail();
		this.senha = cli.getSenha();
		this.perfil = cli.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.datacadastro = cli.getDataCadastro();			
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
