package org.study.commands.adminCommands;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dto.MovieDto;
import org.study.dto.SessionScheduleDto;
import org.study.dto.TimeDto;
import org.study.facade.MovieFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.models.enums.MovieGenre;
import org.study.models.enums.WeekDay;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class ViewCancelledScheduleCommandTest {
    @InjectMocks
    private ViewCancelledScheduleCommand viewCancelledScheduleCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private SessionScheduleFacade sessionScheduleFacade;

    @Mock
    private MovieFacade movieFacade;

    private List<MovieDto> movieDtosList;
    private List<SessionScheduleDto> sessionScheduleDtos;

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

        SessionScheduleDto sessionScheduleDto1 = new SessionScheduleDto();
        sessionScheduleDto1.setScheduleId(1);
        sessionScheduleDto1.setWeekDay(WeekDay.MONDAY);
        sessionScheduleDto1.setMovieName("Avengers");
        List<TimeDto> timeList = new ArrayList<>();
        timeList.add(new TimeDto(1, LocalTime.of(9, 0)));
        timeList.add(new TimeDto(3, LocalTime.of(15, 0)));
        sessionScheduleDto1.setTimeList(timeList);

        SessionScheduleDto sessionScheduleDto2 = new SessionScheduleDto();
        sessionScheduleDto2.setScheduleId(2);
        sessionScheduleDto2.setWeekDay(WeekDay.MONDAY);
        sessionScheduleDto2.setMovieName("Dark");
        List<TimeDto> timeList2 = new ArrayList<>();
        timeList2.add(new TimeDto(2, LocalTime.of(12, 0)));
        sessionScheduleDto2.setTimeList(timeList2);

        sessionScheduleDtos = new ArrayList<>();
        sessionScheduleDtos.add(sessionScheduleDto1);
        sessionScheduleDtos.add(sessionScheduleDto2);
    }

    @Test
    public void shouldViewCancelledSchedule() {
        String expectedPath = "pages/admin/admin_nonactive_schedule.jsp";

        when(sessionScheduleFacade.viewNonActiveSchedule()).thenReturn(sessionScheduleDtos);
        when(movieFacade.getAllMovies(true)).thenReturn(movieDtosList);

        String resultPath = viewCancelledScheduleCommand.execute(request);
        verify(request).setAttribute(ParametersNames.SCHEDULES, sessionScheduleDtos);
        verify(request).setAttribute(ParametersNames.MOVIES, movieDtosList);
        assertThat(resultPath, equalTo(expectedPath));
    }
}
