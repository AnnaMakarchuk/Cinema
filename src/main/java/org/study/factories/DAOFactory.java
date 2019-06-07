package org.study.factories;

import org.study.DAO.*;
import org.study.DAO.impl.*;

public class DAOFactory {

    private static final DAOFactory daoFactoryInstance = new DAOFactory();

    private AdministratorDAO administratorDAO;
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    private SessionScheduleDAO sessionScheduleDAO;
    private HallDAO hallDAO;
    private MovieDAO movieDAO;
    private MovieGenreDAO movieGenreDAO;
    private TicketDAO ticketDAO;
    private PriceDAO priceDAO;

    public DAOFactory() {
        administratorDAO = new AdministratorDAOImpl();
        userDAO = new UserDAOImpl();
        userRoleDAO = new UserRoleDAOImpl();
        sessionScheduleDAO = new SessionScheduleDAOImpl();
        hallDAO = new HallDAOImpl();
        movieDAO = new MovieDAOImpl();
        movieGenreDAO = new MovieGenreDAOImpl();
        ticketDAO = new TicketDAOImpl();
        priceDAO = new PriceDAOImpl();
    }

    public static DAOFactory getInstance() {
        return daoFactoryInstance;
    }

    public static DAOFactory getDaoFactoryInstance() {
        return daoFactoryInstance;
    }

    public AdministratorDAO getAdministratorDAO() {
        return administratorDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public UserRoleDAO getUserRoleDAO() {
        return userRoleDAO;
    }

    public SessionScheduleDAO getSessionScheduleDAO() {
        return sessionScheduleDAO;
    }

    public HallDAO getHallDAO() {
        return hallDAO;
    }

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public MovieGenreDAO getMovieGenreDAO() {
        return movieGenreDAO;
    }

    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public PriceDAO getPriceDAO() {
        return priceDAO;
    }
}
