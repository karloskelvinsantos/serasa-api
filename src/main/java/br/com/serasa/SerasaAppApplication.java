package br.com.serasa;

import br.com.serasa.security.jwt.JwtToken;
import br.com.serasa.security.jwt.JwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaRepositories
@EnableJpaAuditing
@EnableCaching
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
