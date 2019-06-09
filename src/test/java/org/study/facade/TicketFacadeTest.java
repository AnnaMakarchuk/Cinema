package org.study.facade;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.study.dto.TicketDto;
import org.study.models.Hall;
import org.study.models.Movie;
import org.study.models.Place;
import org.study.models.SessionSchedule;
import org.study.models.Ticket;
import org.study.models.enums.MovieGenre;
import org.study.models.enums.WeekDay;
import org.study.services.TicketService;

@RunWith(MockitoJUnitRunner.class)
public class TicketFacadeTest {
    @InjectMocks
    private TicketFacade ticketFacade;

    @Mock
    private TicketService ticketService;

    @Before
    public void setUp() {
        Hall hall = new Hall(1, "Gold", 5, 6);
        Movie movie = new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no");
        SessionSchedule sessionSchedule1 = new SessionSchedule(1, WeekDay.MONDAY,
                LocalTime.of(9, 0), hall, movie);
        SessionSchedule sessionSchedule2 = new SessionSchedule(2, WeekDay.MONDAY,
                LocalTime.of(12, 0), hall, movie);
        Ticket ticket1 = new Ticket(1, sessionSchedule1, new Place(4, 4), 50);
        Ticket ticket2 = new Ticket(2, sessionSchedule2, new Place(5, 6), 100);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        when(ticketService.viewAllTicketsByUser(1)).thenReturn(ticketList);
        when(ticketService.countOccupiedPlaces()).thenReturn(50);
        when(ticketService.viewAllTicketsByPages(2, 10)).thenReturn(ticketList);
    }

    @Test
    public void shouldCallViewByUserServiceMethod() {
        ticketFacade.getAllTicketsByUser(1);
        verify(ticketService).viewAllTicketsByUser(1);
    }

    @Test
    public void shouldGetTicketsByUser() {
        List<TicketDto> expected = new ArrayList<>();
        TicketDto ticketDto1 = new TicketDto();
        ticketDto1.setTicketId(1);
        ticketDto1.setTicketPrice(50);
        ticketDto1.setMovieName("Avengers");
        ticketDto1.setWeekDay(WeekDay.MONDAY);
        ticketDto1.setScheduleTime(LocalTime.of(9, 0));
        ticketDto1.setHallName("Gold");
        ticketDto1.setPlaceRow(4);
        ticketDto1.setPlaceNumber(4);
        expected.add(ticketDto1);

        TicketDto ticketDto2 = new TicketDto();
        ticketDto2.setTicketId(2);
        ticketDto2.setTicketPrice(100);
        ticketDto2.setMovieName("Avengers");
        ticketDto2.setWeekDay(WeekDay.MONDAY);
        ticketDto2.setScheduleTime(LocalTime.of(12, 0));
        ticketDto2.setHallName("Gold");
        ticketDto2.setPlaceRow(5);
        ticketDto2.setPlaceNumber(6);
        expected.add(ticketDto2);

        List<TicketDto> result = ticketFacade.getAllTicketsByUser(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallDeleteByUserServiceMethod() {
        ticketFacade.deleteTicketByID(2);
        verify(ticketService).deleteTicketByUser(2);
    }

    @Test
    public void shouldCallDeleteByScheduleServiceMethod() {
        List<Integer> scheduleIdList = Arrays.asList(1, 3, 5);
        ticketFacade.deleteTicketBySessionScheduleID(scheduleIdList);
        verify(ticketService).deleteTicketByAdmin(scheduleIdList);
    }

    @Test
    public void shouldCallCountServiceMethod() {
        ticketFacade.countPagesQuantity();
        verify(ticketService).countOccupiedPlaces();
    }

    @Test
    public void shouldCountPages() {
        int expected = 5;

        int result = ticketFacade.countPagesQuantity();

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldReturnTicketsWithPagination() {
        List<TicketDto> expected = new ArrayList<>();
        TicketDto ticketDto1 = new TicketDto();
        ticketDto1.setTicketId(1);
        ticketDto1.setTicketPrice(50);
        ticketDto1.setMovieName("Avengers");
        ticketDto1.setWeekDay(WeekDay.MONDAY);
        ticketDto1.setScheduleTime(LocalTime.of(9, 0));
        ticketDto1.setHallName("Gold");
        ticketDto1.setPlaceRow(4);
        ticketDto1.setPlaceNumber(4);
        expected.add(ticketDto1);

        TicketDto ticketDto2 = new TicketDto();
        ticketDto2.setTicketId(2);
        ticketDto2.setTicketPrice(100);
        ticketDto2.setMovieName("Avengers");
        ticketDto2.setWeekDay(WeekDay.MONDAY);
        ticketDto2.setScheduleTime(LocalTime.of(12, 0));
        ticketDto2.setHallName("Gold");
        ticketDto2.setPlaceRow(5);
        ticketDto2.setPlaceNumber(6);
        expected.add(ticketDto2);

        List<TicketDto> result = ticketFacade.allTicketsWithPagination(2);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallAddServiceMethod() {
        List<PlaceDto> placeDtos = new ArrayList<>();
        placeDtos.add(new PlaceDto(3, 4));
        PositionDto expected = new PositionDto();
        expected.setScheduleId(1);
        expected.setPlaces(placeDtos);

        List<Place> places =  new ArrayList<>();
        places.add(new Place(3, 4));

        Ticket ticket1 = new Ticket(new Place(4, 4), 50);
        Ticket ticket2 = new Ticket(new Place(5, 6), 100);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        ticketFacade.addNewTickets(1, 1, placeDtos);

        verify(ticketService).createTicketByUser(1, 1, places);
    }
}