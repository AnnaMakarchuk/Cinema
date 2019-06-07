package org.study.services.impl;

import java.util.Set;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.study.dao.TicketDao;
import org.study.dao.UserDao;
import org.study.dao.UserRoleDao;
import org.study.factories.DaoFactory;
import org.study.models.RegisteredUser;
import org.study.models.enums.Gender;
import org.study.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private UserRoleDao userRoleDao;
    private TicketDao ticketDao;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getInstance().getUserDao();
        this.userRoleDao = DaoFactory.getInstance().getUserRoleDao();
        this.ticketDao = DaoFactory.getInstance().getTicketDao();
    }

    /**
     * this method show concrete user by ID
     */
    @Override
    public RegisteredUser viewUser(int userId) {
        RegisteredUser registeredUser = userDao.getUserById(userId);
        LOG.info("UserService getUserById user" + registeredUser.getUserLogin());
        return registeredUser;
    }

    /**
     * this method create new user from input parameters and call dao to putt user in database
     */
    @Override
    public void registerNewUser(String userName, String userSurname, String userRole, String userGender,
                                String userLogin, String userEMailAddress, String userPassword) {
        int roleId = userRoleDao.getId(userRole);
        LOG.info("UserService getUserById user id from database");
        RegisteredUser registeredUser = new RegisteredUser(userName, userSurname,
                Gender.valueOf(userGender.toUpperCase()), userLogin, userEMailAddress, userPassword);
        userDao.create(roleId, registeredUser);
        LOG.info("UserService registered new user " + userName + " " + userSurname);
    }

    /**
     * this method update users data
     */
    @Override
    public RegisteredUser updateUserInformation( int userId, String clientLogin, String clientPassword) {
        userDao.update(userId, clientLogin, clientPassword);
        LOG.info("AdministratorService updateIsActive data of admin with id " + userId);
        RegisteredUser registeredUser = userDao.getUserById(userId);
        LOG.info("UserService getUserById user" + registeredUser.getUserLogin());
        return registeredUser;
    }

    /**
     * this method delete user from the cinema base
     */
    @Override
    public void deleteUser(int userId) {
        userDao.delete(userId);
        LOG.info("UserService delete registered id " + userId);
    }

    /**
     * this method select user from database by login and password.
     * In case incorrect data from server registered user will be null
     */
    @Override
    public RegisteredUser getUserByLoginPassword(String userLogin, String userPassword) {
        return userDao.getUserByLoginAndPassword(userLogin, userPassword);
    }

    /**
     * for each cancelled session schedule method find tickets for delete and getUserById user id from them
     */
    @Override
    public List<RegisteredUser> createListUsersFromTicket(List<Integer> scheduleIdList) {
        Set<Integer> userId = scheduleIdList.stream()
                .map(ticketDao::getUserIdByScheduleId)
                .collect(Collectors.toSet());
        LOG.info("List of user ID is created");
        return userId.stream().filter(id -> id != 0)
                .map(userDao::getUserById)
                .collect(Collectors.toList());
    }
}
