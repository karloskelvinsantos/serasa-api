package com.serasa.kk;

import com.serasa.kk.security.jwt.JwtToken;
import com.serasa.kk.security.jwt.JwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@AllArgsConstructor
public class SerasaAppApplication {

	private final JwtTokenService jwtTokenService;

	public static void main(String[] args) {
		SpringApplication.run(SerasaAppApplication.class, args);
	}

	@Bean
	public JwtToken jwtToken() {
		return jwtTokenService.findTop();
	}

}
