package com.SpingbootApp;

import io.jsonwebtoken.Jwts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpingbootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpingbootAppApplication.class, args);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String rawPassword = "test@123";
//		String encodedPassword = encoder.encode(rawPassword);
//		System.out.println("Hashed Password: " + encodedPassword);
	}

}
