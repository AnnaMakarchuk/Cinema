package org.study.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.study.dao.HallDao;
import org.study.dao.PriceDao;
import org.study.dao.SessionScheduleDao;
import org.study.dao.TicketDao;
import org.study.factories.DaoFactory;
import org.study.models.Hall;
import org.study.models.Place;
import org.study.models.SessionSchedule;
import org.study.services.HallService;

public class HallServiceImpl implements HallService {
    private static final Logger LOG = Logger.getLogger(HallServiceImpl.class);

    private HallDao hallDao;
    private SessionScheduleDao sessionScheduleDao;
    private PriceDao priceDao;
    private TicketDao ticketDao;

    public HallServiceImpl() {
        this.hallDao = DaoFactory.getInstance().getHallDao();
        this.sessionScheduleDao = DaoFactory.getInstance().getSessionScheduleDao();
        this.priceDao = DaoFactory.getInstance().getPriceDao();
        this.ticketDao = DaoFactory.getInstance().getTicketDao();
    }

    /**
     * this method get hall for selected schedule with price and occupied places
     */
    @Override
    public Hall getHallWithPriceAndPlaces(int scheduleId) {
        SessionSchedule sessionSchedule = sessionScheduleDao.get(scheduleId);
        int hallId = sessionSchedule.getHall().getId();
        LOG.info("HallService get hallId from sessionSchedule");

        Hall hall = hallDao.get(hallId);
        LOG.info("HallService get hall" + hall.getHallName());
        List<Place> occupiedPlaces = getOccupiedPlacesList(scheduleId);
        LOG.info("HallService get all occupied places in hall");

        Map<Integer, Double> priceType = new HashMap<>();
        for (int i = 1; i <= hall.getMaxRow(); i++) {
            double priceForRow = priceDao.get(i, hallId);
            priceType.put(i, priceForRow);
        }
        LOG.info("HallService get all prices in hall");
        hall.setOccupiedPlaces(occupiedPlaces);
        hall.setPriceType(priceType);
        return hall;
    }

    private List<Place> getOccupiedPlacesList(int sessionId) {
        return ticketDao.getTicketsBySchedule(sessionId).stream()
                .map(ticket -> new Place(ticket.getPlace().getRow(), ticket.getPlace().getPlaceNumber()))
                .collect(Collectors.toList());
    }
}
