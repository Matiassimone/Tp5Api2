package com.tp5api2.tp5api2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Tp5api2Application {
	public static String urlServidor;

	public static void main(String[] args) {
		urlServidor = "http://localhost:8085";
		SpringApplication.run(Tp5api2Application.class, args);
	}

}
