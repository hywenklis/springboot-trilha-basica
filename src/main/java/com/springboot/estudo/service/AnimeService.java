package com.springboot.estudo.service;

import com.springboot.estudo.domain.Anime;
import com.springboot.estudo.dto.AnimePostDto;
import com.springboot.estudo.dto.AnimePutDto;
import com.springboot.estudo.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final List<Anime> animes;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findbyId(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found!"));
    }

    public Anime save(AnimePostDto animePostDto) {
        return animeRepository.save(Anime.builder().name(animePostDto.getName()).build());
    }

    public void delete(Long id) {
        animeRepository.delete(findbyId(id));
    }

    public void replace(AnimePutDto animePutDto) {
        findbyId(animePutDto.getId());
        Anime anime = Anime.builder()
                .id(animePutDto.getId())
                .name(animePutDto.getName())
                .build();
        animeRepository.save(anime);
    }
}
