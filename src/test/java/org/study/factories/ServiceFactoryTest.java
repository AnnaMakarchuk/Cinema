package org.study.factories;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.study.services.AdministratorService;
import org.study.services.HallService;
import org.study.services.MovieService;
import org.study.services.SessionScheduleService;
import org.study.services.TicketService;
import org.study.services.UserService;

public class ServiceFactoryTest {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Test
    public void shouldGetServices() {
        assertThat(serviceFactory.getAdministratorService(), instanceOf(AdministratorService.class));
        assertThat(serviceFactory.getHallService(), instanceOf(HallService.class));
        assertThat(serviceFactory.getMovieService(), instanceOf(MovieService.class));
        assertThat(serviceFactory.getSessionScheduleService(), instanceOf(SessionScheduleService.class));
        assertThat(serviceFactory.getTicketService(), instanceOf(TicketService.class));
        assertThat(serviceFactory.getUserService(), instanceOf(UserService.class));
    }
}
