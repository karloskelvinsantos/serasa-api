package com.serasa.kk.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class PessoaDTO {

    @NotBlank
    private String nome;
    private String telefone;
    private Integer idade;
    private String cidade;
    @NotBlank
    @Size(max = 2, min = 2, message = "Formato deve ser -> 'XX'")
    private String estado;
    @Min(0)
    @Max(100)
    private int score;
    private String regiao;
    private LocalDateTime createdAt;
}
