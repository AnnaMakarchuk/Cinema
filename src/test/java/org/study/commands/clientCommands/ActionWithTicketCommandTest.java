package org.study.commands.clientCommands;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.study.dto.RegisteredUserDto;
import org.study.dto.TicketDto;
import org.study.facade.TicketFacade;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.models.enums.WeekDay;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class ActionWithTicketCommandTest {
    @InjectMocks
    private ActionWithTicketCommand actionWithTicketCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private TicketFacade ticketFacade;

    private List<TicketDto> ticketDtos;
    private RegisteredUserDto registeredUserDtoClient;

    @Before
    public void setUp() {
        registeredUserDtoClient = new RegisteredUserDto();
        registeredUserDtoClient.setUserId(1);
        registeredUserDtoClient.setUserName("Alisa");
        registeredUserDtoClient.setUserSurname("Test");
        registeredUserDtoClient.setGender(Gender.FEMALE);
        registeredUserDtoClient.setUserRole(UserRole.CLIENT);
        registeredUserDtoClient.setUserLogin("alisa");
        registeredUserDtoClient.setUserEMailAddress("a@i.ua");

        ticketDtos = new ArrayList<>();
        TicketDto ticketDto1 = new TicketDto();
        ticketDto1.setTicketId(1);
        ticketDto1.setTicketPrice(50);
        ticketDto1.setMovieName("Avengers");
        ticketDto1.setWeekDay(WeekDay.MONDAY);
        ticketDto1.setScheduleTime(LocalTime.of(9, 0));
        ticketDto1.setHallName("Gold");
        ticketDto1.setPlaceRow(4);
        ticketDto1.setPlaceNumber(4);
        ticketDtos.add(ticketDto1);

        TicketDto ticketDto2 = new TicketDto();
        ticketDto2.setTicketId(2);
        ticketDto2.setTicketPrice(100);
        ticketDto2.setMovieName("Avengers");
        ticketDto2.setWeekDay(WeekDay.MONDAY);
        ticketDto2.setScheduleTime(LocalTime.of(12, 0));
        ticketDto2.setHallName("Gold");
        ticketDto2.setPlaceRow(5);
        ticketDto2.setPlaceNumber(6);
        ticketDtos.add(ticketDto2);
    }

    @Test
    public void shouldReturnListTickets() {
        String expectedPath = "pages/client/client_tickets.jsp";
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersNames.USER)).thenReturn(registeredUserDtoClient);
        when(ticketFacade.getAllTicketsByUser(registeredUserDtoClient.getUserId())).thenReturn(ticketDtos);

        String resultPath = actionWithTicketCommand.execute(request);
        verify(request).setAttribute(ParametersNames.TICKETS, ticketDtos);
        assertThat(resultPath, equalTo(expectedPath));
    }
}