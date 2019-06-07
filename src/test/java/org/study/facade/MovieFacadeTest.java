package org.study.facade;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dto.MovieDto;
import org.study.models.Movie;
import org.study.models.enums.MovieGenre;
import org.study.services.MovieService;

@RunWith(MockitoJUnitRunner.class)
public class MovieFacadeTest {
    @InjectMocks
    private MovieFacade movieFacade;

    @Mock
    private MovieService movieService;

    @Before
    public void setUp() {
        Movie movie1 = new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no");
        Movie movie2 = new Movie(2, "Dark", MovieGenre.ACTION, 100, 16,
                "no");
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        when(movieService.viewAllAvailableMovies(true)).thenReturn(movieList);
    }

    @Test
    public void shouldReturnDTOObject() {
        assertThat(movieFacade.getAllMovies(true).get(0), instanceOf(MovieDto.class));
    }

    @Test
    public void shouldReturnMovieDTOList() {
        List<MovieDto> expected = new ArrayList<>();
        MovieDto movieDto1 = new MovieDto();
        movieDto1.setMovieId(1);
        movieDto1.setMovieName("Avengers");
        movieDto1.setMovieGenre(MovieGenre.ACTION);
        movieDto1.setMovieDuration(100);
        movieDto1.setAgeLimit(16);
        movieDto1.setMovieDescription("no");
        expected.add(movieDto1);

        MovieDto movieDto2 = new MovieDto();
        movieDto2.setMovieId(2);
        movieDto2.setMovieName("Dark");
        movieDto2.setMovieGenre(MovieGenre.ACTION);
        movieDto2.setMovieDuration(100);
        movieDto2.setAgeLimit(16);
        movieDto2.setMovieDescription("no");
        expected.add(movieDto2);

        List<MovieDto> result = movieFacade.getAllMovies(true);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallGETServiceMethod() {
        movieFacade.getAllMovies(true);
        verify(movieService).viewAllAvailableMovies(true);
    }

    @Test
    public void shouldCallAddServiceMethod() {
        movieFacade
                .createNewMovie("Toppen", "cartoon", 90, 16, "n");
        verify(movieService)
                .addNewMovie("Toppen", "cartoon", 90, 16, "n");
    }
}
