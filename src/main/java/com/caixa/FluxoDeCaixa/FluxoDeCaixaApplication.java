package com.caixa.FluxoDeCaixa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.caixa.FluxoDeCaixa.DAO.CustomJpaDAOImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaDAOImpl.class)
public class FluxoDeCaixaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluxoDeCaixaApplication.class, args);
	}

}
