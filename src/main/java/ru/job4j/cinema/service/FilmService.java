package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.dto.FilmDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmService {

    Optional<Film> findById(int id);

    Collection<Film> findAll();

    Collection<FilmDto> getFilmList();

}