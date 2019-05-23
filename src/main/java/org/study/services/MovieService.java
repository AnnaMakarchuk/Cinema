package org.study.services;

import org.study.models.Movie;

import java.util.List;

public interface MovieService {

    Movie viewMovie(int movieId);

    List<Movie> viewAllAvailableMovie();

    void addNewMovie(Movie movie);

    void updateMovieFeature(Movie movie);

    void deleteMovie(Movie movie);

}
