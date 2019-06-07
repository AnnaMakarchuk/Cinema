package org.study.facade;

import org.apache.log4j.Logger;
import org.study.dto.MovieDto;
import org.study.factories.ServiceFactory;
import org.study.models.Movie;
import org.study.services.MovieService;

import java.util.List;
import java.util.stream.Collectors;

public class MovieFacade {
    private static final Logger LOG = Logger.getLogger(MovieService.class);

    private MovieService movieService;

    public MovieFacade() {
        this.movieService = ServiceFactory.getInstance().getMovieService();
    }

    /**
     * this method convert list of movie models to list of movie dto models
     */
    public List<MovieDto> getAllMovies(boolean isActive) {
        List<Movie> movieList = movieService.viewAllAvailableMovies(isActive);
        LOG.info("MovieFacade method getUserById all movies");
        return createMovieDTOList(movieList);
    }

    /**
     * this method create new movie and add in database
     */
    public void createNewMovie(String movieName, String movieGenre, int movieDuration, int ageLimit,
                               String movieDescription) {
        movieService.addNewMovie(movieName, movieGenre, movieDuration, ageLimit, movieDescription);
    }

    private List<MovieDto> createMovieDTOList(List<Movie> movieList) {
        return movieList.stream()
                .map(movie -> {
                    MovieDto movieDto = new MovieDto();
                    movieDto.setMovieId(movie.getId());
                    movieDto.setMovieName(movie.getMovieName());
                    movieDto.setMovieGenre(movie.getMovieGenre());
                    movieDto.setMovieDuration(movie.getMovieDuration());
                    movieDto.setAgeLimit(movie.getAgeLimit());
                    movieDto.setMovieDescription(movie.getMovieDescription());
                    return movieDto;
                })
                .collect(Collectors.toList());
    }
}
