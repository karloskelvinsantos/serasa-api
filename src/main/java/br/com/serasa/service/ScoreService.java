package br.com.serasa.service;

import br.com.serasa.dto.ScoreDTO;
import br.com.serasa.model.Score;
import br.com.serasa.repository.ScoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScoreService {

    private final ModelMapper mapper;
    private final ScoreRepository scoreRepository;

    public ScoreService(ModelMapper mapper, ScoreRepository scoreRepository) {
        this.mapper = mapper;
        this.scoreRepository = scoreRepository;
    }

    public Score create(ScoreDTO scoreDTO) {
        var score = mapper.map(scoreDTO, Score.class);
        return scoreRepository.save(score);
    }

    public String findDescriptionByScore(Integer score) {
        return scoreRepository.findDescriptionByRangeScore(score);
    }
}
