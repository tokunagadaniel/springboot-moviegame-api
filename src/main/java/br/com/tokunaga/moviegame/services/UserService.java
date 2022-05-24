package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.User;
import br.com.tokunaga.moviegame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;

    @Override
    public User find(String name) {
        Optional<User> user = repository.findById(name);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public boolean login(String name, String passwd) {
        Optional<User> user = repository.findById(name);

        if (user.isPresent()) {
            String secret = new String(Base64.getDecoder().decode(user.get().getPasswd().getBytes()));
            return secret.equals(passwd);
        }

        throw new RuntimeException();
    }

    @Override
    public void create(String name, String passwd) {
        repository.save(
                User.builder()
                        .name(name)
                        .passwd(Base64.getEncoder().encodeToString(passwd.getBytes()))
                        .build()
        );
    }
}
