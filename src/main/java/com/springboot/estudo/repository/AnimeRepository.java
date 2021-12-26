package com.springboot.estudo.repository;

import com.springboot.estudo.domain.Anime;

import java.util.List;

public interface AnimeRepository {

    List<Anime> listAll();
}
