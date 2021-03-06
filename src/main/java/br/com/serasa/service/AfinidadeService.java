package br.com.serasa.service;

import br.com.serasa.dto.AfinidadeDTO;
import br.com.serasa.model.Afinidade;
import br.com.serasa.repository.AfinidadeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getEstadosByRegiao(String regiao) {
        var afinidade = afinidadeRepository.findByRegiaoEqualsIgnoreCase(regiao);
        return afinidade.getEstados();
    }

    public List<AfinidadeDTO> findAll() {
        return afinidadeRepository.findAll()
                .stream()
                .map(afinidade -> mapper.map(afinidade, AfinidadeDTO.class))
                .collect(Collectors.toList());
    }
}
