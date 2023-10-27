package ru.job4j.cinema.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;

@Mapper(componentModel = "spring")
public interface FilmSessionMapper {

    @Mapping(source = "filmSession.id", target = "id")
    @Mapping(source = "hall.name", target = "hall")
    @Mapping(source = "film.name", target = "film")
    FilmSessionDto getFilmSessionDtoFromEntity(FilmSession filmSession, Film film, Hall hall);

}
