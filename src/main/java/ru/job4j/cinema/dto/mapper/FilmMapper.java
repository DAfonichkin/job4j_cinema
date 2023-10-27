package ru.job4j.cinema.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;

@Mapper(componentModel = "spring")
public interface FilmMapper {

   FilmDto getFilmDtoFromEntity(Film film);

    Film getFilmEntityFromDto(FilmDto filmDto);

}
