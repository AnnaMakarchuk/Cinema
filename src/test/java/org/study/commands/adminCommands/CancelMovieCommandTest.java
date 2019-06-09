package org.study.commands.adminCommands;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import org.study.dto.RegisteredUserDto;
import org.study.dto.SessionScheduleDto;
import org.study.dto.TimeDto;
import org.study.facade.SessionScheduleFacade;
import org.study.facade.TicketFacade;
import org.study.facade.UserFacade;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.models.enums.WeekDay;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class CancelMovieCommandTest {

    @InjectMocks
    private CancelMovieCommand cancelMovieCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private SessionScheduleFacade sessionScheduleFacade;

    @Mock
    private UserFacade userFacade;

    @Mock
    private TicketFacade ticketFacade;

    private List<SessionScheduleDto> cancelledScheduleList;
    private List<RegisteredUserDto> registeredUserDtos;

    @Before
    public void setUp() {
        RegisteredUserDto registeredUserDto = new RegisteredUserDto();
        registeredUserDto.setUserId(1);
        registeredUserDto.setUserName("Alisa");
        registeredUserDto.setUserSurname("Test");
        registeredUserDto.setGender(Gender.FEMALE);
        registeredUserDto.setUserRole(UserRole.CLIENT);
        registeredUserDto.setUserLogin("alisa");
        registeredUserDto.setUserEMailAddress("a@i.ua");

        registeredUserDtos = new ArrayList<>();
        registeredUserDtos.add(registeredUserDto);

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

        cancelledScheduleList = new ArrayList<>();
        cancelledScheduleList.add(sessionScheduleDto1);
        cancelledScheduleList.add(sessionScheduleDto2);
    }

    @Test
    public void shouldCancelMovieAndReturnUserList() {
        String expectedPath = "pages/admin/admin_cancel_schedule.jsp";

        when(request.getParameter(ParametersNames.MOVIE_ID)).thenReturn("1");
        when(sessionScheduleFacade.cancelMovie(1)).thenReturn(cancelledScheduleList);
        List<Integer> scheduleId = cancelledScheduleList.stream()
                .map(SessionScheduleDto::getScheduleId)
                .collect(Collectors.toList());
        when(userFacade.createUserListWithCancelledSchedule(scheduleId)).thenReturn(registeredUserDtos);

        String resultPath = cancelMovieCommand.execute(request);
        verify(request).setAttribute(ParametersNames.SCHEDULES, cancelledScheduleList);
        verify(request).setAttribute(ParametersNames.CLIENTS, registeredUserDtos);
        verify(ticketFacade).deleteTicketBySessionScheduleID(scheduleId);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldFailCancelMovie() {
        String expectedPath = "pages/404.jsp";
        when(request.getParameter(ParametersNames.MOVIE_ID)).thenReturn(null);

        String resultPath = cancelMovieCommand.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }
}
