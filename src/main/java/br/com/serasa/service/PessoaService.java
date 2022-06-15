package br.com.serasa.service;

import br.com.serasa.dto.PessoaDTO;
import br.com.serasa.model.Pessoa;
import br.com.serasa.dto.PessoaResponse;
import br.com.serasa.exception.PessoaNotFoundException;
import br.com.serasa.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PessoaResponse> findAll() {
        var pessoas = pessoaRepository.findAll();
        pessoas.forEach(pessoa -> {
            pessoa.setEstados(afinidadeService.getEstadosByRegiao(pessoa.getRegiao().toString()));
            pessoa.setScoreDescricao(scoreService.findDescriptionByScore(pessoa.getScore()));
        });

        return pessoas.stream().map(pessoa -> mapper.map(pessoa, PessoaResponse.class)).collect(Collectors.toList());
    }

    public PessoaResponse findOne(Long id) {
        var pessoaFinded = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(MESSAGE_PERSON_NOT_FOUND));

        pessoaFinded.setEstados(afinidadeService.getEstadosByRegiao(pessoaFinded.getRegiao().toString()));
        pessoaFinded.setScoreDescricao(scoreService.findDescriptionByScore(pessoaFinded.getScore()));
        return mapper.map(pessoaFinded, PessoaResponse.class);
    }
}
