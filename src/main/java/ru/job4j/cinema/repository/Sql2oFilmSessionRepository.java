package ru.job4j.cinema.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    private final Sql2o sql2o;
    private static final Logger LOGGER = Logger.getLogger(Sql2oTicketRepository.class);

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<FilmSession> save(FilmSession filmSession) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO film_sessions(film_id, halls_id, start_time, end_time, price)
                    VALUES (:film_id, :halls_id, :start_time, :end_time, :price)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("film_id", filmSession.getFilmId())
                    .addParameter("halls_id", filmSession.getHall())
                    .addParameter("start_time", filmSession.getStartTime())
                    .addParameter("end_time", filmSession.getEndTime())
                    .addParameter("price", filmSession.getPrice());
            int generatedId = query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeUpdate().getKey(Integer.class);
            filmSession.setId(generatedId);
            return Optional.ofNullable(filmSession);
        } catch (Exception e) {
            LOGGER.error("Exception during saving film session", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
            var affectedRows = query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeUpdate().getResult();
            return affectedRows > 0;
        } catch (Exception e) {
            LOGGER.error("Exception during deleting film session", e);
            return false;
        }
    }

    @Override
    public boolean update(FilmSession filmSession) {
        try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE film_sessions
                    SET film_id = :film_id, halls_id = :halls_id, start_time = :start_time,
                        end_time = :end_time, price = :price
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("film_id", filmSession.getFilmId())
                    .addParameter("halls_id", filmSession.getHall())
                    .addParameter("start_time", filmSession.getStartTime())
                    .addParameter("end_time", filmSession.getEndTime())
                    .addParameter("price", filmSession.getPrice());
            var affectedRows = query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeUpdate().getResult();
            return affectedRows > 0;
        } catch (Exception e) {
            LOGGER.error("Exception during updating film session", e);
            return false;
        }
    }

    @Override
    public Optional<FilmSession> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
            var filmSession = query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeAndFetchFirst(FilmSession.class);
            return Optional.ofNullable(filmSession);
        } catch (Exception e) {
            LOGGER.error("Exception during finding film session", e);
            return Optional.empty();
        }
    }

    @Override
    public Collection<FilmSession> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions");
            return query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeAndFetch(FilmSession.class);
        } catch (Exception e) {
            LOGGER.error("Exception during finding film session", e);
            return Collections.emptyList();
        }
    }

}