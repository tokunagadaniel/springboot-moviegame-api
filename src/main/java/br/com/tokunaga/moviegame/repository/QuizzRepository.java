package br.com.tokunaga.moviegame.repository;

import br.com.tokunaga.moviegame.entites.Movie;
import br.com.tokunaga.moviegame.entites.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzRepository extends JpaRepository<Quizz, Long> {
}