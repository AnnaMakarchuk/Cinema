package org.study.factories;

import org.study.DAO.impl.MovieGenreDAOImpl;

public class MovieGenreDAOFactory implements DAOFactories {

    private MovieGenreDAOFactory() {
    }

    private static class MovieGenreDAOFactorySingletonHolder {
        private final static MovieGenreDAOFactory INSTANCE = new MovieGenreDAOFactory();
    }

    public static MovieGenreDAOFactory getInstance() {
        return MovieGenreDAOFactory.MovieGenreDAOFactorySingletonHolder.INSTANCE;
    }

    @Override
    public MovieGenreDAOImpl create() {
        return new MovieGenreDAOImpl();
    }
}