package com.savsoftware.helpdesk.config;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.savsoftware.helpdesk.services.DBService;

@Configuration
@Profile("dev")
public class devConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value; 

    @Bean
    boolean instanciaDB() {
		
    	if(value.equals("create")) {    		
    		this.dbService.instanciaDB();    		    		    		
    	}
		return false;
	}
}
