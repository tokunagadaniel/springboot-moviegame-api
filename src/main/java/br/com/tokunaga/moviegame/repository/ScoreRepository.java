package br.com.tokunaga.moviegame.repository;

import br.com.tokunaga.moviegame.entites.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, String> {

}