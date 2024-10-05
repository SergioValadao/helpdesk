package com.savsoftware.helpdesk.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private Environment env;
	
   @Bean
   WebSecurityCustomizer webSecurityCustomizer() {	   
       return (web) -> web.ignoring().requestMatchers("/chamados", "tecnicos", "/clientes");
      
   }
      
  
@Bean
   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   	   
	   if (Arrays.asList(env.getActiveProfiles()).contains("teste")) {	  	   	  
		   http          	
           .authorizeHttpRequests(auth -> auth
        		   .requestMatchers("/h2-console", "/h2-console/**").permitAll()
                   .anyRequest().permitAll()                   
           )               
           .headers(headers -> headers.frameOptions().disable())
           .csrf(csrf -> csrf.disable());
	   } else {		   		   
		   http          	
           .authorizeHttpRequests(auth -> auth
        		   .requestMatchers("/h2-console", "/h2-console/**").denyAll()
                   .anyRequest().permitAll()                   
           )               
           .headers(headers -> headers.frameOptions().disable())
           .csrf(csrf -> csrf.disable());
	   }	   
	   return http.build();
   }
   
   @Bean
   HttpFirewall allowHttpMethod() {
       List<String> allowedMethods = new ArrayList<>();
       allowedMethods.add("GET");
       allowedMethods.add("POST");
       allowedMethods.add("DELETE");
       allowedMethods.add("PUT");
       allowedMethods.add("OPTIONS");       
       StrictHttpFirewall firewall = new StrictHttpFirewall();       
       firewall.setAllowedHttpMethods(allowedMethods);
       return firewall;
   }
   
   @Bean
   BCryptPasswordEncoder bCryptPasswordEncoder() {
	   return new BCryptPasswordEncoder();
   }
      
}