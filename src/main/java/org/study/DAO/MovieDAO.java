package org.study.DAO;

import org.study.models.Movie;

import java.util.List;

public interface MovieDAO {

    Movie get(int movieId);

    void create(Movie movie);

    void update(Movie movie);

    void delete(Movie movie);

    List<Movie> getAll();

}
