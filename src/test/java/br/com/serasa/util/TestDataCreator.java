package br.com.serasa.util;

import br.com.serasa.dto.PessoaDTO;
import br.com.serasa.dto.PessoaResponse;
import br.com.serasa.model.Pessoa;
import org.modelmapper.ModelMapper;

import static br.com.serasa.util.TestConstants.*;

public final class TestDataCreator {

    private TestDataCreator() {}

    public static Pessoa createPessoa() {
        return Pessoa.builder()
                .id(DEFAULT_PESSOA_ID)
                .nome(DEFAULT_PESSOA_NOME)
                .telefone(DEFAULT_PESSOA_TELEFONE)
                .idade(DEFAULT_PESSOA_IDADE)
                .cidade(DEFAULT_PESSOA_CIDADE)
                .estado(DEFAULT_PESSOA_ESTADO)
                .regiao(DEFAULT_PESSOA_REGIAO)
                .score(DEFAULT_PESSOA_SCORE)
                .build();
    }

    public static PessoaResponse createPessoaResponse() {
        ModelMapper mapper = new ModelMapper();
        var pessoa = createPessoa();

        pessoa.setEstados(DEFAULT_PESSOA_ESTADOS);
        pessoa.setScoreDescricao(DEFAULT_PESSOA_SCORE_DESCRICAO);

        return mapper.map(pessoa, PessoaResponse.class);
    }

    public static PessoaDTO createPessoaDTO() {
        return new PessoaDTO(DEFAULT_PESSOA_NOME, DEFAULT_PESSOA_TELEFONE, DEFAULT_PESSOA_IDADE,
                DEFAULT_PESSOA_CIDADE, DEFAULT_PESSOA_ESTADO, DEFAULT_PESSOA_SCORE, DEFAULT_PESSOA_REGIAO);
    }
}
