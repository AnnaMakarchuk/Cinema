package org.study.factories;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.study.facade.HallFacade;
import org.study.facade.MovieFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.facade.TicketFacade;
import org.study.facade.UserFacade;

public class FacadeFactoryTest {

    private FacadeFactory facadeFactory = FacadeFactory.getInstance();

    @Test
    public void shouldGetFacadeClass() {
        assertThat(facadeFactory.getUserFacade(), instanceOf(UserFacade.class));
        assertThat(facadeFactory.getMovieFacade(), instanceOf(MovieFacade.class));
        assertThat(facadeFactory.getSessionScheduleFacade(), instanceOf(SessionScheduleFacade.class));
        assertThat(facadeFactory.getTicketFacade(), instanceOf(TicketFacade.class));
        assertThat(facadeFactory.getHallFacade(), instanceOf(HallFacade.class));
    }
}
