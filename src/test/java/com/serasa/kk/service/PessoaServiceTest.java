package com.serasa.kk.service;

import com.serasa.kk.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

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
    void create() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}