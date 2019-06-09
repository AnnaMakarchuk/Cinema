package org.study.commands.adminCommands;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import org.study.dto.MovieDto;
import org.study.facade.MovieFacade;
import org.study.models.enums.MovieGenre;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class ViewPageAddNewMovieCommandTest {
    @InjectMocks
    private ViewPageAddNewMovieCommand viewPageAddNewMovieCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private MovieFacade movieFacade;

    private List<MovieDto> movieDtosList;

    @Before
    public void setUp() {
        MovieDto movieDto1 = new MovieDto();
        movieDto1.setMovieId(1);
        movieDto1.setMovieName("Avengers");
        movieDto1.setMovieGenre(MovieGenre.ACTION);
        movieDto1.setMovieDuration(100);
        movieDto1.setAgeLimit(16);
        movieDto1.setMovieDescription("no");

        MovieDto movieDto2 = new MovieDto();
        movieDto2.setMovieId(2);
        movieDto2.setMovieName("Dark");
        movieDto2.setMovieGenre(MovieGenre.ACTION);
        movieDto2.setMovieDuration(100);
        movieDto2.setAgeLimit(16);
        movieDto2.setMovieDescription("no");

        movieDtosList = new ArrayList<>();
        movieDtosList.add(movieDto1);
        movieDtosList.add(movieDto2);
    }

    @Test
    public void shouldShowMovieDtoList() {
        String expectedPath = "pages/admin/admin_add_movie.jsp";
        when(movieFacade.getAllMovies(true)).thenReturn(movieDtosList);

        String resultPath = viewPageAddNewMovieCommand.execute(request);
        verify(request).setAttribute(ParametersNames.MOVIES, movieDtosList);
        assertThat(resultPath, equalTo(expectedPath));
    }
}
