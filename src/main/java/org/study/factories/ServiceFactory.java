package org.study.factories;

import org.study.services.AdministratorService;
import org.study.services.HallService;
import org.study.services.MovieService;
import org.study.services.SessionScheduleService;
import org.study.services.TicketService;
import org.study.services.UserService;
import org.study.services.impl.AdministratorServiceImpl;
import org.study.services.impl.HallServiceImpl;
import org.study.services.impl.MovieServiceImpl;
import org.study.services.impl.SessionScheduleServiceImpl;
import org.study.services.impl.TicketServiceImpl;
import org.study.services.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory SERVICE_FACTORY_INSTANCE = new ServiceFactory();

    private AdministratorService administratorService;
    private UserService userService;
    private TicketService ticketService;
    private SessionScheduleService sessionScheduleService;
    private HallService hallService;
    private MovieService movieService;

    private ServiceFactory() {
        this.administratorService = new AdministratorServiceImpl();
        this.userService = new UserServiceImpl();
        this.ticketService = new TicketServiceImpl();
        this.sessionScheduleService = new SessionScheduleServiceImpl();
        this.hallService = new HallServiceImpl();
        this.movieService = new MovieServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY_INSTANCE;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public UserService getUserService() {
        return userService;
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
}
