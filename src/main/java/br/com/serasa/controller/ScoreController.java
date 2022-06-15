package br.com.serasa.controller;

import br.com.serasa.dto.ScoreDTO;
import br.com.serasa.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public ResponseEntity<ScoreDTO> create(@RequestBody @Valid ScoreDTO scoreDTO, UriComponentsBuilder uriComponentsBuilder) {
        logger.info("request received for create score: {}", scoreDTO);
        var saved = scoreService.create(scoreDTO);
        var uri = uriComponentsBuilder.path("/score/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
