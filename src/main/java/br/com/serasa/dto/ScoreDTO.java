package br.com.serasa.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ScoreDTO implements Serializable {

    @JsonAlias({"scoreDescricao", "inicial", "final"})
    private String scoreDescricao;
    private int inicial;
    @JsonProperty("final")
    private int scoreFinal;
}
