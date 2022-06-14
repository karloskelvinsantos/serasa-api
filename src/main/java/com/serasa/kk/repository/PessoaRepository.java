package com.serasa.kk.repository;

import com.serasa.kk.dto.PessoaDTO;
import com.serasa.kk.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select p from pessoa p where ")
    List<Pessoa> findAllWithEstadosAndScoreDescricao();
}
