package br.com.tokunaga.moviegame.repository;

import br.com.tokunaga.moviegame.entites.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {

    @Query(value = "SELECT * FROM MATCH WHERE USER_NAME = ?1", nativeQuery = true)
    List<Match> findByUser(String name);
    @Query(value = "SELECT * FROM MATCH WHERE USER_NAME = ?1 AND STATUS = ?2", nativeQuery = true)
    Match findByUserAndStatus(String name, Integer status);
}