package br.com.tech4me.casams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CasaMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaMsApplication.class, args);
	}

}
