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
import org.study.facade.SessionScheduleFacade;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class ChangeScheduleCommandTest {
    @InjectMocks
    private ChangeScheduleCommand changeScheduleCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private SessionScheduleFacade sessionScheduleFacade;


    @Test
    public void shouldChangeSchedule() {
        String expectedPath = "pages/admin/admin_schedule_changed.jsp";

        when(request.getParameter(ParametersNames.MOVIE_ID)).thenReturn("1");
        when(request.getParameter(ParametersNames.SCHEDULE_ID)).thenReturn("1");
        when(sessionScheduleFacade.changeSchedule(1, 1, true)).thenReturn(true);

        String resultPath = changeScheduleCommand.execute(request);
        verify(sessionScheduleFacade).changeSchedule(1, 1, true);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldNotChangeSchedule() {
        String expectedPath = "pages/admin/admin_schedule_nochanged.jsp";

        when(request.getParameter(ParametersNames.MOVIE_ID)).thenReturn("1");
        when(request.getParameter(ParametersNames.SCHEDULE_ID)).thenReturn("1");
        when(sessionScheduleFacade.changeSchedule(1, 1, true)).thenReturn(false);

        String resultPath = changeScheduleCommand.execute(request);
        verify(sessionScheduleFacade).changeSchedule(1, 1, true);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnErrorPageNullMovie() {
        String expectedPath = "pages/404.jsp";
        when(request.getParameter(ParametersNames.MOVIE_ID)).thenReturn(null);

        String resultPath = changeScheduleCommand.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnErrorPageNullSchedule() {
        String expectedPath = "pages/404.jsp";
        when(request.getParameter(ParametersNames.SCHEDULE_ID)).thenReturn(null);

        String resultPath = changeScheduleCommand.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }

}