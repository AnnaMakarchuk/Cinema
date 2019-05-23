package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.MovieGenreDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.enums.MovieGenre;
import org.study.services.MovieGenreService;

public class MovieGenreServiceImpl implements MovieGenreService {

    /**
     * create instance of DAO class with type MOVIE GENRE
     */
    private static final MovieGenreDAO INSTANCE = (MovieGenreDAO) DAOFactory.createDAO(DAOType.MOVIEGENRE).create();
    private static final Logger LOG = Logger.getLogger(MovieGenreServiceImpl.class);

    /**
     * this method get value MovieGenre from DataBase
     *
     * @param movieGenreName
     * @return enum constant value
     */
    @Override
    public MovieGenre showMovieGenre(String movieGenreName) {
        MovieGenre movieGenre = INSTANCE.get(movieGenreName);
        LOG.info("Movie Genre Service show genre " + movieGenre.name());
        return movieGenre;
    }

    /**
     * this method add new value of MovieGenre in DataBase
     *
     * @param movieGenre
     */
    @Override
    public void addNewMovieGenre(MovieGenre movieGenre) {
        INSTANCE.create(movieGenre);
        LOG.info("Service added new movie Genre " + movieGenre.name());
    }
}
