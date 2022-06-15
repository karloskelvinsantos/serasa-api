package br.com.serasa.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    public static final String AUTHORIZATION = "Authorization";
    private final AuthService authService;

    TokenAuthenticationFilter(AuthService authService) {
        super();
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Optional<String> token = getToken(request);

        if(isValidToken(token)) {
            authorizeRequestWithToken(token.get());
        }

        filterChain.doFilter(request, response);

    }

    private Optional<String> getToken(HttpServletRequest request) {

        String token = request.getHeader(AUTHORIZATION);

        if(token!=null) {
            token = token.substring(7);
        }

        return Optional.ofNullable(token);

    }

    private boolean isValidToken(Optional<String> token) {
        return token.isPresent() && authService.isValidToken(token.get());
    }

    private void authorizeRequestWithToken(String token) {
        Optional<SerasaUser> userFromToken = authService.getUserFromToken(token);
        if(userFromToken.isPresent()) {
            SerasaUser user = userFromToken.get();
            logger.info("authorizing {}",user.getUsername());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

    }
}
