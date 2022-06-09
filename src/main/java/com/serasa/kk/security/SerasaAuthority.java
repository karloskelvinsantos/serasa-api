package com.serasa.kk.security;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "serasa_authority")
public class SerasaAuthority implements GrantedAuthority {

    public static final String COMMON_USER = "USER";
    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public SerasaAuthority() {
    }

    public SerasaAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static SerasaAuthority commonUser() {
        return new SerasaAuthority(COMMON_USER);
    }
}
