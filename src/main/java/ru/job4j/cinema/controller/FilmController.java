package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.service.FilmService;


@Controller
@RequestMapping("/film")
@ThreadSafe
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/list")
    public String getFilmList(Model model) {
        model.addAttribute("films", filmService.getFilmList());
        return "film/list";
    }

}
