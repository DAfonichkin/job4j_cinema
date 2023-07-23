package ru.job4j.cinema.model;

import java.util.Objects;

public class Ticket {

    private int id;

    private FilmSession filmSession;

    private int rowNumber;

    private int placeNumber;

    private User user;

    public Ticket() {
    }

    public Ticket(int id, FilmSession filmSession, int rowNumber, int placeNumber, User user) {
        this.id = id;
        this.filmSession = filmSession;
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FilmSession getFilmSession() {
        return filmSession;
    }

    public void setFilmSession(FilmSession filmSession) {
        this.filmSession = filmSession;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return id == ticket.id && rowNumber == ticket.rowNumber && placeNumber == ticket.placeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rowNumber, placeNumber);
    }
}
