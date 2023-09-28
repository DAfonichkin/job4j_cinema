package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oGenreRepository implements GenreRepository {

    private final Sql2o sql2o;

    public Sql2oGenreRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Genre> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM genres WHERE id = :id");
            var file = query.addParameter("id", id).executeAndFetchFirst(Genre.class);
            return Optional.ofNullable(file);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM genres WHERE id = :id");
            var affectedRows = query.setColumnMappings(Genre.COLUMN_MAPPING).addParameter("id", id).executeUpdate().getResult();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Collection<Genre> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM genres");
            return query.setColumnMappings(Genre.COLUMN_MAPPING).executeAndFetch(Genre.class);
        }
    }


    @Override
    public Genre save(Genre genre) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO genres(name) VALUES(:name)", true)
                    .addParameter("name", genre.getName());
            int generatedId = query.setColumnMappings(Genre.COLUMN_MAPPING).executeUpdate().getKey(Integer.class);
            genre.setId(generatedId);
            return genre;
        }
    }

}