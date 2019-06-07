package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.study.factories.MapperFactory;
import org.study.models.Hall;
import org.study.models.Movie;
import org.study.models.SessionSchedule;
import org.study.models.enums.WeekDay;
import org.study.utils.TimeConverter;

public class SessionScheduleMapper implements Mapper<SessionSchedule> {
    private Mapper<Hall> hallMapper;
    private Mapper<Movie> movieMapper;

    public SessionScheduleMapper() {
        this.hallMapper = MapperFactory.getInstance().getHallMapper();
        this.movieMapper = MapperFactory.getInstance().getMovieMapper();
    }

    /**
     * this method create object from result set
     */
    @Override
    public SessionSchedule createModel(ResultSet rs) throws SQLException {
        if (Objects.isNull(rs)) {
            return null;
        }
        return new SessionSchedule(rs.getInt(ColumnNames.SCHEDULE_ID),
                WeekDay.valueOf(rs.getString(ColumnNames.SESSION_DAY).toUpperCase()),
                TimeConverter.convertSQLTimeToLocalTime(rs.getTime(ColumnNames.SESSION_TIME)),
                hallMapper.createModel(rs),
                movieMapper.createModel(rs));
    }
}
