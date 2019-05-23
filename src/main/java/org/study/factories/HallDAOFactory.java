package org.study.factories;

import org.study.DAO.impl.HallDAOImpl;
import org.study.DAO.impl.UserDAOImpl;

public class HallDAOFactory implements DAOFactories {

    private HallDAOFactory() {
    }

    private static class HallDAOFactorySingletonHolder {
        private final static HallDAOFactory INSTANCE = new HallDAOFactory();
    }

    public static HallDAOFactory getInstance() {
        return HallDAOFactory.HallDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public HallDAOImpl create() {
        return new HallDAOImpl();
    }
}
