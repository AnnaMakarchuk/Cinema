package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.UserDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.RegisteredUser;
import org.study.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    /**
     * create instance of DAO class with type USER
     */
    private static final UserDAO INSTANCE = (UserDAO) DAOFactory.createDAO(DAOType.USER).create();
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    /**
     * this method show concrete user
     *
     * @param userId
     * @return user
     */
    @Override
    public RegisteredUser viewUser(int userId) {
        RegisteredUser registeredUser = INSTANCE.get(userId);
        LOG.info("UserService get user" + registeredUser.getUserLogin());
        return registeredUser;
    }

    /**
     * this method show all registered users
     *
     * @return registeredUserList
     */
    @Override
    public List<RegisteredUser> viewAllAvailableUsers() {
        List<RegisteredUser> registeredUserList = INSTANCE.getAll();
        LOG.info("UserService get all registered users");
        return registeredUserList;
    }

    /**
     * this method register new user
     *
     * @param registeredUser
     */
    @Override
    public void registerNewUser(RegisteredUser registeredUser) {
        INSTANCE.create(registeredUser);
        LOG.info("UserService registered new user" + registeredUser.getUserLogin());
    }

    /**
     * this method update users data
     *
     * @param registeredUser
     */
    @Override
    public void updateUserInformation(RegisteredUser registeredUser) {
        INSTANCE.update(registeredUser);
        LOG.info("UserService update data of registered user " + registeredUser.getUserLogin());
    }

    /**
     * this method delete user from the cinema base
     *
     * @param registeredUser
     */
    @Override
    public void deleteUser(RegisteredUser registeredUser) {
        INSTANCE.delete(registeredUser);
        LOG.info("UserService delete registered user " + registeredUser.getUserLogin());
    }
}
