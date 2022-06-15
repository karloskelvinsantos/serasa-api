package br.com.serasa.repository;

import br.com.serasa.model.Afinidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfinidadeRepository extends JpaRepository<Afinidade, Long> {

    Afinidade findByRegiaoEqualsIgnoreCase(String regiao);
}
