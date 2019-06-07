package org.study.utils;

import org.study.models.enums.Gender;
import org.study.models.enums.MovieGenre;
import org.study.models.enums.UserRole;
import org.study.models.enums.WeekDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class ResultSetTablesData {

    public static final String ID = "id";
    public static final String NAME = "name";

    public static final String SESSION_DAY = "day_of_week";
    public static final String SESSION_TIME = "time";

    public static final String MOVIE_GENRE = "genre";
    public static final String MOVIE_DURATION = "duration";
    public static final String MOVIE_AGE = "ageLimit";
    public static final String MOVIE_DESCRIPTION = "description";

    public static final String USER_SURNAME = "surname";
    public static final String USER_GENDER = "gender";
    public static final String USER_ROLE = "role";
    public static final String USER_LOGIN = "login";
    public static final String USER_EMAIL = "email_address";
    public static final String USER_PASSWORD = "password";
    public static final String USER_SALARY = "salary";
    public static final String USER_HOURS = "working_hours_week";

    public static final String TICKET_PRICE = "price";
    public static final String TICKET_ROW = "row";
    public static final String TICKET_PLACE = "place_number";

    public static final String HALL_ROW_NUMBER = "max_row";
    public static final String HALL_PLACES_IN_ROW = "places_in_row";

    public static String getStringByName(String columnName, ResultSet resultSet) throws SQLException {
        return resultSet.getString(columnName);
    }

    public static int getIntByName(String columnName, ResultSet resultSet) throws SQLException {
        return resultSet.getInt(columnName);
    }

    public static double getDoubleByName(String columnName, ResultSet resultSet) throws SQLException {
        return resultSet.getDouble(columnName);
    }

    public static LocalTime getTimeByName(String columnName, ResultSet resultSet) throws SQLException {
        return TimeConverter.convertSQLTimeToLocalTime(resultSet.getTime(columnName));
    }

    public static Gender getGender(ResultSet resultSet) throws SQLException {
        return Gender.valueOf(getStringByName(USER_GENDER, resultSet).toUpperCase());

    }

    public static UserRole getUserRole(ResultSet resultSet) throws SQLException {
        return UserRole.valueOf(getStringByName(USER_ROLE, resultSet).toUpperCase());
    }

    public static WeekDay getWeekDay(ResultSet resultSet) throws SQLException {
        return WeekDay.valueOf(getStringByName(SESSION_DAY, resultSet).toUpperCase());
    }

    public static MovieGenre getMovieGenre(ResultSet resultSet) throws SQLException {
        return MovieGenre.valueOf(getStringByName(MOVIE_GENRE, resultSet).toUpperCase());
    }


}
