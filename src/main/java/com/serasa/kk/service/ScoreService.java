package com.serasa.kk.service;

import com.serasa.kk.dto.ScoreDTO;
import com.serasa.kk.model.Score;
import com.serasa.kk.repository.ScoreRepository;
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
        var scoreDescription = scoreRepository.findDescriptionByRangeScore(score);
        return scoreDescription;
    }
}
