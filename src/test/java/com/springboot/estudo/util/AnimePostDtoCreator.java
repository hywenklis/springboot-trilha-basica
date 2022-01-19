package com.springboot.estudo.util;

import com.springboot.estudo.dto.AnimePostDto;

public class AnimePostDtoCreator {

    public static AnimePostDto createAnimePostDto() {
        return AnimePostDto.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }
}
