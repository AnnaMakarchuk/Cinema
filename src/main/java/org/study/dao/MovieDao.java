package org.study.dao;

import org.study.models.Movie;

import java.util.List;

public interface MovieDao {

    Movie getMovieById(int movieId);

    List<Movie> getAll(boolean isActive);

    void create(int movieGenreId, Movie movie);

    void updateMovieStatus(int movieId, boolean isActive);
}
