package org.study.dto;

import org.study.models.enums.MovieGenre;

public class MovieDto {

    private int movieId;
    private String movieName;
    private MovieGenre movieGenre;
    private int movieDuration;
    private int ageLimit;
    private String movieDescription;

    public MovieDto() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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
        if (!(o instanceof MovieDto)) return false;

        MovieDto movieDto = (MovieDto) o;

        if (movieId != movieDto.movieId) return false;
        if (movieDuration != movieDto.movieDuration) return false;
        if (ageLimit != movieDto.ageLimit) return false;
        if (movieName != null ? !movieName.equals(movieDto.movieName) : movieDto.movieName != null) return false;
        if (movieGenre != movieDto.movieGenre) return false;
        return movieDescription != null ? movieDescription.equals(movieDto.movieDescription) : movieDto.movieDescription == null;
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
        return "MovieDTO{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieGenre=" + movieGenre +
                ", movieDuration=" + movieDuration +
                ", ageLimit=" + ageLimit +
                ", movieDescription='" + movieDescription + '\'' +
                '}';
    }
}
