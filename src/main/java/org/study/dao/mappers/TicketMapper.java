package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.study.factories.MapperFactory;
import org.study.models.Place;
import org.study.models.SessionSchedule;
import org.study.models.Ticket;

public class TicketMapper implements Mapper<Ticket> {//TODO move init of dependencies to constructor

    private Mapper<SessionSchedule> sessionScheduleMapper;
    private Mapper<Place> placeMapper;

    public TicketMapper() {
        this.sessionScheduleMapper = MapperFactory.getInstance().getSessionScheduleMapper();
        this.placeMapper = MapperFactory.getInstance().getPlaceMapper();
    }

    /**
     * this method create object from result set
     */
    @Override
    public Ticket createModel(ResultSet rs) throws SQLException {
        if (Objects.isNull(rs)) {
            return null;
        }
        return new Ticket(rs.getInt(ColumnNames.TICKET_ID),
                sessionScheduleMapper.createModel(rs),
                placeMapper.createModel(rs),
                rs.getDouble(ColumnNames.TICKET_PRICE));
    }
}
