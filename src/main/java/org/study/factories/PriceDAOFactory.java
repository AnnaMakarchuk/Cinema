package org.study.factories;

import org.study.DAO.impl.PriceDAOImpl;

public class PriceDAOFactory implements DAOFactories {

    private PriceDAOFactory() {
    }

    private static class PriceDAOFactorySingletonHolder {
        private final static PriceDAOFactory INSTANCE = new PriceDAOFactory();
    }

    public static PriceDAOFactory getInstance() {
        return PriceDAOFactory.PriceDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public PriceDAOImpl create() {
        return new PriceDAOImpl();
    }
}
