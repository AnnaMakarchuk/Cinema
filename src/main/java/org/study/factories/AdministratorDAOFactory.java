package org.study.factories;

import org.study.DAO.impl.AdministratorDAOImpl;
import org.study.DAO.impl.UserDAOImpl;

public class AdministratorDAOFactory implements DAOFactories {

    private AdministratorDAOFactory() {
    }

    private static class AdministratorDAOFactorySingletonHolder {
        private final static AdministratorDAOFactory INSTANCE = new AdministratorDAOFactory();
    }

    public static AdministratorDAOFactory getInstance() {
        return AdministratorDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public AdministratorDAOImpl create() {
        return new AdministratorDAOImpl();
    }
}
