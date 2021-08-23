package com.jtm.assessment.callcentre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class CallCentreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallCentreApplication.class, args);
	}

	@Bean
	@Scope("prototype")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
