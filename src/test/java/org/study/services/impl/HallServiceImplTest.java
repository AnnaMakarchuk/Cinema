package org.study.services.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dao.HallDao;
import org.study.dao.PriceDao;
import org.study.dao.SessionScheduleDao;
import org.study.dao.TicketDao;
import org.study.models.Hall;
import org.study.models.Movie;
import org.study.models.Place;
import org.study.models.SessionSchedule;
import org.study.models.Ticket;
import org.study.models.enums.MovieGenre;
import org.study.models.enums.WeekDay;

@RunWith(MockitoJUnitRunner.class)
public class HallServiceImplTest {
    @InjectMocks
    private HallServiceImpl hallService;

    @Mock
    private HallDao hallDao;

    @Mock
    private SessionScheduleDao sessionScheduleDao;

    @Mock
    private PriceDao priceDao;

    @Mock
    private TicketDao ticketDao;

    @Before
    public void setUp() {
        Hall hall = new Hall(1, "Gold", 3, 3);
        Movie movie = new Movie(1, "Avengers", MovieGenre.ACTION, 100, 16,
                "no");
        SessionSchedule sessionSchedule = new SessionSchedule(1, WeekDay.MONDAY, LocalTime.of(9, 0),
                hall, movie);
        Ticket ticket1 = new Ticket(1, sessionSchedule, new Place(3, 2), 50);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);

        when(sessionScheduleDao.get(1)).thenReturn(sessionSchedule);
        when(hallDao.get(1)).thenReturn(hall);
        when(ticketDao.getTicketsBySchedule(1)).thenReturn(ticketList);
        when(priceDao.get(anyInt(), anyInt())).thenReturn(50.00);
    }

    @Test
    public void shouldCallMethodsDAO() {
        hallService.getHallWithPriceAndPlaces(1);
        verify(sessionScheduleDao).get(1);
        verify(hallDao).get(1);
        verify(ticketDao).getTicketsBySchedule(1);
        verify(priceDao, times(3)).get(anyInt(), anyInt());
    }

    @Test
    public void getHallWithPriceAndPlaces() {
        Hall expected = new Hall(1, "Gold", 3, 3);
        List<Place> places = new ArrayList<>();
        places.add(new Place(4, 4));
        expected.setOccupiedPlaces(places);
        Map<Integer, Double> priceType = new HashMap<>();
        priceType.put(1, 50.00);
        priceType.put(2, 50.00);
        priceType.put(3, 50.00);
        expected.setPriceType(priceType);

        Hall result = hallService.getHallWithPriceAndPlaces(1);

        assertThat(result, equalTo(expected));
    }
}
