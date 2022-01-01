package com.springboot.estudo.service;

import com.springboot.estudo.domain.Anime;
import com.springboot.estudo.dto.AnimePostDto;
import com.springboot.estudo.dto.AnimePutDto;
import com.springboot.estudo.exception.BadRequestException;
import com.springboot.estudo.mapper.AnimeMapper;
import com.springboot.estudo.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findbyId(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found!"));
    }

    @Transactional
    public Anime save(AnimePostDto animePostDto) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostDto));
    }

    public void delete(Long id) {
        animeRepository.delete(findbyId(id));
    }

    public void replace(AnimePutDto animePutDto) {
        findbyId(animePutDto.getId());
        animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePutDto));
    }
}
