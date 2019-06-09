package org.study.commands;

import java.time.LocalTime;
import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dto.HallDto;
import org.study.dto.SessionScheduleDto;
import org.study.facade.HallFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.models.enums.WeekDay;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class ViewHallSchemeCommandTest {
    @InjectMocks
    private ViewHallSchemeCommand viewHallScheme;

    @Mock
    private HttpServletRequest request;

    @Mock
    private SessionScheduleFacade sessionScheduleFacade;

    @Mock
    private HallFacade hallFacade;

    @Test
    public void shouldReturnScheme() {
        String expectedPath = "pages/hall_scheme.jsp";

        SessionScheduleDto sessionScheduleDto = new SessionScheduleDto();
        sessionScheduleDto.setScheduleId(1);
        sessionScheduleDto.setWeekDay(WeekDay.MONDAY);
        sessionScheduleDto.setMovieName("Avengers");
        sessionScheduleDto.setTime(LocalTime.of(9, 0));

        HallDto hallDto = new HallDto();
        hallDto.setHallId(1);
        hallDto.setHallName("Gold");
        hallDto.setMaxRow(5);
        hallDto.setMaxPlacesInRow(3);

        when(request.getParameter(ParametersNames.SCHEDULE_ID)).thenReturn("1");
        when(sessionScheduleFacade.getScheduleById(1)).thenReturn(sessionScheduleDto);
        when(hallFacade.getHallDataWithPriceAndOccuipedPlaces(1)).thenReturn(hallDto);

        String resultPath = viewHallScheme.execute(request);
        verify(request).setAttribute(ParametersNames.SCHEDULE, sessionScheduleDto);
        verify(request).setAttribute(ParametersNames.HALL, hallDto);
        assertThat(resultPath, equalTo(expectedPath));
    }


    @Test
    public void shouldReturnEmptyScheme() {
        String expectedPath = "pages/404.jsp";

        when(request.getParameter(ParametersNames.SCHEDULE_ID)).thenReturn(null);

        String resultPath = viewHallScheme.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnTruePermission() {
        assertTrue("permission is false", viewHallScheme.checkPermissions(request));
    }

}