package org.study.services.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
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
import org.study.dao.TicketDao;
import org.study.models.Hall;
import org.study.models.Movie;
import org.study.models.Place;
import org.study.models.SessionSchedule;
import org.study.models.Ticket;
import org.study.models.enums.MovieGenre;
import org.study.models.enums.WeekDay;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketDao ticketDao;

    private SessionSchedule sessionSchedule1;
    private SessionSchedule sessionSchedule2;
    private List<Ticket> ticketList;

    @Before
    public void setUp() {
        Hall hall = new Hall(1, "Gold", 5, 6);
        Movie movie = new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no");
        sessionSchedule1 = new SessionSchedule(1, WeekDay.MONDAY, LocalTime.of(9, 0), hall, movie);
        sessionSchedule2 = new SessionSchedule(2, WeekDay.MONDAY, LocalTime.of(12, 0), hall, movie);
        Ticket ticket1 = new Ticket(1, sessionSchedule1, new Place(4, 4), 50);
        Ticket ticket2 = new Ticket(2, sessionSchedule2, new Place(5, 6), 100);
        ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        when(ticketDao.get(1)).thenReturn(ticketList);
        when(ticketDao.countOccupiedPlaces()).thenReturn(10);
        when(ticketDao.getAllTicketsByPages(2, 10)).thenReturn(ticketList);
    }

    @Test
    public void shouldCallGetAllMethodTicketDAO() {
        ticketService.viewAllTicketsByUser(1);
        verify(ticketDao).get(1);
    }

    @Test
    public void viewAllTicketsByUser() {
        List<Ticket> expected = new ArrayList<>();
        expected.add(new Ticket(1, sessionSchedule1, new Place(4, 4), 50));
        expected.add(new Ticket(2, sessionSchedule2, new Place(5, 6), 100));

        List<Ticket> result = ticketService.viewAllTicketsByUser(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallCreateMethodTicketDAO() {
        ticketService.createTicketByUser(1, 1, ticketList);
        verify(ticketDao).create(1, 1, ticketList.get(0));
        verify(ticketDao).create(1, 1, ticketList.get(1));
    }

    @Test
    public void shouldCallDeleteByUserMethodTicketDAO() {
        ticketService.deleteTicketByUser(1);
        verify(ticketDao).delete(1);
    }

    @Test
    public void shouldCallCountMethodTicketDAO() {
        ticketService.countOccupiedPlaces();
        verify(ticketDao).countOccupiedPlaces();
    }

    @Test
    public void countOccupiedPlaces() {
        int result = ticketService.countOccupiedPlaces();
        assertThat(result, is(10));
    }

    @Test
    public void shouldCallDeleteByAdminMethodTicketDAO() {
        List<Integer> sessionIdList = Arrays.asList(1, 2, 4);
        ticketService.deleteTicketByAdmin(sessionIdList);
        verify(ticketDao).deleteBySchedule(sessionIdList.get(0));
        verify(ticketDao).deleteBySchedule(sessionIdList.get(1));
        verify(ticketDao).deleteBySchedule(sessionIdList.get(2));

    }

    @Test
    public void shouldCallGetAllByPagesMethodTicketDAO() {
        ticketService.viewAllTicketsByPages(2, 10);
        verify(ticketDao).getAllTicketsByPages(2, 10);
    }

    @Test
    public void viewAllTicketsByPages() throws Exception {
        List<Ticket> expected = new ArrayList<>();
        expected.add(new Ticket(1, sessionSchedule1, new Place(4, 4), 50));
        expected.add(new Ticket(2, sessionSchedule2, new Place(5, 6), 100));

        List<Ticket> result = ticketService.viewAllTicketsByPages(2, 10);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallAddDAOMethod() {
        ticketService.createTicketByUser(1, 1, ticketList);
        verify(ticketDao).create(1, 1, ticketList.get(0));
        verify(ticketDao).create(1, 1, ticketList.get(1));

    }
}
