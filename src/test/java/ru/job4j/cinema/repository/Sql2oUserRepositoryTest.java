package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.User;

import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");
        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);
        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void clearUsers() {
        var users = sql2oUserRepository.findAll();
        for (var user : users) {
            sql2oUserRepository.deleteById(user.getId());
        }
    }


    @Test
    void saveWithUniqueEmail() {
        var user1 = sql2oUserRepository.save(new User(0, "1", "1", "1")).get();
        var user2 = sql2oUserRepository.save(new User(0, "2", "2", "2")).get();
        var result = sql2oUserRepository.findAll();
        assertThat(result).isEqualTo(List.of(user1, user2));
    }

    @Test
    void saveWithNotUniqueEmail() {
        var user1 = sql2oUserRepository.save(new User(0, "1", "1", "1"));
        var user2 = sql2oUserRepository.save(new User(1, "1", "1", "1"));
        assertThat(user2.isEmpty()).isTrue();
    }

    @Test
    void findByEmailAndPasswordAndWasFind() {
        var user1 = sql2oUserRepository.save(new User(0, "1", "1", "1"));
        var user2 = sql2oUserRepository.save(new User(1, "2", "2", "2"));
        var result = sql2oUserRepository.findByEmailAndPassword("2", "2");
        assertThat(result).isEqualTo(user2);
    }

    @Test
    void findByEmailAndPasswordAndWasNotFind() {
        var user1 = sql2oUserRepository.save(new User(0, "1", "1", "1"));
        var result = sql2oUserRepository.findByEmailAndPassword("2", "2");
        assertThat(result.isEmpty()).isTrue();
    }
}