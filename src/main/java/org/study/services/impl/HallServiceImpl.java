package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.HallDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.Hall;
import org.study.services.HallService;

public class HallServiceImpl implements HallService {

    /**
     * create instance of DAO class with type HALL
     */
    private static final HallDAO INSTANCE = (HallDAO) DAOFactory.createDAO(DAOType.HALL).create();
    private static final Logger LOG = Logger.getLogger(HallServiceImpl.class);

    /**
     * this method get hall by Id from database
     *
     * @param hallId
     * @return Hall
     */
    @Override
    public Hall viewHallFromDataBase(int hallId) {
        Hall hall = INSTANCE.get(hallId);
        LOG.info("HallService get hall" + hall.getHallName());
        return hall;
    }

    /**
     * this method add new hall in base
     *
     * @param hall
     */
    @Override
    public void addNewHall(Hall hall) {
        INSTANCE.create(hall);
        LOG.info("HallService create new Hall " + hall.getHallName());
    }

    /**
     * this method set updated characteristic for selected hall
     *
     * @param hall
     */
    @Override
    public void setNewHallFeature(Hall hall) {
        INSTANCE.update(hall);
        LOG.info("HallService update hall " + hall.getHallName());
    }
}
