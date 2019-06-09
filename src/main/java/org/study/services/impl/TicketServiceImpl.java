package org.study.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.study.dao.PriceDao;
import org.study.dao.SessionScheduleDao;
import org.study.dao.TicketDao;
import org.study.factories.DaoFactory;
import org.study.models.Place;
import org.study.models.SessionSchedule;
import org.study.models.Ticket;
import org.study.services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private static final Logger LOG = Logger.getLogger(TicketServiceImpl.class);
    private static final int HALL_ID = 1;

    private TicketDao ticketDao;
    private PriceDao priceDao;
    private SessionScheduleDao sessionScheduleDao;

    public TicketServiceImpl() {
        this.ticketDao = DaoFactory.getInstance().getTicketDao();
        this.priceDao = DaoFactory.getInstance().getPriceDao();
        this.sessionScheduleDao = DaoFactory.getInstance().getSessionScheduleDao();
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
     * this method create list of tickets from places List and find price for each place in hall 1
     */
    @Override
    public List<Ticket> createTicketByUser(int userId, int scheduleId, List<Place> placesList) {
        SessionSchedule sessionSchedule = sessionScheduleDao.get(scheduleId);
        LOG.info("Schedule with id + " + scheduleId + "for bought tickets was selected ");
        List<Ticket> ticketList = new ArrayList<>();
        for (Place place : placesList) {
            double placePrice = priceDao.get(place.getRow(), HALL_ID);
            ticketList.add(new Ticket(sessionSchedule, place, placePrice));
        }
        LOG.info("ticket list with schedule, places and price is created");
        for (Ticket ticket : ticketList) {
            ticketDao.create(userId, scheduleId, ticket);
        }
        LOG.info("TicketService create new tickets bought by user " + userId);
        return ticketList;
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
