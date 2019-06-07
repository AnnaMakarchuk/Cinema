package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.dao.MovieDao;
import org.study.dao.MovieGenreDao;
import org.study.factories.DaoFactory;
import org.study.models.Movie;
import org.study.models.enums.MovieGenre;
import org.study.services.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    private static final Logger LOG = Logger.getLogger(MovieServiceImpl.class);

    private MovieDao movieDao;
    private MovieGenreDao movieGenreDao;

    public MovieServiceImpl() {
        this.movieDao = DaoFactory.getInstance().getMovieDao();
        this.movieGenreDao = DaoFactory.getInstance().getMovieGenreDao();
    }

    /**
     * this method show all available active movies
     */
    @Override
    public List<Movie> viewAllAvailableMovies(boolean isActive) {
        List<Movie> movieList = movieDao.getAll(isActive);
        LOG.info("MovieService get all movies");
        return movieList;
    }

    /**
     * this method add new movie
     */
    @Override
    public void addNewMovie(String movieName, String movieGenre, int movieDuration, int ageLimit,
                            String movieDescription) {
        int movieGenreID = movieGenreDao.get(movieGenre);
        LOG.info("MovieService getUserById movie genre id from database");
        Movie movie = new Movie(movieName, MovieGenre.valueOf(movieGenre.toUpperCase()), movieDuration, ageLimit,
                movieDescription);
        movieDao.create(movieGenreID, movie);
        LOG.info("Movie added");
    }
}
