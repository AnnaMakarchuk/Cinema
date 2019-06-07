package org.study.factories;

import org.study.dao.AdministratorDao;
import org.study.dao.HallDao;
import org.study.dao.MovieDao;
import org.study.dao.MovieGenreDao;
import org.study.dao.PriceDao;
import org.study.dao.SessionScheduleDao;
import org.study.dao.TicketDao;
import org.study.dao.UserDao;
import org.study.dao.UserRoleDao;
import org.study.dao.impl.AdministratorDaoImpl;
import org.study.dao.impl.HallDaoImpl;
import org.study.dao.impl.MovieDaoImpl;
import org.study.dao.impl.MovieGenreDaoImpl;
import org.study.dao.impl.PriceDaoImpl;
import org.study.dao.impl.SessionScheduleDaoImpl;
import org.study.dao.impl.TicketDaoImpl;
import org.study.dao.impl.UserDaoImpl;
import org.study.dao.impl.UserRoleDaoImpl;

public class DaoFactory {

    private static final DaoFactory DAO_FACTORY_INSTANCE = new DaoFactory();

    private AdministratorDao administratorDao;
    private UserDao userDao;
    private UserRoleDao userRoleDao;
    private SessionScheduleDao sessionScheduleDao;
    private HallDao hallDao;
    private MovieDao movieDao;
    private MovieGenreDao movieGenreDao;
    private TicketDao ticketDao;
    private PriceDao priceDao;

    private DaoFactory() {
        administratorDao = new AdministratorDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
        sessionScheduleDao = new SessionScheduleDaoImpl();
        hallDao = new HallDaoImpl();
        movieDao = new MovieDaoImpl();
        movieGenreDao = new MovieGenreDaoImpl();
        ticketDao = new TicketDaoImpl();
        priceDao = new PriceDaoImpl();
    }

    public static DaoFactory getInstance() {
        return DAO_FACTORY_INSTANCE;
    }

    public AdministratorDao getAdministratorDao() {
        return administratorDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public SessionScheduleDao getSessionScheduleDao() {
        return sessionScheduleDao;
    }

    public HallDao getHallDao() {
        return hallDao;
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }

    public MovieGenreDao getMovieGenreDao() {
        return movieGenreDao;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public PriceDao getPriceDao() {
        return priceDao;
    }
}
