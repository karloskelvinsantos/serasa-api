package br.com.serasa.service;

import br.com.serasa.dto.PessoaResponse;
import br.com.serasa.exception.PessoaNotFoundException;
import br.com.serasa.model.Pessoa;
import br.com.serasa.repository.PessoaRepository;
import br.com.serasa.util.TestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.serasa.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    private PessoaService pessoaService;


    @Mock
    private ModelMapper mapper;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private AfinidadeService afinidadeService;
    @Mock
    private ScoreService scoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaService = new PessoaService(mapper, pessoaRepository, afinidadeService, scoreService);
    }

    @Test
    void deveRetornarPessoaAoExecutarPostComDadosCorretos() {
        var pessoa = TestDataCreator.createPessoa();
        var pessoaDto = TestDataCreator.createPessoaDTO();

        when(mapper.map(pessoaDto, Pessoa.class)).thenReturn(pessoa);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        var pessoaCreated = pessoaService.create(pessoaDto);

        assertEquals(pessoaCreated.getNome(), pessoaDto.getNome());
        assertEquals(pessoaCreated.getCidade(), pessoaDto.getCidade());
        assertEquals(pessoaCreated.getEstado(), pessoaDto.getEstado());
        assertEquals(pessoaCreated.getRegiao(), pessoaDto.getRegiao());
        assertEquals(pessoaCreated.getScore(), pessoaDto.getScore());
        assertEquals(pessoaCreated.getTelefone(), pessoaDto.getTelefone());
        assertEquals(pessoaCreated.getIdade(), pessoaDto.getIdade());
    }

    @Test
    void deveRetornarTodasAsPessoas() {
        var pessoa = TestDataCreator.createPessoa();
        var listPessoas = Collections.singletonList(pessoa);

        when(pessoaRepository.findAll()).thenReturn(listPessoas);
        when(afinidadeService.getEstadosByRegiao(pessoa.getRegiao().toString())).thenReturn(DEFAULT_PESSOA_ESTADOS);
        when(scoreService.findDescriptionByScore(pessoa.getScore())).thenReturn(DEFAULT_PESSOA_SCORE_DESCRICAO);
        when(mapper.map(pessoa, PessoaResponse.class)).thenReturn(TestDataCreator.createPessoaResponse());

        var pessoasResponse = pessoaService.findAll();

        assertFalse(pessoasResponse.isEmpty());
        assertEquals(1L, pessoasResponse.size());
    }

    @Test
    void deveRetornarPessoaReferenteAoIdOne() {
        var pessoa = TestDataCreator.createPessoa();
        when(pessoaRepository.findById(DEFAULT_PESSOA_ID)).thenReturn(Optional.ofNullable(pessoa));
        when(afinidadeService.getEstadosByRegiao(pessoa.getRegiao().toString())).thenReturn(DEFAULT_PESSOA_ESTADOS);
        when(scoreService.findDescriptionByScore(pessoa.getScore())).thenReturn(DEFAULT_PESSOA_SCORE_DESCRICAO);
        when(mapper.map(pessoa, PessoaResponse.class)).thenReturn(TestDataCreator.createPessoaResponse());

        var pessoaResponse = pessoaService.findOne(DEFAULT_PESSOA_ID);

        assertEquals(pessoa.getNome(), pessoaResponse.getNome());
        assertEquals(pessoa.getCidade(), pessoaResponse.getCidade());
        assertEquals(pessoa.getEstado(), pessoaResponse.getEstado());
        assertEquals(DEFAULT_PESSOA_SCORE_DESCRICAO, pessoaResponse.getScoreDescricao());
        assertEquals(DEFAULT_PESSOA_ESTADOS, pessoaResponse.getEstados());
    }

    @Test
    void deveRetornarExceptionQuandoBuscarPessoasENaoExistirNenhumaCadastrada() {
        when(pessoaRepository.findAll()).thenReturn(List.of());

        assertThrows(PessoaNotFoundException.class, () -> pessoaService.findAll());
    }

    @Test
    void deveRetornarExceptionPessoaNaoEncontradaQuandoBuscarPessoaPorIdNaoExistente() {
        when(pessoaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(PessoaNotFoundException.class, () -> pessoaService.findOne(2L));
    }
}