package org.study.services;

import org.study.models.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> viewAllTicketsByUser(int userId);

    void createTicketByUser(int userId, Ticket ticket);

    void updateTicketByUser(Ticket ticket);

    void deleteTicketByUser(Ticket ticket);

    int countOccupiedPlacesBySessionSchedule(int sessionId);

    List<Ticket> viewAllTicketsBySessionSchedule(int sessionId);

    void deleteTicketByAdmin(int sessionId);

}
