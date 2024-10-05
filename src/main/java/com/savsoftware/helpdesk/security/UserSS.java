package com.savsoftware.helpdesk.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.savsoftware.helpdesk.domain.enums.Perfil;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSS(Integer id, String email, String senha, Set<Perfil> perfil) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;		
		this.authorities = perfil.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		//return UserDetails.super.isAccountNonExpired();
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//return UserDetails.super.isAccountNonLocked();
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//return UserDetails.super.isCredentialsNonExpired();
		return true;
	}

	@Override
	public boolean isEnabled() {
		//return UserDetails.super.isEnabled();
		return true;
	}



}
