package org.study.services.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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
import org.study.dao.SessionScheduleDao;
import org.study.models.Hall;
import org.study.models.Movie;
import org.study.models.SessionSchedule;
import org.study.models.enums.MovieGenre;
import org.study.models.enums.WeekDay;

@RunWith(MockitoJUnitRunner.class)
public class SessionScheduleServiceImplTest {
    @InjectMocks
    private SessionScheduleServiceImpl sessionScheduleService;

    @Mock
    private SessionScheduleDao sessionScheduleDao;

    @Mock
    private MovieDao movieDao;

    private Hall hall;
    private Movie movie;
    private Movie movie2;

    @Before
    public void setUp() {
        hall = new Hall(1, "Gold", 5, 3);
        movie = new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no");
        movie2 = new Movie(2, "Avengers2", MovieGenre.ACTION, 350, 16,
                "no");
        SessionSchedule sessionSchedule1 = new SessionSchedule(1, WeekDay.MONDAY, LocalTime.of(9, 0),
                hall, movie);
        SessionSchedule sessionSchedule2 = new SessionSchedule(2, WeekDay.MONDAY, LocalTime.of(12, 0),
                hall, movie);
        SessionSchedule sessionSchedule3 = new SessionSchedule(3, WeekDay.MONDAY, LocalTime.of(15, 0),
                hall, movie);
        List<SessionSchedule> sessionScheduleList = new ArrayList<>();
        sessionScheduleList.add(sessionSchedule1);
        sessionScheduleList.add(sessionSchedule2);
        sessionScheduleList.add(sessionSchedule3);

        when(movieDao.getMovieById(1)).thenReturn(movie);
        when(movieDao.getMovieById(2)).thenReturn(movie2);


        when(sessionScheduleDao.getScheduleByMovieId(1, false)).thenReturn(sessionScheduleList);
        when(sessionScheduleDao.getAllByDay("monday")).thenReturn(sessionScheduleList);
        when(sessionScheduleDao.getAllNonActive(false)).thenReturn(sessionScheduleList);
        when(sessionScheduleDao.get(2)).thenReturn(sessionSchedule2);
        when(sessionScheduleDao.getScheduleTime(1)).thenReturn(LocalTime.of(15, 0));
        when(sessionScheduleDao.getScheduleTime(2)).thenReturn(LocalTime.of(17, 0));
    }

    @Test
    public void shouldCallUpdateGetMethodScheduleDAO() {
        sessionScheduleService.updateScheduleCancelMovie(1);
        verify(sessionScheduleDao).updateIsActive(1, false);
        verify(sessionScheduleDao).getScheduleByMovieId(1, false);
    }

    @Test
    public void updateSessionStatus() throws Exception {
        List<SessionSchedule> expected = new ArrayList<>();
        expected.add(new SessionSchedule(1, WeekDay.MONDAY, LocalTime.of(9, 0), hall, movie));
        expected.add(new SessionSchedule(2, WeekDay.MONDAY, LocalTime.of(12, 0), hall, movie));
        expected.add(new SessionSchedule(3, WeekDay.MONDAY, LocalTime.of(15, 0), hall, movie));

        List<SessionSchedule> result = sessionScheduleService.updateScheduleCancelMovie(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallGetAllByDayMethodScheduleDAO() {
        sessionScheduleService.viewAllScheduleByWeekDay("monday");
        verify(sessionScheduleDao).getAllByDay("monday");
    }

    @Test
    public void viewAllScheduleByWeekDay() throws Exception {
        List<SessionSchedule> expected = new ArrayList<>();
        expected.add(new SessionSchedule(1, WeekDay.MONDAY, LocalTime.of(9, 0), hall, movie));
        expected.add(new SessionSchedule(2, WeekDay.MONDAY, LocalTime.of(12, 0), hall, movie));
        expected.add(new SessionSchedule(3, WeekDay.MONDAY, LocalTime.of(15, 0), hall, movie));

        List<SessionSchedule> result = sessionScheduleService.viewAllScheduleByWeekDay("monday");

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallGetAllNonActiveMethodScheduleDAO() {
        sessionScheduleService.createNonActiveSchedule(false);
        verify(sessionScheduleDao).getAllNonActive(false);
    }

    @Test
    public void createNonActiveSchedule() {
        List<SessionSchedule> expected = new ArrayList<>();
        expected.add(new SessionSchedule(1, WeekDay.MONDAY, LocalTime.of(9, 0), hall, movie));
        expected.add(new SessionSchedule(2, WeekDay.MONDAY, LocalTime.of(12, 0), hall, movie));
        expected.add(new SessionSchedule(3, WeekDay.MONDAY, LocalTime.of(15, 0), hall, movie));

        List<SessionSchedule> result = sessionScheduleService.createNonActiveSchedule(false);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldUpdateLastDaySchedule() {
        boolean result = sessionScheduleService.updateSession(1, 1, true);
        assertThat(result, is(true));
    }

    @Test
    public void shouldUpdateScheduleWithTooLongMovie() {
        boolean result = sessionScheduleService.updateSession(1, 2, true);
        assertThat(result, is(false));
    }

    @Test
    public void shouldUpdateScheduleMovie() {
        boolean result = sessionScheduleService.updateSession(1, 1, true);
        assertThat(result, is(true));
    }

    @Test
    public void shouldCallGetByIdScheduleDAO() {
        sessionScheduleService.getByScheduleId(2);
        verify(sessionScheduleDao).get(2);
    }

    @Test
    public void shouldReturnScheduleById() {
        SessionSchedule expected =  new SessionSchedule(2, WeekDay.MONDAY, LocalTime.of(12, 0),
                hall, movie);

        SessionSchedule result = sessionScheduleService.getByScheduleId(2);

        assertThat(result, equalTo(expected));
    }
}
