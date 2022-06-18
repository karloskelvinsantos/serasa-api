package br.com.serasa.util;

import br.com.serasa.model.RegiaoEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TestConstants {

    public static final Long DEFAULT_PESSOA_ID = 1L;
    public static final String DEFAULT_PESSOA_NOME = "product one";
    public static final String DEFAULT_PESSOA_TELEFONE = "description product one";
    public static final Integer DEFAULT_PESSOA_IDADE = 20;
    public static final String DEFAULT_PESSOA_CIDADE = "São Paulo";
    public static final String DEFAULT_PESSOA_ESTADO = "SP";
    public static final int DEFAULT_PESSOA_SCORE = 200;
    public static final RegiaoEnum DEFAULT_PESSOA_REGIAO = RegiaoEnum.SUDESTE;

    public static final List<String> DEFAULT_PESSOA_ESTADOS = Arrays.asList("SP", "RJ", "MG", "SP");
    public static final String DEFAULT_PESSOA_SCORE_DESCRICAO = "Insuficiente";


    private TestConstants() {}
}
