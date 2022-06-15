package br.com.serasa.dto;

import lombok.Data;

import java.util.List;

@Data
public class AfinidadeDTO {

    private String regiao;
    private List<String> estados;
}
