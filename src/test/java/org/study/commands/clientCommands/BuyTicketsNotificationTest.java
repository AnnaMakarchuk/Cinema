package org.study.commands.clientCommands;

import com.sun.org.apache.regexp.internal.RE;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.study.dto.PlaceDto;
import org.study.dto.PositionDto;
import org.study.dto.RegisteredUserDto;
import org.study.dto.TicketDto;
import org.study.facade.TicketFacade;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.models.enums.WeekDay;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class BuyTicketsNotificationTest {

    @InjectMocks
    private BuyTicketsNotification buyTicketsNotification;

    @Mock
    private HttpServletRequest request;

    @Mock
    private TicketFacade ticketFacade;

    @Mock
    private HttpSession session;


    private RegisteredUserDto registeredUserDto;
    private List<TicketDto> expectedTicket;

    @Before
    public void setUp() {
        registeredUserDto = new RegisteredUserDto();
        registeredUserDto.setUserId(1);
        registeredUserDto.setUserName("Alisa");
        registeredUserDto.setUserSurname("Test");
        registeredUserDto.setGender(Gender.FEMALE);
        registeredUserDto.setUserRole(UserRole.CLIENT);
        registeredUserDto.setUserLogin("alisa");
        registeredUserDto.setUserEMailAddress("a@i.ua");

        expectedTicket = new ArrayList<>();
        TicketDto ticketDto1 = new TicketDto();
        ticketDto1.setTicketId(1);
        ticketDto1.setTicketPrice(50);
        ticketDto1.setMovieName("Avengers");
        ticketDto1.setWeekDay(WeekDay.MONDAY);
        ticketDto1.setScheduleTime(LocalTime.of(9, 0));
        ticketDto1.setHallName("Gold");
        ticketDto1.setPlaceRow(4);
        ticketDto1.setPlaceNumber(4);
        expectedTicket.add(ticketDto1);
    }

    @Test
    public void shouldAddNewTickets() throws IOException {
        String expectedPath = "pages/client/client_tickets.jsp";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersNames.USER)).thenReturn(registeredUserDto);

        List<PlaceDto> placeDtos = new ArrayList<>();
        placeDtos.add(new PlaceDto(3, 4));
        PositionDto expected = new PositionDto();
        expected.setScheduleId(1);
        expected.setPlaces(placeDtos);
        String expectedPlace = "{\"scheduleId\": 1, \"places\": [{\"row\": 3, \"place\": 4}]}";
        Reader reader = new StringReader(expectedPlace);
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        when(ticketFacade.addNewTickets(registeredUserDto.getUserId(), 1, placeDtos)).thenReturn(expectedTicket);

        String resultPath = buyTicketsNotification.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
        verify(ticketFacade).addNewTickets(registeredUserDto.getUserId(), 1, placeDtos);
        verify(request).setAttribute(ParametersNames.TICKETS, expectedTicket);
    }

    @Test
    public void shouldFailAddNewTickets() throws IOException {
        String expectedPath = "pages/404.jsp";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersNames.USER)).thenReturn(registeredUserDto);

        List<PlaceDto> placeDtos = new ArrayList<>();
        placeDtos.add(new PlaceDto(3, 4));
        PositionDto expected = new PositionDto();
        expected.setScheduleId(1);
        expected.setPlaces(placeDtos);
        String expectedPlace = "{\"places\": [{\"row\": 3, \"place\": 4}]}";
        Reader reader = new StringReader(expectedPlace);
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        when(ticketFacade.addNewTickets(registeredUserDto.getUserId(), 1, placeDtos)).thenReturn(expectedTicket);

        String resultPath = buyTicketsNotification.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }

}