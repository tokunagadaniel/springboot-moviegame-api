package br.com.tokunaga.moviegame.repository;

import br.com.tokunaga.moviegame.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}