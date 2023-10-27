package ru.job4j.cinema.dto.mapper;

import org.mapstruct.Mapper;
import ru.job4j.cinema.dto.FileDto;

import ru.job4j.cinema.model.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Mapper(componentModel = "spring")
public interface FileMapper {

    default FileDto getDtoFromEntity(File file) {
        try {
            return new FileDto(file.getName(), Files.readAllBytes(Path.of(file.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
