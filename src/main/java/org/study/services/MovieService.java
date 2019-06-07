package org.study.services;

import org.study.models.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> viewAllAvailableMovies(boolean isActive);

    void addNewMovie(String movieName, String movieGenre, int movieDuration, int ageLimit,
                     String movieDescription);
}
