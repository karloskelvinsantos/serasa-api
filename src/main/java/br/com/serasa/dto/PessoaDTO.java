package br.com.serasa.dto;

import br.com.serasa.model.RegiaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Max(1000)
    private int score;
    private RegiaoEnum regiao;
}
