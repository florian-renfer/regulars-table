package com.github.florian_renfer.regulars_table;

import org.springframework.boot.SpringApplication;

public class TestRegularsTableApplication {

	public static void main(String[] args) {
		SpringApplication.from(RegularsTableApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
