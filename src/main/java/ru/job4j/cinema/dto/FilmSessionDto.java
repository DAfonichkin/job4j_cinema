package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;

import java.time.LocalDateTime;
import java.util.Objects;

public class FilmSessionDto {

    private int id;

    private String film;

    private String hall;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    public FilmSessionDto() {
    }

    public FilmSessionDto(int id, String film, String hall, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public FilmSessionDto(FilmSession session) {
        this.id = session.getId();
        this.startTime = session.getStartTime();
        this.endTime = session.getEndTime();
        this.price = session.getPrice();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilmSessionDto)) {
            return false;
        }
        FilmSessionDto that = (FilmSessionDto) o;
        return id == that.id && film.equals(that.film) && hall.equals(that.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film, hall);
    }
}
