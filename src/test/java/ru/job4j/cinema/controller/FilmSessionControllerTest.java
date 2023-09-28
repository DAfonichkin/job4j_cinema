package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmSessionService;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmSessionControllerTest {

    private FilmSessionController filmSessionController;

    private FilmSessionService filmSessionService;

    private final LocalDateTime startTime = LocalDateTime.now();
    private final LocalDateTime endTime = LocalDateTime.now().plusMinutes(60);

    @BeforeEach
    public void initService() {
        filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
    }

    @Test
    void whenRequestFilmSessionsListPageThenGetPageWithFilmSessions() {
        var filmSessionDto1 = new FilmSessionDto(1, "film1", "hall1", 1, 1, startTime, endTime, 1, 1);
        var filmSessionDto2 = new FilmSessionDto(2, "film2", "hall2", 1, 1, startTime, endTime, 1, 1);
        var expectedFilmSessions = List.of(filmSessionDto1, filmSessionDto2);
        when(filmSessionService.getFilmSessionList()).thenReturn(expectedFilmSessions);

        var model = new ConcurrentModel();
        var view = filmSessionController.getFilmList(model);
        var actualFilmSessions = model.getAttribute("filmSessions");

        assertThat(view).isEqualTo("filmSession/list");
        assertThat(actualFilmSessions).isEqualTo(expectedFilmSessions);
    }

}