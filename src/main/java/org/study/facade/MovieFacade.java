package org.study.facade;

import org.apache.log4j.Logger;
import org.study.DTO.MovieDTO;
import org.study.factories.ServiceFactory;
import org.study.models.Movie;
import org.study.services.MovieService;

import java.util.List;
import java.util.stream.Collectors;

public class MovieFacade {

    private static final Logger LOG = Logger.getLogger(MovieService.class);

    private MovieService movieService = ServiceFactory.getInstance().getMovieService();

    /**
     * this method convert list of movie models to list of movie DTO models
     *
     * @return List<MovieDTO>
     */
    public List<MovieDTO> getAllMovies() {
        List<Movie> movieList = movieService.viewAllAvailableMovies();
        LOG.info("MovieFacade method get all movies");
        return createMovieDTOList(movieList);
    }

    private List<MovieDTO> createMovieDTOList(List<Movie> movieList) {
        return movieList.stream().map(movie -> {
                    MovieDTO movieDTO = new MovieDTO();
                    movieDTO.setId(movie.getId());
                    movieDTO.setMovieName(movie.getMovieName());
                    movieDTO.setMovieGenre(movie.getMovieGenre());
                    movieDTO.setMovieDuration(movie.getMovieDuration());
                    movieDTO.setAgeLimit(movie.getAgeLimit());
                    movieDTO.setMovieDescription(movie.getMovieDescription());
                    return movieDTO;
                }
        ).collect(Collectors.toList());
    }
}
