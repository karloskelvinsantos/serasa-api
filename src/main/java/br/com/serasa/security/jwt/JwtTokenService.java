package br.com.serasa.security.jwt;

import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

    private final JwtTokenRepository repository;

    public JwtTokenService(JwtTokenRepository repository) {
        this.repository = repository;
    }

    public JwtToken findTop() {
        return repository.findFirstByOrderByIdDesc();
    }
}
