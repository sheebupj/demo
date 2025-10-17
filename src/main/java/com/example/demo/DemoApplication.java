package com.example.demo;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean // Tells Spring to create and manage this ModelMapper object
	public ModelMapper modelMapper() {
		// You can add custom configuration here if needed
		ModelMapper modelMapper = new ModelMapper();

		// Example configuration (optional, but good practice):
		 modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper;
	}


}
