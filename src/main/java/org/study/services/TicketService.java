package org.study.services;

import org.study.models.Place;
import org.study.models.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> viewAllTicketsByUser(int userId);

    List<Ticket> createTicketByUser(int userId, int sessionId, List<Place> placesList);

    void deleteTicketByUser(int ticketId);

    void deleteTicketByAdmin(List<Integer> scheduleIdList);

    int countOccupiedPlaces();

    List<Ticket> viewAllTicketsByPages(int pageNumber, int rowsOnPage);
}
