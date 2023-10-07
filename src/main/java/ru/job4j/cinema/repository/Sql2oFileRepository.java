package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.File;
import org.apache.log4j.Logger;

import java.util.Optional;

@Repository
public class Sql2oFileRepository implements FileRepository {

    private final Sql2o sql2o;
    private static final Logger LOGGER = Logger.getLogger(Sql2oTicketRepository.class);

    public Sql2oFileRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<File> save(File file) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO files (name, path) VALUES (:name, :path)", true)
                    .addParameter("name", file.getName())
                    .addParameter("path", file.getPath());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            file.setId(generatedId);
            return Optional.of(file);
        } catch (Exception e) {
            LOGGER.error("Exception during saving file", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<File> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files WHERE id = :id");
            var file = query.addParameter("id", id).executeAndFetchFirst(File.class);
            return Optional.ofNullable(file);
        } catch (Exception e) {
            LOGGER.error("Exception finding file", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM files WHERE id = :id");
            var affectedRows = query.addParameter("id", id).executeUpdate().getResult();
            return affectedRows > 0;
        } catch (Exception e) {
            LOGGER.error("Exception during deleting file", e);
            return false;
        }
    }

}