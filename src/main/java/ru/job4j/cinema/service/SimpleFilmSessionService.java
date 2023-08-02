package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.HallRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    public SimpleFilmSessionService(FilmSessionRepository filmSessionRepository, FilmRepository filmRepository, HallRepository hallRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public Optional<FilmSession> findById(int id) {
        return filmSessionRepository.findById(id);
    }

    @Override
    public Collection<FilmSession> findAll() {
        return filmSessionRepository.findAll();
    }

    @Override
    public Collection<FilmSessionDto> getFilmSessionList() {
        List<FilmSessionDto> result = new ArrayList<>();
        for (FilmSession filmSession : filmSessionRepository.findAll()) {
            FilmSessionDto filmSessionDto = new FilmSessionDto(filmSession);
            Optional<Film> film = filmRepository.findById(filmSession.getFilmId());
            film.ifPresent(value -> filmSessionDto.setFilm(value.getName()));
            Optional<Hall> hall = hallRepository.findById(filmSession.getHall());
            hall.ifPresent(value -> filmSessionDto.setHall(value.getName()));
            result.add(filmSessionDto);
        }
        return result;
    }

}