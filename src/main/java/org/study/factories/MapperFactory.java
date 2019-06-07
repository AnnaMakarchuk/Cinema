package org.study.factories;

import java.util.Objects;
import org.study.dao.mappers.AdministratorMapper;
import org.study.dao.mappers.HallMapper;
import org.study.dao.mappers.Mapper;
import org.study.dao.mappers.MovieMapper;
import org.study.dao.mappers.PlaceMapper;
import org.study.dao.mappers.RegisteredUserMapper;
import org.study.dao.mappers.SessionScheduleMapper;
import org.study.dao.mappers.TicketMapper;
import org.study.models.Administrator;
import org.study.models.Hall;
import org.study.models.Movie;
import org.study.models.Place;
import org.study.models.RegisteredUser;
import org.study.models.SessionSchedule;
import org.study.models.Ticket;

public final class MapperFactory {
    private static MapperFactory MAPPER_FACTORY_INSTANCE;

    private MapperFactory() {
    }

    public static MapperFactory getInstance() {
        if (Objects.isNull(MAPPER_FACTORY_INSTANCE)) {
            MAPPER_FACTORY_INSTANCE = new MapperFactory();
        }
        return MAPPER_FACTORY_INSTANCE;
    }

    public Mapper<RegisteredUser> getRegisteredUserMapper() {
        return new RegisteredUserMapper();
    }

    public Mapper<Administrator> getAdministratorMapper() {
        return new AdministratorMapper();
    }

    public Mapper<Hall> getHallMapper() {
        return new HallMapper();
    }

    public Mapper<Movie> getMovieMapper() {
        return new MovieMapper();
    }

    public Mapper<Place> getPlaceMapper() {
        return new PlaceMapper();
    }

    public Mapper<Ticket> getTicketMapper() {
        return new TicketMapper();
    }

    public Mapper<SessionSchedule> getSessionScheduleMapper() {
        return new SessionScheduleMapper();
    }
}
