package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@ThreadSafe
public class SimpleTicketService implements TicketService {

    private final TicketRepository ticketRepository;

    private SimpleTicketService(TicketRepository sql2oTicketRepository, FileService fileService) {
        this.ticketRepository = sql2oTicketRepository;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public boolean deleteById(int id) {
        var ticketOptional = findById(id);
        if (ticketOptional.isEmpty()) {
            return false;
        }
        var isDeleted = ticketRepository.deleteById(id);
        return isDeleted;
    }

    @Override
    public boolean update(Ticket ticket) {
        return ticketRepository.update(ticket);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Collection<Ticket> findAll() {
        return ticketRepository.findAll();
    }

}