package org.study.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.study.dao.mappers.ColumnNames;
import org.study.dao.mappers.Mapper;
import org.study.configuration.TransactionManager;
import org.study.factories.MapperFactory;
import org.study.models.SessionSchedule;
import org.study.dao.SessionScheduleDao;
import org.study.utils.TimeConverter;

public class SessionScheduleDaoImpl implements SessionScheduleDao {
    private static final Logger LOG = Logger.getLogger(SessionScheduleDaoImpl.class);
    private Mapper<SessionSchedule> sessionScheduleMapper = MapperFactory.getInstance().getSessionScheduleMapper();

    private static final String SESSION_SQL_GET = "SELECT s.id as id_schedule, s.day_of_week, s.time, " +
            "h.id as id_hall, h.name as name_hall, h.max_row, h.places_in_row, m.id as id_movie, m.name as name_movie," +
            "m.duration, m.ageLimit, m.description, g.genre FROM session_schedule as s " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id JOIN movie_genre as g ON m.genre_id = g.id " +
            "WHERE s.isActive = 1 AND s.id = (?)";
    private static final String SESSION_SQL_GET_ALL_WEEKDAY = "SELECT s.id as id_schedule, s.day_of_week, s.time, " +
            "h.id as id_hall, h.name as name_hall, h.max_row, h.places_in_row, m.id as id_movie, m.name as name_movie," +
            "m.duration, m.ageLimit, m.description, g.genre FROM session_schedule as s " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id JOIN movie_genre as g ON m.genre_id = g.id " +
            "WHERE s.isActive = 1 AND s.day_of_week = (?) ORDER BY s.time ASC";
    private static final String SESSION_SQL_GET_BY_MOVIE_ID = "SELECT s.id as id_schedule, s.day_of_week, s.time, " +
            "h.id as id_hall, h.name as name_hall, h.max_row, h.places_in_row, m.id as id_movie, m.name as name_movie," +
            "m.duration, m.ageLimit, m.description, g.genre FROM session_schedule as s " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id JOIN movie_genre as g ON m.genre_id = g.id WHERE s.isActive IN (?)" +
            " AND m.id = (?)";
    private static final String SESSION_SQL_UPDATE_IS_ACTIVE = "UPDATE session_schedule SET isActive = (?) " +
            "WHERE movie_id = (?)";
    private static final String SESSION_SQL_GET_NOT_ACTIVE = "SELECT s.id as id_schedule, s.day_of_week, s.time, " +
            "h.id as id_hall, h.name as name_hall, h.max_row, h.places_in_row, m.id as id_movie, m.name as name_movie," +
            "m.duration, m.ageLimit, m.description, g.genre FROM session_schedule as s " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id JOIN movie_genre as g ON m.genre_id = g.id " +
            "WHERE s.isActive = (?)";
    private static final String SESSION_SQL_GET_TIME = "SELECT time FROM session_schedule WHERE id = (?)";
    private static final String SESSION_SQL_UPDATE_MOVIE = "UPDATE session_schedule SET movie_id = (?), isActive = (?) " +
            "WHERE id = (?)";

    /**
     * this method getUserById session schedule by id from database
     */
    @Override
    public SessionSchedule get(int scheduleId) {
        SessionSchedule sessionSchedule = null;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(SESSION_SQL_GET);) {
            preparedStatement.setInt(1, scheduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    sessionSchedule = sessionScheduleMapper.createModel(resultSet);
                    LOG.info("Schedule with id " + scheduleId + " was selected");
                }
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return sessionSchedule;
    }

    /**
     * this method updateIsActive session in schedule in database
     */
    @Override
    public void updateIsActive(int movieId, boolean isActive) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(SESSION_SQL_UPDATE_IS_ACTIVE);) {
            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setInt(2, movieId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Schedule with movie id " + movieId + " is not active");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * add movie in schedule and make schedule active
     */
    @Override
    public void updateMovieInSchedule(int scheduleId, int movieId, boolean isActive) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(SESSION_SQL_UPDATE_MOVIE);) {
            preparedStatement.setInt(1, movieId);
            preparedStatement.setBoolean(2, isActive);
            preparedStatement.setInt(3, scheduleId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Schedule with id " + scheduleId + " is active");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * this method select all active schedule for concrete weekday from database
     */
    @Override
    public List<SessionSchedule> getAllByDay(String weekday) {
        List<SessionSchedule> sessionScheduleList = new ArrayList<>();
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(SESSION_SQL_GET_ALL_WEEKDAY);) {
            preparedStatement.setString(1, weekday);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    sessionScheduleList.add(sessionScheduleMapper.createModel(resultSet));
                }
                LOG.info("Schedule List was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return sessionScheduleList;
    }

    /**
     * this method getUserById all schedule from database by movieId
     */
    @Override
    public List<SessionSchedule> getScheduleByMovieId(int movieId, boolean isActive) {
        List<SessionSchedule> sessionScheduleList = new ArrayList<>();
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(SESSION_SQL_GET_BY_MOVIE_ID);) {
            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setInt(2, movieId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    sessionScheduleList.add(sessionScheduleMapper.createModel(resultSet));
                }
                LOG.info("Session List was selected");

            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return sessionScheduleList;
    }

    /**
     * this method select all non active schedule from database
     */
    @Override
    public List<SessionSchedule> getAllNonActive(boolean isActive) {
        List<SessionSchedule> nonActiveSessionScheduleList = new ArrayList<>();
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(SESSION_SQL_GET_NOT_ACTIVE);) {
            preparedStatement.setBoolean(1, isActive);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    nonActiveSessionScheduleList.add(sessionScheduleMapper.createModel(resultSet));
                }
                LOG.info("Session List was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return nonActiveSessionScheduleList;
    }

    @Override
    public LocalTime getScheduleTime(int scheduleId) {
        LocalTime localTime = null;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(SESSION_SQL_GET_TIME);) {
            preparedStatement.setInt(1, scheduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                resultSet.next();
                localTime = TimeConverter.convertSQLTimeToLocalTime(resultSet.getTime(ColumnNames.SESSION_TIME));
                LOG.info("Time of schedule id + " + scheduleId + " was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return localTime;
    }
}
