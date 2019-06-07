package org.study.facade;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dto.HallDto;
import org.study.models.Hall;
import org.study.services.HallService;

@RunWith(MockitoJUnitRunner.class)
public class HallFacadeTest {

    @InjectMocks
    private HallFacade hallFacade;

    @Mock
    private HallService hallService;

    @Before
    public void setUp() {
        Hall hall = new Hall(1, "Gold", 5, 3);
        when(hallService.getHallWithPriceAndPlaces(1)).thenReturn(hall);
    }

    @Test
    public void shouldReturnDTOObject() {
        assertThat(hallFacade.getHallDataWithPriceAndOccuipedPlaces(1), instanceOf(HallDto.class));
    }

    @Test
    public void shouldReturnHallDTO() {
        HallDto expected = new HallDto();
        expected.setHallId(1);
        expected.setHallName("Gold");
        expected.setMaxRow(5);
        expected.setMaxPlacesInRow(3);

        HallDto result = hallFacade.getHallDataWithPriceAndOccuipedPlaces(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallGETServiceMethod() {
        hallFacade.getHallDataWithPriceAndOccuipedPlaces(1);
        verify(hallService).getHallWithPriceAndPlaces(1);
    }
}
