package org.study.factories;

import org.study.facade.HallFacade;
import org.study.facade.MovieFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.facade.TicketFacade;
import org.study.facade.UserFacade;

public class FacadeFactory {
    private static final FacadeFactory FACADE_FACTORY_INSTANCE = new FacadeFactory();

    private UserFacade userFacade;
    private MovieFacade movieFacade;
    private SessionScheduleFacade sessionScheduleFacade;
    private HallFacade hallFacade;
    private TicketFacade ticketFacade;

    private FacadeFactory() {
        this.userFacade = new UserFacade();
        this.movieFacade = new MovieFacade();
        this.sessionScheduleFacade = new SessionScheduleFacade();
        this.hallFacade = new HallFacade();
        this.ticketFacade = new TicketFacade();
    }

    public static FacadeFactory getInstance() {
        return FACADE_FACTORY_INSTANCE;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public MovieFacade getMovieFacade() {
        return movieFacade;
    }

    public SessionScheduleFacade getSessionScheduleFacade() {
        return sessionScheduleFacade;
    }

    public HallFacade getHallFacade() {
        return hallFacade;
    }

    public TicketFacade getTicketFacade() {
        return ticketFacade;
    }
}
