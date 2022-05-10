package com.example.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ConferenceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ConferenceApplication.class, args);
		runSwaggerOpenApiOnStart();
	}
	private static void runSwaggerOpenApiOnStart() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080/swagger-ui/index.html");
	}

}
