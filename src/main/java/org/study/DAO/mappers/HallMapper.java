package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.study.models.Hall;

public class HallMapper implements Mapper<Hall> {
    /**
     * this method create object from result set
     */
    @Override
    public Hall createModel(ResultSet rs) throws SQLException {
        if (Objects.isNull(rs)) {
            return null;
        }
        return new Hall(rs.getInt(ColumnNames.HALL_ID),
                rs.getString(ColumnNames.HALL_NAME),
                rs.getInt(ColumnNames.HALL_ROW_NUMBER),
                rs.getInt(ColumnNames.HALL_PLACES_IN_ROW));
    }
}
