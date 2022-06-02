package com.serasa.kk.controller;

import com.serasa.kk.dto.PessoaDTO;
import com.serasa.kk.model.Pessoa;
import com.serasa.kk.service.PessoaService;
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
@RequestMapping("/pessoa")
public class PessoaController {

    private final Logger logger = LoggerFactory.getLogger(PessoaController.class);
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody @Valid PessoaDTO pessoaDTO, UriComponentsBuilder uriComponentsBuilder) {
        logger.info("request received for create person: {}", pessoaDTO);
        var saved = pessoaService.create(pessoaDTO);
        var uri = uriComponentsBuilder.path("/pessoa/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
