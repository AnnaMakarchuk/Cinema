package org.study.commands;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
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
import org.study.facade.SessionScheduleFacade;
import org.study.models.enums.WeekDay;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class ViewSchedulePageCommandTest {
    @InjectMocks
    private ViewSchedulePageCommand viewSchedulePage;

    @Mock
    private HttpServletRequest request;

    @Mock
    private SessionScheduleFacade sessionScheduleFacade;

    private List<SessionScheduleDto> allScheduleByDay;

    @Before
    public void setUp() {
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

        allScheduleByDay = new ArrayList<>();
        allScheduleByDay.add(sessionScheduleDto1);
        allScheduleByDay.add(sessionScheduleDto2);
    }

    @Test
    public void shouldReturnScheduleByDay() {
        String expectedPath = "pages/schedule_day.jsp";

        when(request.getParameter(ParametersNames.SCHEDULE_DAY)).thenReturn("sunday");
        when(sessionScheduleFacade.getAllScheduleByDay("sunday")).thenReturn(allScheduleByDay);

        String resultPath = viewSchedulePage.execute(request);
        verify(request).setAttribute(ParametersNames.SCHEDULE_WEEKDAY, "sunday");
        verify(request).setAttribute(ParametersNames.SCHEDULES, allScheduleByDay);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnScheduleByMonday() {
        String expectedPath = "pages/schedule_day.jsp";

        when(request.getParameter(ParametersNames.SCHEDULE_DAY)).thenReturn(null);
        when(sessionScheduleFacade.getAllScheduleByDay("monday")).thenReturn(allScheduleByDay);

        String resultPath = viewSchedulePage.execute(request);
        verify(request).setAttribute(ParametersNames.SCHEDULE_WEEKDAY, "monday");
        verify(request).setAttribute(ParametersNames.SCHEDULES, allScheduleByDay);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnTruePermission() {
        assertTrue("permission is false", viewSchedulePage.checkPermissions(request));
    }
}
