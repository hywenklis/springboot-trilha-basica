package com.springboot.estudo.util;

import com.springboot.estudo.dto.AnimePutDto;

public class AnimePutDtoCreator {

    public static AnimePutDto createAnimePutDto() {
        return AnimePutDto.builder()
                .id(AnimeCreator.createValidUpdatedAnime().getId())
                .name(AnimeCreator.createValidUpdatedAnime().getName())
                .build();
    }
}
