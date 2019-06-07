package org.study.factories;

import org.study.facade.MovieFacade;
import org.study.facade.UserFacade;

public class FacadeFactory {
    private static final FacadeFactory FACADE_FACTORY_INSTANCE = new FacadeFactory();

    private UserFacade userFacade;
    private MovieFacade movieFacade;

    private FacadeFactory() {
        this.userFacade = new UserFacade();
        this.movieFacade = new MovieFacade();
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
}
