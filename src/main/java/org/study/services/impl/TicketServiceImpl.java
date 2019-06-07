package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.dao.TicketDao;
import org.study.factories.DaoFactory;
import org.study.models.Ticket;
import org.study.services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private static final Logger LOG = Logger.getLogger(TicketServiceImpl.class);

    private TicketDao ticketDao;

    public TicketServiceImpl() {
        this.ticketDao = DaoFactory.getInstance().getTicketDao();
    }

    /**
     * this method show all tickets bought ny concrete user.
     */
    @Override
    public List<Ticket> viewAllTicketsByUser(int userId) {
        List<Ticket> ticketList = ticketDao.get(userId);
        LOG.info("TicketService view all tickets bought by user " + userId);
        return ticketList;
    }

    /**
     * this method create ticket after registered user bought it
     */
    @Override
    public void createTicketByUser(int userId, int sessionId, List<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            ticketDao.create(userId, sessionId, ticket);
        }
        LOG.info("TicketService create new tickets bought by user " + userId);
    }

    /**
     * this method delete selected ticket
     */
    @Override
    public void deleteTicketByUser(int ticketId) {
        ticketDao.delete(ticketId);
        LOG.info("TicketService delete select ticket");
    }

    /**
     * this method allows admin to see total quantity of bought tickets
     */
    @Override
    public int countOccupiedPlaces() {
        int occupiedPlaces = ticketDao.countOccupiedPlaces();
        LOG.info("TicketService count occupied places on session");
        return occupiedPlaces;
    }

    /**
     * this method allows admin to delete all bought tickets for concrete session in schedule.
     * this method call in case deleting or updating schedule dy admin.
     */
    @Override
    public void deleteTicketByAdmin(List<Integer> scheduleIdList) {
        scheduleIdList.forEach(ticketDao::deleteBySchedule);
    }

    /**
     * this method show all purchased tickets in cinema with pagination
     */
    @Override
    public List<Ticket> viewAllTicketsByPages(int pageNumber, int rowsOnPage) {
        List<Ticket> ticketList = ticketDao.getAllTicketsByPages(pageNumber, rowsOnPage);
        LOG.info("TicketService view all available tickets");
        return ticketList;
    }
}
