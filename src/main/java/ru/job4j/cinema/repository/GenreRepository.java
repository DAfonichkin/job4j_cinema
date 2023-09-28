package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Genre;

import java.util.Collection;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(int id);

    boolean deleteById(int id);

    Collection<Genre> findAll();

    Genre save(Genre genre);

}