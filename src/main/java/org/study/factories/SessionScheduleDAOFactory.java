package org.study.factories;

import org.study.DAO.impl.SessionScheduleDAOImpl;

public class SessionScheduleDAOFactory implements DAOFactories {

    private SessionScheduleDAOFactory() {
    }

    private static class SessionScheduleDAOFactorySingletonHolder {
        private final static SessionScheduleDAOFactory INSTANCE = new SessionScheduleDAOFactory();
    }

    public static SessionScheduleDAOFactory getInstance() {
        return SessionScheduleDAOFactory.SessionScheduleDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public SessionScheduleDAOImpl create() {
        return new SessionScheduleDAOImpl();
    }
}
