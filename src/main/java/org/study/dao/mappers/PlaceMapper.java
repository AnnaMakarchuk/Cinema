package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.study.models.Place;

public class PlaceMapper implements Mapper<Place> {

    /**
     * this method create object from result set
     */
    @Override
    public Place createModel(ResultSet rs) throws SQLException {
        if (Objects.isNull(rs)) {
            return null;
        }
        return new Place(rs.getInt(ColumnNames.TICKET_ROW),
                rs.getInt(ColumnNames.TICKET_PLACE));
    }
}
