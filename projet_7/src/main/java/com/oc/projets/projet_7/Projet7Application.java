package com.oc.projets.projet_7;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
//@EnableBatchProcessing
public class Projet7Application {

	public static void main(String[] args) {
		SpringApplication.run(Projet7Application.class, args);
	}

}
