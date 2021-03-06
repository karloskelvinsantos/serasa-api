package br.com.serasa.repository;

import br.com.serasa.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query("select s.scoreDescricao from Score s where :score >= s.inicial and :score <= s.scoreFinal")
    String findDescriptionByRangeScore(int score);
}
