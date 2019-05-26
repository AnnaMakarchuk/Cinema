package org.study.factories;

import org.study.services.*;
import org.study.services.impl.*;

public class ServiceFactory {

    private static final ServiceFactory SERVICE_FACTORY_INSTANCE = new ServiceFactory();

    private AdministratorService administratorService;
    private UserService userService;
    private UserRoleService userRoleService;
    private TicketService ticketService;
    private SessionScheduleService sessionScheduleService;
    private HallService hallService;
    private MovieService movieService;
    private MovieGenreService movieGenreService;

    public ServiceFactory() {
        this.administratorService = new AdministratorServiceImpl();
        this.userService = new UserServiceImpl();
        this.userRoleService = new UserRoleServiceImpl();
        this.ticketService = new TicketServiceImpl();
        this.sessionScheduleService = new SessionScheduleServiceImpl();
        this.hallService = new HallServiceImpl();
        this.movieService = new MovieServiceImpl();
        this.movieGenreService = new MovieGenreServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY_INSTANCE;
    }

    public static ServiceFactory getServiceFactoryInstance() {
        return SERVICE_FACTORY_INSTANCE;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public SessionScheduleService getSessionScheduleService() {
        return sessionScheduleService;
    }

    public HallService getHallService() {
        return hallService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public MovieGenreService getMovieGenreService() {
        return movieGenreService;
    }
}
