package ru.job4j.cinema.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Hall;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Repository
public class Sql2oHallRepository implements HallRepository {

    private final Sql2o sql2o;
    private static final Logger LOGGER = Logger.getLogger(Sql2oTicketRepository.class);

    public Sql2oHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Hall> save(Hall hall) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO halls(name, description, row_count, place_count)
                    VALUES (:name, :description, :row_count, :place_count)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("name", hall.getName())
                    .addParameter("description", hall.getDescription())
                    .addParameter("row_count", hall.getRowCount())
                    .addParameter("place_count", hall.getPlaceCount());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            hall.setId(generatedId);
            return Optional.ofNullable(hall);
        } catch (Exception e) {
            LOGGER.error("Exception during saving hall", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM halls WHERE id = :id");
            query.addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        } catch (Exception e) {
            LOGGER.error("Exception during deleting hall", e);
            return false;
        }
    }

    @Override
    public boolean update(Hall hall) {
        try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE halls
                    SET name = :name, description = :description, row_count = :row_count, place_count = :place_count
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("name", hall.getName())
                    .addParameter("description", hall.getDescription())
                    .addParameter("row_count", hall.getRowCount())
                    .addParameter("place_count", hall.getPlaceCount());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        } catch (Exception e) {
            LOGGER.error("Exception during updating hall", e);
            return false;
        }
    }

    @Override
    public Optional<Hall> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls WHERE id = :id");
            query.addParameter("id", id);
            var hall = query.setColumnMappings(Hall.COLUMN_MAPPING).executeAndFetchFirst(Hall.class);
            return Optional.ofNullable(hall);
        } catch (Exception e) {
            LOGGER.error("Exception during finding hall", e);
            return Optional.empty();
        }
    }

    @Override
    public Collection<Hall> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls");
            return query.setColumnMappings(Hall.COLUMN_MAPPING).executeAndFetch(Hall.class);
        } catch (Exception e) {
            LOGGER.error("Exception during finding all halls", e);
            return Collections.emptyList();
        }
    }

}