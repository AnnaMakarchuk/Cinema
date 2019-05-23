package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.MovieDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.Movie;
import org.study.services.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    /**
     * create instance of DAO class with type MOVIE
     */
    private static final MovieDAO INSTANCE = (MovieDAO) DAOFactory.createDAO(DAOType.MOVIE).create();
    private static final Logger LOG = Logger.getLogger(MovieServiceImpl.class);

    /**
     * this method show concrete movie
     *
     * @param movieId
     * @return Movie
     */
    @Override
    public Movie viewMovie(int movieId) {
        Movie movie = INSTANCE.get(movieId);
        LOG.info("MovieService get movie" + movie.getMovieName());
        return movie;
    }

    /**
     * this method show all available movies
     *
     * @return moviesList
     */
    @Override
    public List<Movie> viewAllAvailableMovie() {
        List<Movie> movieList = INSTANCE.getAll();
        LOG.info("MovieService get all movies");
        return movieList;
    }

    /**
     * this methos add new movie
     *
     * @param movie
     */
    @Override
    public void addNewMovie(Movie movie) {
        INSTANCE.create(movie);
        LOG.info("MovieService add new movie " + movie.getMovieName());
    }

    /**
     * this method update information about concrete movie
     *
     * @param movie
     */
    @Override
    public void updateMovieFeature(Movie movie) {
        INSTANCE.update(movie);
        LOG.info("MovieService update movie " + movie.getMovieName());
    }

    /**
     * delete movie for available movies
     *
     * @param movie
     */
    @Override
    public void deleteMovie(Movie movie) {
        INSTANCE.delete(movie);
        LOG.info("MovieService delete movie " + movie.getMovieName());
    }
}
