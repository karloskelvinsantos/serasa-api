package com.serasa.kk.exception;

public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException(String message) {
        super(message);
    }
}
