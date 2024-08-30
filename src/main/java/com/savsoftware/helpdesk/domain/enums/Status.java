package com.savsoftware.helpdesk.domain.enums;

public enum Status {
	
	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTE"), ENCERRADO(2, "ENCERRADO");
	
	private Integer codigo;
	private String descricao;
	
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static String toEnum(Integer cod) {
		if(cod == null ) {
			return null;
		}
		for(Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) {
				return x.getDescricao();
			}
		}
		throw new IllegalArgumentException("Status inválido");
	}
	
}
