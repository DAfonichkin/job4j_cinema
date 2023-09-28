package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.FilmService;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

class FilmControllerTest {

    private FilmService filmService;

    private FilmController filmController;

    @BeforeEach
    public void initService() {
        filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
    }

    @Test
    void whenRequestFilmListPageThenGetFilmList() {
        var filmDto1 = new FilmDto(1, "name1", "description1", 1, 1, 1, "genre1");
        var filmDto2 = new FilmDto(2, "name2", "description2", 1, 1, 1, "genre2");
        var expectedFilms = List.of(filmDto1, filmDto2);
        when(filmService.getFilmList()).thenReturn(expectedFilms);

        var model = new ConcurrentModel();
        var view = filmController.getFilmList(model);
        var actualFilms = model.getAttribute("films");
        assertThat(view).isEqualTo("film/list");
        assertThat(actualFilms).isEqualTo(expectedFilms);
    }
}