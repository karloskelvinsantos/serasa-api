package com.serasa.kk.controller;

import com.serasa.kk.dto.AfinidadeDTO;
import com.serasa.kk.service.AfinidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("afinidade")
public class AfinidadeController {

    private final Logger logger = LoggerFactory.getLogger(AfinidadeController.class);
    private final AfinidadeService afinidadeService;

    public AfinidadeController(AfinidadeService afinidadeService) {
        this.afinidadeService = afinidadeService;
    }

    @PostMapping
    public ResponseEntity<AfinidadeDTO> create(@Valid @RequestBody AfinidadeDTO afinidadeDTO, UriComponentsBuilder uriComponentsBuilder) {
        logger.info("request received for create afinity: {}", afinidadeDTO);
        var saved = afinidadeService.create(afinidadeDTO);
        var uri = uriComponentsBuilder.path("/afinidade/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
