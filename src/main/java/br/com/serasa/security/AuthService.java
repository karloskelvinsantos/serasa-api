package br.com.serasa.security;

import br.com.serasa.security.jwt.JwtToken;
import br.com.serasa.security.jwt.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class AuthService implements UserDetailsService {
    public static final String BEARER = "Bearer";
    public static final String ROLES = "roles";

    private static final String ISSUER = "serara-api";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository repository;
    private JwtToken jwtToken;

    private JwtTokenService jwtTokenService;

    public AuthService(UserRepository repository, JwtTokenService jwtTokenService) {
        this.repository = repository;
        this.jwtTokenService = jwtTokenService;
        this.jwtToken = jwtTokenService.findTop();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SerasaUser> user = repository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("User not found!");
    }

    public TokenDTO generateToken(Authentication authentication) {
        this.jwtToken = jwtTokenService.findTop();

        var principal = (SerasaUser) authentication.getPrincipal();

        Date today = new Date();
        Date expiration = new Date(today.getTime() + jwtToken.getExpiration());

        String token = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(principal.getId().toString())
                .claim(ROLES, principal.getAuthorityNames())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtToken.getSecretKey())
                .compact();

        return new TokenDTO(token, BEARER, expiration.getTime());
    }

    public boolean isValidToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtToken.getSecretKey())
                    .requireIssuer(ISSUER)
                    .parseClaimsJws(token)!=null;
        }catch(io.jsonwebtoken.SignatureException e) {
            return false;
        }
    }

    public Optional<SerasaUser> getUserFromToken(String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtToken.getSecretKey()).parseClaimsJws(token).getBody();
            Long id = Long.parseLong(body.getSubject());
            return repository.findById(id);
        } catch (io.jsonwebtoken.SignatureException e) {
            logger.warn("No possible to parser token: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
