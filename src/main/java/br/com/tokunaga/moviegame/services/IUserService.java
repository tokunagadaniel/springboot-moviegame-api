package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.User;

public interface IUserService {
    User find(String name);

    boolean login(String name, String passwd);

    void create(String name, String passwd);
}
