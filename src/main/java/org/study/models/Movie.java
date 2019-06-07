package org.study.models;

import org.study.models.enums.MovieGenre;
import org.study.utils.ResultSetTablesData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie {
    private int id;
    private String movieName;
    private MovieGenre movieGenre;
    private int movieDuration;
    private int ageLimit;
    private String movieDescription;

    public Movie(int id, String movieName, MovieGenre movieGenre, int movieDuration, int ageLimit,
                 String movieDescription) {
        this.id = id;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieDuration = movieDuration;
        this.ageLimit = ageLimit;
        this.movieDescription = movieDescription;
    }

    /**
     * create Movie with constructor with an input parameter ResultSet*
     */
    public Movie(ResultSet resultSet) throws SQLException {
        this.id = ResultSetTablesData.getIntByName(ResultSetTablesData.ID, resultSet);
        this.movieName = ResultSetTablesData.getStringByName(ResultSetTablesData.NAME, resultSet);
        this.movieGenre = ResultSetTablesData.getMovieGenre(resultSet);
        this.movieDuration = ResultSetTablesData.getIntByName(ResultSetTablesData.MOVIE_DURATION, resultSet);
        this.ageLimit = ResultSetTablesData.getIntByName(ResultSetTablesData.MOVIE_AGE, resultSet);
        this.movieDescription = ResultSetTablesData.getStringByName(ResultSetTablesData.MOVIE_DESCRIPTION, resultSet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(MovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (movieDuration != movie.movieDuration) return false;
        if (ageLimit != movie.ageLimit) return false;
        if (movieName != null ? !movieName.equals(movie.movieName) : movie.movieName != null) return false;
        if (movieGenre != movie.movieGenre) return false;
        return movieDescription != null ? movieDescription.equals(movie.movieDescription) : movie.movieDescription == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (movieName != null ? movieName.hashCode() : 0);
        result = 31 * result + (movieGenre != null ? movieGenre.hashCode() : 0);
        result = 31 * result + movieDuration;
        result = 31 * result + ageLimit;
        result = 31 * result + (movieDescription != null ? movieDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieGenre=" + movieGenre +
                ", movieDuration=" + movieDuration +
                ", ageLimit=" + ageLimit +
                ", movieDescription='" + movieDescription + '\'' +
                '}';
    }
}
