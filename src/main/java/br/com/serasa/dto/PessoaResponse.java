package br.com.serasa.dto;

import lombok.Data;

import java.util.List;

@Data
public class PessoaResponse {

    private String nome;
    private String cidade;
    private String estado;
    private String scoreDescricao;
    private List<String> estados;
}
