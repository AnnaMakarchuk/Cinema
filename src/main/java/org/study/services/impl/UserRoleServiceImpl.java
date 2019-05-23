package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.UserRoleDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.enums.UserRole;
import org.study.services.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {
    /**
     * create instance of DAO class with type MOVIE GENRE
     */
    private static final UserRoleDAO INSTANCE = (UserRoleDAO) DAOFactory.createDAO(DAOType.USERROLE).create();
    private static final Logger LOG = Logger.getLogger(UserRoleServiceImpl.class);

    /**
     * this method get value UserRole from DataBase
     *
     * @param userRoleName
     * @return
     */
    @Override
    public UserRole showUserRole(String userRoleName) {
        return INSTANCE.get(userRoleName);
//        UserRole userRole = userRoleDAO.get(userRoleName);
//        LOG.info("User Role Service show role " + userRole.name());
//        return userRole;
    }

    /**
     * this method add new value of UserRole in DataBase
     *
     * @param userRole
     */
    @Override
    public void addNewUserRole(UserRole userRole) {
        INSTANCE.create(userRole);
        LOG.info("Service added new user role " + userRole.name());
    }
}
