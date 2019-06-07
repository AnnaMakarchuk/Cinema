package org.study.services.impl;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dao.MovieDao;
import org.study.dao.MovieGenreDao;
import org.study.models.Movie;
import org.study.models.enums.MovieGenre;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {
    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieDao movieDao;

    @Mock
    private MovieGenreDao movieGenreDao;

    @Before
    public void setUp() {
        Movie movie1 = new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no");
        Movie movie2 = new Movie(2, "Dark", MovieGenre.ACTION, 100, 16,
                "no");
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        when(movieDao.getAll(true)).thenReturn(movieList);
        when(movieGenreDao.get("comedy")).thenReturn(1);

    }

    @Test
    public void shouldCallGetAllMethodMovieDAO() {
        movieService.viewAllAvailableMovies(true);
        verify(movieDao).getAll(true);
    }

    @Test
    public void shouldReturnAllAvailableMovies() {
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no"));
        expected.add(new Movie(2, "Dark", MovieGenre.ACTION, 100, 16,
                "no"));
        List<Movie> result = movieService.viewAllAvailableMovies(true);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallCreateMethodMovieDAO() {
        movieService.addNewMovie("name", "comedy", 150, 10, "no");
        verify(movieGenreDao).get("comedy");
        verify(movieDao).create(1, new Movie("name", MovieGenre.COMEDY, 150,
                10, "no"));
    }
}
