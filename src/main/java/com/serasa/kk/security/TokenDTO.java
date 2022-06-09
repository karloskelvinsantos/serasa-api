package com.serasa.kk.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO {

    private final String token;
    private final String authenticationMethod;
    private final Long expiresAt;

    @JsonCreator
    public TokenDTO(@JsonProperty("token") String token,
                    @JsonProperty("authenticationMethod") String authenticationMethod,
                    @JsonProperty("expiresAt") Long expiresAt) {
        super();
        this.token = token;
        this.authenticationMethod = authenticationMethod;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    @Override
    public String toString() {
        return authenticationMethod + " " + token;
    }
}
