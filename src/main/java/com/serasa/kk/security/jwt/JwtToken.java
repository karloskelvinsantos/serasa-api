package com.serasa.kk.security.jwt;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long expiration;
    private String secretKey;
    @Column(nullable = false)
    private boolean active;
}
