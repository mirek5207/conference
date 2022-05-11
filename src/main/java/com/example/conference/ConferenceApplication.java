package com.example.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ConferenceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ConferenceApplication.class, args);
	}
}
