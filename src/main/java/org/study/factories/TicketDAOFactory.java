package org.study.factories;

import org.study.DAO.impl.TicketDAOImpl;
import org.study.DAO.impl.UserDAOImpl;

public class TicketDAOFactory implements DAOFactories {

    private TicketDAOFactory() {
    }

    private static class TicketDAOFactorySingletonHolder {
        private final static TicketDAOFactory INSTANCE = new TicketDAOFactory();
    }

    public static TicketDAOFactory getInstance() {
        return TicketDAOFactory.TicketDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public TicketDAOImpl create() {
        return new TicketDAOImpl();
    }
}