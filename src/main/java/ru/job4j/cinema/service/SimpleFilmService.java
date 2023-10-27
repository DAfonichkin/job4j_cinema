package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.mapper.FilmMapper;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    private final FilmMapper filmMapper;

    public SimpleFilmService(FilmRepository sql2oFilmRepository, GenreRepository sql2oGenreRepository, FilmMapper filmMapper) {
        this.filmRepository = sql2oFilmRepository;
        this.genreRepository = sql2oGenreRepository;
        this.filmMapper = filmMapper;
    }

     @Override
    public Optional<Film> findById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public Collection<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Collection<FilmDto> getFilmList() {
        List<FilmDto> result = new ArrayList<>();
        for (Film film : filmRepository.findAll()) {
            FilmDto filmDto = filmMapper.getFilmDtoFromEntity(film);
            Optional<Genre> genre = genreRepository.findById(film.getGenreId());
            genre.ifPresent(value -> filmDto.setGenre(value.getName()));
            result.add(filmDto);
        }
        return result;
    }

}