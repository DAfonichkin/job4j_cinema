package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oTicketRepository implements TicketRepository {

    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var sql = """
                      INSERT INTO tickets(session_id, row_number, place_number, user_id)
                      VALUES (:session_id, :row_number, :place_number, :user_id)
                      """;
            var query = connection.createQuery(sql, true)
                    .addParameter("session_id", ticket.getSessionId())
                    .addParameter("row_number", ticket.getRowNumber())
                    .addParameter("place_number", ticket.getPlaceNumber())
                    .addParameter("user_id", ticket.getUser());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return ticket;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM tickets WHERE id = :id");
            query.addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean update(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE tickets
                    SET session_id = :session_id, row_number = :row_number, place_number = :place_number, user_id = :user_id
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("session_id", ticket.getSessionId())
                    .addParameter("row_number", ticket.getRowNumber())
                    .addParameter("place_number", ticket.getPlaceNumber())
                    .addParameter("user_id", ticket.getUser());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Ticket> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM tickets WHERE id = :id");
            query.addParameter("id", id);
            var ticket = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(ticket);
        }
    }

    @Override
    public Collection<Ticket> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM tickets");
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetch(Ticket.class);
        }
    }

}