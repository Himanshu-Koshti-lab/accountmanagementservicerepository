package com.tcs.poc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class AccountManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementServiceApplication.class, args);
	}
	
//	@Bean
//	   public RestTemplate getRestTemplate() {
//	      return new RestTemplate();
//	   }

}
