package org.study.factories;

import org.study.DAO.impl.MovieDAOImpl;

public class MovieDAOFactory implements DAOFactories {

    private MovieDAOFactory() {
    }

    private static class MovieDAOFactorySingletonHolder {
        private final static MovieDAOFactory INSTANCE = new MovieDAOFactory();
    }

    public static MovieDAOFactory getInstance() {
        return MovieDAOFactory.MovieDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public MovieDAOImpl create() {
        return new MovieDAOImpl();
    }
}