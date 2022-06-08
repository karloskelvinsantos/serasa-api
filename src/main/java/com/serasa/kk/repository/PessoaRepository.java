package com.serasa.kk.repository;

import com.serasa.kk.dto.PessoaDTO;
import com.serasa.kk.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Page<Pessoa> findAllProjectBy(Pageable pageable);
}
