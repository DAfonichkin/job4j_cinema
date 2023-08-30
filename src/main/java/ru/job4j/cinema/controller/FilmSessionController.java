package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.service.FilmSessionService;


@Controller
@RequestMapping("/sessions")
@ThreadSafe
public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/list")
    public String getFilmList(Model model) {
        model.addAttribute("filmSessions", filmSessionService.getFilmSessionList());
        return "filmSession/list";
    }


}
