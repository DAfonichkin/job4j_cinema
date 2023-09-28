package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.TicketService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

class TicketControllerTest {

    private TicketController ticketController;
    private FilmSessionService filmSessionService;
    private TicketService ticketService;

    @BeforeEach
    public void initService() {
        ticketService = mock(TicketService.class);
        filmSessionService = mock(FilmSessionService.class);
        ticketController = new TicketController(filmSessionService, ticketService);
    }

    @Test
    void whenBuyTicketThenGetSuccessPage() {
        var ticket = new Ticket(1, 1, 1, 1, 1);
        when(ticketService.save(ticket)).thenReturn(ticket);
        var model = new ConcurrentModel();
        var view = ticketController.buyTicket(ticket, model);
        assertThat(view).isEqualTo("ticket/success");
    }

    @Test
    void whenBuyTicketThenGetErrorPageWithMessage() {
        var ticket = new Ticket(1, 1, 1, 1, 1);
        var expectedException = new RuntimeException("");
        when(ticketService.save(any())).thenThrow(expectedException);
        var model = new ConcurrentModel();
        var view = ticketController.buyTicket(ticket, model);
        assertThat(view).isEqualTo("ticket/error");
    }


}