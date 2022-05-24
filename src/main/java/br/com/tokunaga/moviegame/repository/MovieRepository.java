package br.com.tokunaga.moviegame.repository;

import br.com.tokunaga.moviegame.entites.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}