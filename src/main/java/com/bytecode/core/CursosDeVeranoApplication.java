package com.bytecode.core;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class CursosDeVeranoApplication implements CommandLineRunner{

	@Bean
	public BCryptPasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursosDeVeranoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		String password = "1234";
		for(int i=0; i<2; i++) {
			PasswordEncoder encoder = passwordEncoder1();
			String pass = encoder.encode(password);
			System.out.println(pass); 
		}
		*/
	}

}
