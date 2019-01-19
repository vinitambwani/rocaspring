package com.eny.roca.rocaeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class RocaConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocaConfigServiceApplication.class, args);
	}

}

