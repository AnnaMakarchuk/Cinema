package org.study.factories;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.study.dao.AdministratorDao;
import org.study.dao.HallDao;
import org.study.dao.MovieDao;
import org.study.dao.MovieGenreDao;
import org.study.dao.PriceDao;
import org.study.dao.SessionScheduleDao;
import org.study.dao.TicketDao;
import org.study.dao.UserDao;
import org.study.dao.UserRoleDao;

public class DaoFactoryTest {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Test
    public void shouldGetDAOs() {
        assertThat(daoFactory.getAdministratorDao(), instanceOf(AdministratorDao.class));
        assertThat(daoFactory.getUserDao(), instanceOf(UserDao.class));
        assertThat(daoFactory.getUserRoleDao(), instanceOf(UserRoleDao.class));
        assertThat(daoFactory.getSessionScheduleDao(), instanceOf(SessionScheduleDao.class));
        assertThat(daoFactory.getHallDao(), instanceOf(HallDao.class));
        assertThat(daoFactory.getMovieDao(), instanceOf(MovieDao.class));
        assertThat(daoFactory.getMovieGenreDao(), instanceOf(MovieGenreDao.class));
        assertThat(daoFactory.getTicketDao(), instanceOf(TicketDao.class));
        assertThat(daoFactory.getPriceDao(), instanceOf(PriceDao.class));
    }
}
