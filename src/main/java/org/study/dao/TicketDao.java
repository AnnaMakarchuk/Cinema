package org.study.dao;

import org.study.models.Ticket;

import java.util.List;

public interface TicketDao {

    List<Ticket> get(int userId);

    void create(int userId, int scheduleId, Ticket ticket);

    void delete(int ticketId);

    int countOccupiedPlaces();

    List<Ticket> getTicketsBySchedule(int scheduleId);

    void deleteBySchedule(int scheduleId);

    List<Ticket> getAllTicketsByPages(int pageQuantities, int rowsOnPage);

    int getUserIdByScheduleId(int scheduleId);
}
