package com.pedrohcvf.montai.backend;

import org.springframework.boot.SpringApplication;

public class TestMontaiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(MontaiBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
