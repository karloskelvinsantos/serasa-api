package com.serasa.kk.service;

import com.serasa.kk.dto.PessoaDTO;
import com.serasa.kk.dto.PessoaResponse;
import com.serasa.kk.exception.PessoaNotFoundException;
import com.serasa.kk.model.Pessoa;
import com.serasa.kk.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found";

    private final ModelMapper mapper;
    private final PessoaRepository pessoaRepository;
    private final AfinidadeService afinidadeService;
    private final ScoreService scoreService;

    public PessoaService(ModelMapper mapper, PessoaRepository pessoaRepository, AfinidadeService afinidadeService, ScoreService scoreService) {
        this.mapper = mapper;
        this.pessoaRepository = pessoaRepository;
        this.afinidadeService = afinidadeService;
        this.scoreService = scoreService;
    }

    public Pessoa create(PessoaDTO pessoaDTO) {
        var pessoa = mapper.map(pessoaDTO, Pessoa.class);
        return pessoaRepository.save(pessoa);
    }

    public Page<PessoaResponse> findAll(Pageable pageable) {
        var page = pessoaRepository.findAllProjectBy(pageable);
        page.stream().forEach(pessoa -> {
            pessoa.setEstados(afinidadeService.getEstadosByRegiao(pessoa.getRegiao().toString()));
        });

        return page.map(pessoa -> mapper.map(pessoa, PessoaResponse.class));
    }

    public PessoaResponse findOne(Long id) {
        var pessoaFinded = pessoaRepository.findById(id).orElseThrow(() -> new PessoaNotFoundException(MESSAGE_PERSON_NOT_FOUND));
        pessoaFinded.setEstados(afinidadeService.getEstadosByRegiao(pessoaFinded.getRegiao().toString()));
        var pessoaResponse = mapper.map(pessoaFinded, PessoaResponse.class);
        pessoaResponse.setScoreDescricao(scoreService.findDescriptionByScore(pessoaFinded.getScore()));
        return pessoaResponse;
    }
}
