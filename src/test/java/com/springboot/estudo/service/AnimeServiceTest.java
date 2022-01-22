package com.springboot.estudo.service;

import com.springboot.estudo.domain.Anime;
import com.springboot.estudo.exception.BadRequestException;
import com.springboot.estudo.repository.AnimeRepository;
import com.springboot.estudo.util.AnimeCreator;
import com.springboot.estudo.util.AnimePostDtoCreator;
import com.springboot.estudo.util.AnimePutDtoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Tag("unit")
@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepositoryMock.findAll())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn((List.of(AnimeCreator.createValidAnime())));

        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));
    }

    @Test
    @DisplayName("ListAll returns list of anime inside page object when successful")
    void listAllReturnsListOfAnimesInsidePageObjectWhenSuccessful() {

        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }


    @Test
    @DisplayName("listAllNonPageable returns list of anime when successful")
    void listAllNonPageableReturnsListOfAnimesWhenSuccessful() {

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeService.listAllNonPageable();

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
        Anime anime = animeService.findbyId(1L);

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Find by Name returns list of anime when successful")
    void findByNameReturnsListOfAnimeWhenSuccessful() {

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> anime = animeService.findByName(expectedName);

        Assertions.assertThat(anime)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(anime.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Find by id throws BadRequestException when anime is not found")
    void findByIdThrowsBadRequestExceptionWhenAnimeIsNotFound() {
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> animeService.findbyId(1L));
    }

    @Test
    @DisplayName("Find by Name returns list of anime when anime is not found")
    void findByNameReturnsListOfAnimeWhenAnimeIsNotFound() {

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> anime = animeService.findByName("");

        Assertions.assertThat(anime)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Save returns anime when successful")
    void saveReturnsAnimeWhenSuccessful() {

        Anime anime = animeService.save(AnimePostDtoCreator.createAnimePostDto());

        Assertions.assertThat(anime.getName())
                .isNotNull()
                .isEqualTo(AnimeCreator.createValidAnime().getName());
    }

    @Test
    @DisplayName("Replace updates anime when successful")
    void replaceUpdatesAnimeWhenSuccessful() {

        Assertions.assertThatCode(() -> animeService.replace(AnimePutDtoCreator.createAnimePutDto()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete removes anime when successful")
    void deleteRemovesAnimeWhenSuccessful() {

        Assertions.assertThatCode(() -> animeService.delete(1L)).doesNotThrowAnyException();
    }
}