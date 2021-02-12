package br.com.itau.renegociacaodiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RenegociacaoDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenegociacaoDiscoveryApplication.class, args);
	}

}
