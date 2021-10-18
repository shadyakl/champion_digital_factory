package com.digital.factory.champion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ChampionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChampionApplication.class, args);
	}

}
