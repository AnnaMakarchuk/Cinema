package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.TicketDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.Ticket;
import org.study.services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    /**
     * create instance of DAO class with type TICKET
     */
    private static final TicketDAO INSTANCE = (TicketDAO) DAOFactory.createDAO(DAOType.TICKET).create();
    private static final Logger LOG = Logger.getLogger(TicketServiceImpl.class);

    /**
     * this method show all tickets bought ny concrete user.
     *
     * @param userId
     * @return ticketList
     */
    @Override
    public List<Ticket> viewAllTicketsByUser(int userId) {
        List<Ticket> ticketList = INSTANCE.get(userId);
        LOG.info("TicketService view all tickets bought by user " + userId);
        return ticketList;
    }

    /**
     * this method create ticket after registered user bought it
     *
     * @param userId
     * @param ticket
     */
    @Override
    public void createTicketByUser(int userId, Ticket ticket) {
        INSTANCE.create(userId, ticket);
        LOG.info("TicketService create new ticket bought by user " + userId);
    }

    /**
     * this method update data in selected ticket
     *
     * @param ticket
     */
    @Override
    public void updateTicketByUser(Ticket ticket) {
        INSTANCE.update(ticket);
        LOG.info("TicketService update ticket bought by ticket id");
    }

    /**
     * this method delete selected ticket
     *
     * @param ticket
     */
    @Override
    public void deleteTicketByUser(Ticket ticket) {
        INSTANCE.delete(ticket);
        LOG.info("TicketService delete ticket bought by ticket id");
    }

    /**
     * this method allows admin to see total quantity of bought tickets for concrete session in schedule
     *
     * @param sessionId
     * @return
     */
    @Override
    public int countOccupiedPlacesBySessionSchedule(int sessionId) {
        int occupiedPlaces = INSTANCE.countOccupiedPlaces(sessionId);
        LOG.info("TicketService count occupied places on session " + sessionId);
        return occupiedPlaces;
    }

    /**
     * this method allows admin to see occupied places for concrete session in schedule
     *
     * @param sessionId
     * @return
     */
    @Override
    public List<Ticket> viewAllTicketsBySessionSchedule(int sessionId) {
        List<Ticket> ticketList = INSTANCE.getBySession(sessionId);
        LOG.info("TicketService view all occupied places on session " + sessionId);
        return ticketList;
    }

    /**
     * this method allowa admin to delete all bought tickets for concrete session in schedule.
     * this method call in case deleting or updating schedule dy admin.
     *
     * @param sessionId
     */
    @Override
    public void deleteTicketByAdmin(int sessionId) {
        INSTANCE.deleteBySession(sessionId);
        LOG.info("TicketService delete all tickets on session " + sessionId);
    }
}
