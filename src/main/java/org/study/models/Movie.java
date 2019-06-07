package org.study.models;

import org.study.models.enums.MovieGenre;

public class Movie {
    private int movieId;
    private String movieName;
    private MovieGenre movieGenre;
    private int movieDuration;
    private int ageLimit;
    private String movieDescription;

    public Movie(int movieId, String movieName, MovieGenre movieGenre, int movieDuration, int ageLimit,
                 String movieDescription) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieDuration = movieDuration;
        this.ageLimit = ageLimit;
        this.movieDescription = movieDescription;
    }

    public Movie(String movieName, MovieGenre movieGenre, int movieDuration, int ageLimit,
                 String movieDescription) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieDuration = movieDuration;
        this.ageLimit = ageLimit;
        this.movieDescription = movieDescription;
    }

    public int getId() {
        return movieId;
    }

    public void setId(int id) {
        this.movieId = id;
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

        if (movieId != movie.movieId) return false;
        if (movieDuration != movie.movieDuration) return false;
        if (ageLimit != movie.ageLimit) return false;
        if (movieName != null ? !movieName.equals(movie.movieName) : movie.movieName != null) return false;
        if (movieGenre != movie.movieGenre) return false;
        return movieDescription != null ? movieDescription.equals(movie.movieDescription) : movie.movieDescription == null;
    }

    @Override
    public int hashCode() {
        int result = movieId;
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
                "id=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieGenre=" + movieGenre +
                ", movieDuration=" + movieDuration +
                ", ageLimit=" + ageLimit +
                ", movieDescription='" + movieDescription + '\'' +
                '}';
    }
}
