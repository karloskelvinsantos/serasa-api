package com.serasa.kk.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken doAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
