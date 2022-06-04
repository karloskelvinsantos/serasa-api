package com.serasa.kk.service;

import com.serasa.kk.dto.AfinidadeDTO;
import com.serasa.kk.model.Afinidade;
import com.serasa.kk.repository.AfinidadeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AfinidadeService {

    private final ModelMapper mapper;
    private final AfinidadeRepository afinidadeRepository;

    public AfinidadeService(ModelMapper mapper, AfinidadeRepository afinidadeRepository) {
        this.mapper = mapper;
        this.afinidadeRepository = afinidadeRepository;
    }

    public Afinidade create(AfinidadeDTO afinidadeDTO) {
        var afinidade = mapper.map(afinidadeDTO, Afinidade.class);
        return afinidadeRepository.save(afinidade);
    }
}
