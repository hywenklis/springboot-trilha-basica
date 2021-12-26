package com.springboot.estudo.service;

import com.springboot.estudo.domain.Anime;
import com.springboot.estudo.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    public List<Anime> listAll() {
        return List.of(new Anime(1L,"Naruto"), new Anime(2L,"Berserk"));
    }
}
