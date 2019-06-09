package org.study.commands.adminCommands;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
import org.study.dto.TicketDto;
import org.study.facade.TicketFacade;
import org.study.models.enums.WeekDay;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class ViewAvailableTicketsCommandTest {
    @InjectMocks
    private ViewAvailableTicketsCommand viewAvailableTicketsCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private TicketFacade ticketFacade;

    private List<TicketDto> ticketDtos;

    @Before
    public void setUp() {
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
    public void shouldReturnSTicketsByPage() {
        String expectedPath = "pages/admin/admin_tickets_list.jsp";
        when(request.getParameter(ParametersNames.PAGE)).thenReturn("3");
        when(ticketFacade.allTicketsWithPagination(3)).thenReturn(ticketDtos);

        String resultPath = viewAvailableTicketsCommand.execute(request);
        verify(request).setAttribute(ParametersNames.TICKETS, ticketDtos);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnSTicketsByFirstPage() {
        String expectedPath = "pages/admin/admin_tickets_list.jsp";
        when(request.getParameter(ParametersNames.PAGE)).thenReturn(null);
        when(ticketFacade.allTicketsWithPagination(1)).thenReturn(ticketDtos);

        String resultPath = viewAvailableTicketsCommand.execute(request);
        verify(request).setAttribute(ParametersNames.TICKETS, ticketDtos);
        assertThat(resultPath, equalTo(expectedPath));
    }
}