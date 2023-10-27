package ru.job4j.cinema.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.dto.mapper.FileMapper;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimpleFileService implements FileService {

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    private final String storageDirectory;

    public SimpleFileService(FileRepository sql2oFileRepository,
                             FileMapper fileMapper, @Value("${file.directory}") String storageDirectory) {
        this.fileRepository = sql2oFileRepository;
        this.fileMapper = fileMapper;
        this.storageDirectory = storageDirectory;
        createStorageDirectory(storageDirectory);
    }

    private void createStorageDirectory(String path) {
        try {
            Files.createDirectories(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<File> save(FileDto fileDto) {
        var path = getNewFilePath(fileDto.getName());
        writeFileBytes(path, fileDto.getContent());
        return fileRepository.save(new File(fileDto.getName(), path));
    }

    private String getNewFilePath(String sourceName) {
        return storageDirectory + java.io.File.separator + UUID.randomUUID() + sourceName;
    }

    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<FileDto> getFileById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(fileMapper.getDtoFromEntity(fileOptional.get()));
    }


    @Override
    public boolean deleteById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        deleteFile(fileOptional.get().getPath());
        return fileRepository.deleteById(id);
    }

    private void deleteFile(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}