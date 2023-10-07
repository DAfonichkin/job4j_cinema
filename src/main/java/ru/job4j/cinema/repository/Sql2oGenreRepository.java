package ru.job4j.cinema.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Repository
public class Sql2oGenreRepository implements GenreRepository {

    private final Sql2o sql2o;
    private static final Logger LOGGER = Logger.getLogger(Sql2oTicketRepository.class);

    public Sql2oGenreRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Genre> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM genres WHERE id = :id");
            var file = query.addParameter("id", id).executeAndFetchFirst(Genre.class);
            return Optional.ofNullable(file);
        } catch (Exception e) {
            LOGGER.error("Exception during finding genre", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM genres WHERE id = :id");
            var affectedRows = query.setColumnMappings(Genre.COLUMN_MAPPING).addParameter("id", id).executeUpdate().getResult();
            return affectedRows > 0;
        } catch (Exception e) {
            LOGGER.error("Exception during deleting genre", e);
            return false;
        }
    }

    @Override
    public Collection<Genre> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM genres");
            return query.setColumnMappings(Genre.COLUMN_MAPPING).executeAndFetch(Genre.class);
        } catch (Exception e) {
            LOGGER.error("Exception during finding all genres", e);
            return Collections.emptyList();
        }
    }


    @Override
    public Optional<Genre> save(Genre genre) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO genres(name) VALUES(:name)", true)
                    .addParameter("name", genre.getName());
            int generatedId = query.setColumnMappings(Genre.COLUMN_MAPPING).executeUpdate().getKey(Integer.class);
            genre.setId(generatedId);
            return Optional.ofNullable(genre);
        } catch (Exception e) {
            LOGGER.error("Exception during saving genre", e);
            return Optional.empty();
        }
    }

}