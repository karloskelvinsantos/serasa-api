package br.com.serasa.controller;

import br.com.serasa.dto.AfinidadeDTO;
import br.com.serasa.service.AfinidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<AfinidadeDTO>> findAll() {
        logger.info("request received for list all afinidade");
        return ResponseEntity.ok(afinidadeService.findAll());
    }
}
