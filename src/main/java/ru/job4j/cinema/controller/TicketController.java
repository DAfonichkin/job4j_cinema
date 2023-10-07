package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.TicketService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/ticket")
@ThreadSafe
public class TicketController {

    private final FilmSessionService filmSessionService;
    private final TicketService ticketService;

    public TicketController(FilmSessionService filmSessionService, TicketService ticketService) {
        this.filmSessionService = filmSessionService;
        this.ticketService = ticketService;
    }

    @GetMapping("/buy/{id}")
    public String getCreationPage(Model model, @PathVariable int id) {
        var filmSessionOptional = filmSessionService.findById(id);
        if (filmSessionOptional.isEmpty()) {
            model.addAttribute("message", "Сессия с указанным идентификатором не найдена");
            return "errors/404";
        }
        var filmSession = filmSessionOptional.get();
        model.addAttribute("rowList", filmSession.getRowList());
        model.addAttribute("sessionId", id);
        model.addAttribute("placeList", filmSession.getPlaceList());
        model.addAttribute("filmSession", filmSession);
        return "ticket/buy";
    }

    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute Ticket ticket, Model model) {
        Optional<Ticket> savedTicket = ticketService.save(ticket);
        if (savedTicket.isEmpty()) {
            return "ticket/error";
        }
        model.addAttribute("ticket", ticket);
        return "ticket/success";
    }
}
