package org.study.factories;

import org.study.DAO.impl.UserDAOImpl;

public class UserDAOFactory implements DAOFactories {

    private UserDAOFactory() {
    }

    private static class UserDAOFactorySingletonHolder {
        private final static UserDAOFactory INSTANCE = new UserDAOFactory();
    }

    public static UserDAOFactory getInstance() {
        return UserDAOFactory.UserDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public UserDAOImpl create() {
        return new UserDAOImpl();
    }
}
