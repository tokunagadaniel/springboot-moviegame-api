package br.com.tokunaga.moviegame;

import br.com.tokunaga.moviegame.services.IRankingMoviesService;
import br.com.tokunaga.moviegame.services.IUserService;
import br.com.tokunaga.moviegame.services.RankingMoviesService;
import br.com.tokunaga.moviegame.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@EnableFeignClients
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        IRankingMoviesService rankingSvc = context.getBean(RankingMoviesService.class);
        rankingSvc.load();

        IUserService userSvc = context.getBean(UserService.class);
        userSvc.create("daniel", "123");
        userSvc.create("tokunaga", "123");
    }
}

