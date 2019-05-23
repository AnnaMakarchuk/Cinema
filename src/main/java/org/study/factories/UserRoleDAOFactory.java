package org.study.factories;

import org.study.DAO.impl.UserRoleDAOImpl;

public class UserRoleDAOFactory implements DAOFactories {

    private UserRoleDAOFactory() {
    }

    private static class UserRoleDAOFactorySingletonHolder {
        private final static UserRoleDAOFactory INSTANCE = new UserRoleDAOFactory();
    }

    public static UserRoleDAOFactory getInstance() {
        return UserRoleDAOFactory.UserRoleDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public UserRoleDAOImpl create() {
        return new UserRoleDAOImpl();
    }
}
