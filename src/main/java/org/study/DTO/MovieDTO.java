package org.study.DTO;

import org.study.models.enums.MovieGenre;

public class MovieDTO {

    private int id;
    private String movieName;
    private MovieGenre movieGenre;
    private int movieDuration;
    private int ageLimit;
    private String movieDescription;

    public MovieDTO(int id, String movieName, MovieGenre movieGenre, int movieDuration, int ageLimit, String movieDescription) {
        this.id = id;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieDuration = movieDuration;
        this.ageLimit = ageLimit;
        this.movieDescription = movieDescription;
    }

    public MovieDTO() {
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
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieGenre=" + movieGenre +
                ", movieDuration=" + movieDuration +
                ", ageLimit=" + ageLimit +
                ", movieDescription='" + movieDescription + '\'' +
                '}';
    }
}
