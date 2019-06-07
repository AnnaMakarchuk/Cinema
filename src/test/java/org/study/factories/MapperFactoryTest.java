package org.study.factories;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.study.dao.mappers.AdministratorMapper;
import org.study.dao.mappers.HallMapper;
import org.study.dao.mappers.MovieMapper;
import org.study.dao.mappers.PlaceMapper;
import org.study.dao.mappers.RegisteredUserMapper;
import org.study.dao.mappers.SessionScheduleMapper;
import org.study.dao.mappers.TicketMapper;

public class MapperFactoryTest {

    private MapperFactory mapperFactory = MapperFactory.getInstance();

    @Test
    public void shouldGetMapper() {
        assertThat(mapperFactory.getRegisteredUserMapper(), instanceOf(RegisteredUserMapper.class));
        assertThat(mapperFactory.getAdministratorMapper(), instanceOf(AdministratorMapper.class));
        assertThat(mapperFactory.getHallMapper(), instanceOf(HallMapper.class));
        assertThat(mapperFactory.getMovieMapper(), instanceOf(MovieMapper.class));
        assertThat(mapperFactory.getPlaceMapper(), instanceOf(PlaceMapper.class));
        assertThat(mapperFactory.getTicketMapper(), instanceOf(TicketMapper.class));
        assertThat(mapperFactory.getSessionScheduleMapper(), instanceOf(SessionScheduleMapper.class));
    }
}
