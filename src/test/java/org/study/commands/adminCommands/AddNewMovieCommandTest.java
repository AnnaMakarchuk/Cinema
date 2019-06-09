package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.facade.MovieFacade;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class AddNewMovieCommandTest {
    @InjectMocks
    private AddNewMovieCommand addNewMovieCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private MovieFacade movieFacade;

    @Test
    public void shouldTestSuccessfulMovieAdd() {
        String expectedPath = "pages/admin/admin_movieadded.jsp";

        when(request.getParameter(ParametersNames.MOVIE_NAME)).thenReturn("Potter");
        when(request.getParameter(ParametersNames.MOVIE_GENRE)).thenReturn("fantasy");
        when(request.getParameter(ParametersNames.DURATION)).thenReturn("250");
        when(request.getParameter(ParametersNames.AGE)).thenReturn("16");
        when(request.getParameter(ParametersNames.DESCRIPTION)).thenReturn("Nonono");

        String resultPath = addNewMovieCommand.execute(request);
        verify(movieFacade).createNewMovie("Potter", "fantasy", 250, 16,
                "Nonono");
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldTestFailedMovieAdd() {
        String expectedPath = "pages/401.jsp";

        when(request.getParameter(ParametersNames.MOVIE_NAME)).thenReturn("Potter");
        when(request.getParameter(ParametersNames.MOVIE_GENRE)).thenReturn("fantasy");
        when(request.getParameter(ParametersNames.DURATION)).thenReturn("500");
        when(request.getParameter(ParametersNames.AGE)).thenReturn("35");
        when(request.getParameter(ParametersNames.DESCRIPTION)).thenReturn("Nonono");

        String resultPath = addNewMovieCommand.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }
}
