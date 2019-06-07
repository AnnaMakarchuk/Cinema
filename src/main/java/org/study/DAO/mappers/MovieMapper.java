package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.study.models.Movie;
import org.study.models.enums.MovieGenre;

public class MovieMapper implements Mapper<Movie> {

    /**
     * this method create object from result set
     */
    @Override
    public Movie createModel(ResultSet rs) throws SQLException {
        if (Objects.isNull(rs)) {
            return null;
        }
        return new Movie(rs.getInt(ColumnNames.MOVIE_ID),
                rs.getString(ColumnNames.MOVIE_NAME),
                MovieGenre.valueOf(rs.getString(ColumnNames.MOVIE_GENRE).toUpperCase()),
                rs.getInt(ColumnNames.MOVIE_DURATION),
                rs.getInt(ColumnNames.MOVIE_AGE),
                rs.getString(ColumnNames.MOVIE_DESCRIPTION));
    }
}
