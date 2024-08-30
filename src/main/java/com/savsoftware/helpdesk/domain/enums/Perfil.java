package com.savsoftware.helpdesk.domain.enums;

public enum Perfil {
	
	ADMIN(0, "ADMIN"), CLIENTE(1, "CLIENTE"), TECNICO(2, "TECNICO");
	
	private Integer codigo;
	private String descricao;
	
	private Perfil(Integer codigo, String descricao) {
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
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x.getDescricao();
			}
		}
		throw new IllegalArgumentException("prefil inválido");
	}
	
}
