package org.study.DAO;

import org.study.models.enums.MovieGenre;

public interface MovieGenreDAO {

    MovieGenre get(String genre);

    void create(MovieGenre movieGenre);

    void update(MovieGenre movieGenre);

    void delete(MovieGenre movieGenre);
}
