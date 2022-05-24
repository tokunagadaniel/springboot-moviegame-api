package br.com.tokunaga.moviegame.api.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "movies-app1", url = "https://movies-app1.p.rapidapi.com/api")
public interface RankingMoviesApi {

    @RequestMapping(method = RequestMethod.GET, value = "/movies", headers = {"X-RapidAPI-Host=movies-app1.p.rapidapi.com", "X-RapidAPI-Key=890ded48bbmsh58ec3bfa7ba8810p19bdbbjsn6de36a11eb07"})
    JsonNode getRakingMovies();
}