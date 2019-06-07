package org.study.facade;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dto.SessionScheduleDto;
import org.study.dto.TimeDto;
import org.study.models.Hall;
import org.study.models.Movie;
import org.study.models.SessionSchedule;
import org.study.models.enums.MovieGenre;
import org.study.models.enums.WeekDay;
import org.study.services.SessionScheduleService;

@RunWith(MockitoJUnitRunner.class)
public class SessionScheduleFacadeTest {
    @InjectMocks
    private SessionScheduleFacade sessionScheduleFacade;

    @Mock
    private SessionScheduleService sessionScheduleService;

    @Before
    public void setUp() {
        Hall hall = new Hall(1, "Gold", 5, 3);
        Movie movie = new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no");
        Movie movie2 = new Movie(2, "Dark", MovieGenre.ACTION, 100, 16,
                "no");
        SessionSchedule sessionSchedule1 = new SessionSchedule(1, WeekDay.MONDAY, LocalTime.of(9, 0),
                hall, movie);
        SessionSchedule sessionSchedule2 = new SessionSchedule(2, WeekDay.MONDAY, LocalTime.of(12, 0),
                hall, movie2);
        SessionSchedule sessionSchedule3 = new SessionSchedule(3, WeekDay.MONDAY, LocalTime.of(15, 0),
                hall, movie);
        List<SessionSchedule> sessionScheduleList = new ArrayList<>();
        sessionScheduleList.add(sessionSchedule1);
        sessionScheduleList.add(sessionSchedule2);
        sessionScheduleList.add(sessionSchedule3);

        List<SessionSchedule> cancelSessionScheduleList = new ArrayList<>();
        cancelSessionScheduleList.add(sessionSchedule1);

        when(sessionScheduleService.viewAllScheduleByWeekDay("MONDAY")).thenReturn(sessionScheduleList);
        when(sessionScheduleService.updateScheduleCancelMovie(1)).thenReturn(cancelSessionScheduleList);
        when(sessionScheduleService.createNonActiveSchedule(false)).thenReturn(cancelSessionScheduleList);
        when(sessionScheduleService.updateSession(1, 1, true)).thenReturn(false);
        when(sessionScheduleService.getByScheduleId(1)).thenReturn(sessionSchedule1);
    }

    @Test
    public void shouldCallGetByDayServiceMethod() {
        sessionScheduleFacade.getAllScheduleByDay("monday");
        verify(sessionScheduleService).viewAllScheduleByWeekDay("MONDAY");
    }

    @Test
    public void shouldCheckScheduleByDayListSize() {
        List<SessionScheduleDto> result = sessionScheduleFacade.getAllScheduleByDay("monday");
        assertThat(result.size(), equalTo(2));
    }

    @Test
    public void shouldReturnListOfSchedulesByDay() {
        List<SessionScheduleDto> expected = new ArrayList<>();
        SessionScheduleDto sessionScheduleDto1 = new SessionScheduleDto();
        sessionScheduleDto1.setScheduleId(1);
        sessionScheduleDto1.setWeekDay(WeekDay.MONDAY);
        sessionScheduleDto1.setMovieName("Avengers");
        List<TimeDto> timeList = new ArrayList<>();
        timeList.add(new TimeDto(1, LocalTime.of(9, 0)));
        timeList.add(new TimeDto(3, LocalTime.of(15, 0)));
        sessionScheduleDto1.setTimeList(timeList);
        expected.add(sessionScheduleDto1);

        SessionScheduleDto sessionScheduleDto2 = new SessionScheduleDto();
        sessionScheduleDto2.setScheduleId(2);
        sessionScheduleDto2.setWeekDay(WeekDay.MONDAY);
        sessionScheduleDto2.setMovieName("Dark");
        List<TimeDto> timeList2 = new ArrayList<>();
        timeList2.add(new TimeDto(2, LocalTime.of(12, 0)));
        sessionScheduleDto2.setTimeList(timeList2);
        expected.add(sessionScheduleDto2);

        List<SessionScheduleDto> result = sessionScheduleFacade.getAllScheduleByDay("monday");

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallUpdateServiceMethod() {
        sessionScheduleFacade.cancelMovie(1);
        verify(sessionScheduleService).updateScheduleCancelMovie(1);
    }

    @Test
    public void shouldReturnListOfCancelledSchedule() {
        List<SessionScheduleDto> expected = new ArrayList<>();
        SessionScheduleDto sessionScheduleDto1 = new SessionScheduleDto();
        sessionScheduleDto1.setScheduleId(1);
        sessionScheduleDto1.setWeekDay(WeekDay.MONDAY);
        sessionScheduleDto1.setMovieName("Avengers");
        sessionScheduleDto1.setTime(LocalTime.of(9, 0));
        expected.add(sessionScheduleDto1);

        List<SessionScheduleDto> result = sessionScheduleFacade.cancelMovie(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallGetNonActiveServiceMethod() {
        sessionScheduleFacade.viewNonActiveSchedule();
        verify(sessionScheduleService).createNonActiveSchedule(false);
    }

    @Test
    public void shouldReturnNonActiveSchedule() {
        List<SessionScheduleDto> expected = new ArrayList<>();
        SessionScheduleDto sessionScheduleDto1 = new SessionScheduleDto();
        sessionScheduleDto1.setScheduleId(1);
        sessionScheduleDto1.setWeekDay(WeekDay.MONDAY);
        sessionScheduleDto1.setMovieName("Avengers");
        sessionScheduleDto1.setTime(LocalTime.of(9, 0));
        expected.add(sessionScheduleDto1);

        List<SessionScheduleDto> result = sessionScheduleFacade.viewNonActiveSchedule();

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallUpdateMovieServiceMethod() {
        sessionScheduleFacade.changeSchedule(1, 1, true);
        verify(sessionScheduleService).updateSession(1, 1, true);
    }

    @Test
    public void changeSchedule() {
        boolean change = sessionScheduleFacade.changeSchedule(1, 1, true);
        assertFalse(change);
    }

    @Test
    public void shouldCallGetByIdServiceMethod() {
        sessionScheduleFacade.getScheduleById(1);
        verify(sessionScheduleService).getByScheduleId(1);
    }

    @Test
    public void shouldGetScheduleById() {
        SessionScheduleDto expected = new SessionScheduleDto();
        expected.setScheduleId(1);
        expected.setWeekDay(WeekDay.MONDAY);
        expected.setMovieName("Avengers");
        expected.setTime(LocalTime.of(9, 0));

        SessionScheduleDto result = sessionScheduleFacade.getScheduleById(1);

        assertThat(result, equalTo(expected));
    }
}
