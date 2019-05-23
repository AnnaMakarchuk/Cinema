package org.study.services;

import org.study.models.enums.MovieGenre;

public interface MovieGenreService {

    MovieGenre showMovieGenre(String movieGenreName);

    void addNewMovieGenre(MovieGenre movieGenre);
}
