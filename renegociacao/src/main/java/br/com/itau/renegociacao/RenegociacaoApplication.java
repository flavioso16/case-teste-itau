package br.com.itau.renegociacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RenegociacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenegociacaoApplication.class, args);
	}

}
