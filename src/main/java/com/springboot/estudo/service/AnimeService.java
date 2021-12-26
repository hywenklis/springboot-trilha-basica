package com.springboot.estudo.service;

import com.springboot.estudo.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {

    private final List<Anime> animes = List.of(new Anime(1L,"Naruto"), new Anime(2L,"Berserk"));

    public List<Anime> listAll() {
        return animes;
    }

    public Anime findbyId(Long id) {
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found!"));
    }
}
