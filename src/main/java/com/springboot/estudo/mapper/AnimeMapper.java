package com.springboot.estudo.mapper;

import com.springboot.estudo.domain.Anime;
import com.springboot.estudo.dto.AnimePostDto;
import com.springboot.estudo.dto.AnimePutDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimePostDto animePostDto);
    public abstract Anime toAnime(AnimePutDto animePutDto);
}
