package com.springboot.estudo.controller;

import com.springboot.estudo.domain.Anime;
import com.springboot.estudo.dto.AnimePostDto;
import com.springboot.estudo.service.AnimeService;
import com.springboot.estudo.util.AnimeCreator;
import com.springboot.estudo.util.AnimePostDtoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMock.listAllNonPageable())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.findbyId(ArgumentMatchers.any()))
                .thenReturn((AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn((List.of(AnimeCreator.createValidAnime())));

        BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimePostDto.class)))
                .thenReturn(AnimeCreator.createValidAnime());
    }

    @Test
    @DisplayName("List returns list of anime inside page object when successful")
    void listReturnsListOfAnimesInsidePageObjectWhenSuccessful() {

        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }


    @Test
    @DisplayName("ListAll returns list of anime when successful")
    void listAllReturnsListOfAnimesWhenSuccessful() {

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeController.listAll().getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }


    @Test
    @DisplayName("Find by ID returns anime when successful")
    void findByIdReturnsAnimeWhenSuccessful() {

        Long expectedId = AnimeCreator.createValidAnime().getId();
        Anime anime = animeController.findById(1L).getBody();

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Find by Name returns list of anime when successful")
    void findByNameReturnsListOfAnimeWhenSuccessful() {

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> anime = animeController.findByName(expectedName).getBody();

        Assertions.assertThat(anime)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(anime.get(0).getName()).isEqualTo(expectedName);
    }



    @Test
    @DisplayName("Find by Name returns list of anime when anime is not found")
    void findByNameReturnsListOfAnimeWhenAnimeIsNotFound() {

        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> anime = animeController.findByName("").getBody();

        Assertions.assertThat(anime)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Save returns anime when successful")
    void saveReturnsAnimeWhenSuccessful() {

        Anime anime = animeController.save(AnimePostDtoCreator.createAnimePostDto()).getBody();

        Assertions.assertThat(anime.getName())
                .isNotNull()
                .isEqualTo(AnimeCreator.createValidAnime().getName());
    }

}