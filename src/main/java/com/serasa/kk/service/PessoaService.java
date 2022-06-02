package com.serasa.kk.service;

import com.serasa.kk.dto.PessoaDTO;
import com.serasa.kk.model.Pessoa;
import com.serasa.kk.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final ModelMapper mapper;
    private final PessoaRepository pessoaRepository;

    public PessoaService(ModelMapper mapper, PessoaRepository pessoaRepository) {
        this.mapper = mapper;
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa create(PessoaDTO pessoaDTO) {
        var pessoa = mapper.map(pessoaDTO, Pessoa.class);
        return pessoaRepository.save(pessoa);
    }
}
