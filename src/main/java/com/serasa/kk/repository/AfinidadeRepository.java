package com.serasa.kk.repository;

import com.serasa.kk.model.Afinidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfinidadeRepository extends JpaRepository<Afinidade, Long> {
}
