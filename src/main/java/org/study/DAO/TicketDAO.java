package org.study.DAO;

import org.study.models.Ticket;

import java.util.List;

public interface TicketDAO {

    List<Ticket> get(int userId);

    void create(int userId, Ticket ticket);

    void update(Ticket ticket);

    void delete(Ticket ticket);

    int countOccupiedPlaces(int sessionId);

    List<Ticket> getBySession(int sessionId);

    void deleteBySession(int sessionId);
}
