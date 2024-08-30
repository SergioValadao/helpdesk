package com.savsoftware.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.savsoftware.helpdesk.services.DBService;

@Configuration
@Profile("teste")
public class testeConfig {

	@Autowired
	private DBService dbService;

    @Bean
    boolean instanciaDB() {
		
		this.dbService.instanciaDB();
		return true; 
	}
}
