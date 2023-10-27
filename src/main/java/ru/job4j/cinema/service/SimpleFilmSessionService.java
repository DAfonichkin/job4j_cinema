package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.dto.mapper.FilmSessionMapper;
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
    private final FilmSessionMapper filmSessionMapper;

    public SimpleFilmSessionService(FilmSessionRepository filmSessionRepository, FilmRepository filmRepository, HallRepository hallRepository, FilmSessionMapper filmSessionMapper) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
        this.filmSessionMapper = filmSessionMapper;
    }

    @Override
    public Optional<FilmSessionDto> findById(int id) {
        var filmSessionOptional = filmSessionRepository.findById(id);
        if (filmSessionOptional.isEmpty()) {
            return Optional.empty();
        }
        FilmSession filmSession = filmSessionOptional.get();
        var filmOptional = filmRepository.findById(filmSession.getFilmId());
        var hallOptional = hallRepository.findById(filmSession.getHall());
        if (filmOptional.isEmpty() || hallOptional.isEmpty()) {
            return Optional.empty();
        }
        Film film = filmOptional.get();
        Hall hall = hallOptional.get();
        FilmSessionDto filmSessionDto = filmSessionMapper.getFilmSessionDtoFromEntity(filmSession, film, hall);
        return Optional.of(filmSessionDto);
    }

    @Override
    public Collection<FilmSession> findAll() {
        return filmSessionRepository.findAll();
    }

    @Override
    public Collection<FilmSessionDto> getFilmSessionList() {
        List<FilmSessionDto> result = new ArrayList<>();
        for (FilmSession filmSession : filmSessionRepository.findAll()) {
            var filmOptional = filmRepository.findById(filmSession.getFilmId());
            var hallOptional = hallRepository.findById(filmSession.getHall());
            if (filmOptional.isEmpty() || hallOptional.isEmpty()) {
                 continue;
            }
            Film film = filmOptional.get();
            Hall hall = hallOptional.get();
            FilmSessionDto filmSessionDto = filmSessionMapper.getFilmSessionDtoFromEntity(filmSession, film, hall);
            result.add(filmSessionDto);
        }
        return result;
    }

}