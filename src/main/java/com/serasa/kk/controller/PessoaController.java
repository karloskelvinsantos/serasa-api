package com.serasa.kk.controller;

import com.serasa.kk.dto.PessoaDTO;
import com.serasa.kk.dto.PessoaResponse;
import com.serasa.kk.model.Pessoa;
import com.serasa.kk.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final Logger logger = LoggerFactory.getLogger(PessoaController.class);
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTO pessoaDTO, UriComponentsBuilder uriComponentsBuilder) {
        logger.info("request received for create person: {}", pessoaDTO);
        var saved = pessoaService.create(pessoaDTO);
        var uri = uriComponentsBuilder.path("/pessoa/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> getAll() {
        logger.info("request received for get all persons");
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> getOne(@PathVariable Long id) {
        logger.info("request received for get person with id: {}", id);
        var response = pessoaService.findOne(id);
        return ResponseEntity.ok(response);
    }
}
