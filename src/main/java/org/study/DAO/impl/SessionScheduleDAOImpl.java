package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.models.SessionSchedule;
import org.study.DAO.SessionScheduleDAO;
import org.study.utils.TimeConverter;

import java.sql.*;

public class SessionScheduleDAOImpl implements SessionScheduleDAO {

    private static final Logger LOG = Logger.getLogger(SessionScheduleDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String SESSION_SQL_GET = "SELECT s.day_of_week, s.time, " +
            "h.*, m.*, g.genre FROM session_schedule as s JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id " +
            "JOIN movie_genre as g ON m.genre_id = g.id " +
            "WHERE s.id IN (?)";
    private static final String SESSION_SQL_CREATE = "INSERT INTO session_schedule (day_of_week, time, hall_id, " +
            "movie_id) VALUES (?, ?, ?, ?)";
    private static final String SESSION_SQL_UPDATE = "UPDATE session_schedule SET day_of_week = (?), time = (?), " +
            "hall_id = (?), movie_id = (?) WHERE id IN (?)";
    private static final String SESSION_SQL_DELETE = "DELETE FROM session_schedule WHERE id IN (?)";
    private static final String SQL_GET_MOVIE_ID = "SELECT id FROM movie WHERE name IN (?)";
    private static final String SQL_GET_HALL_ID = "SELECT id FROM hall WHERE name IN (?)";

    /**
     * this method get session schedule by session id from database
     *
     * @param id
     * @return sessionSchedule
     */
    @Override
    public SessionSchedule get(int id) {
        SessionSchedule sessionSchedule = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(SESSION_SQL_GET);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                sessionSchedule = new SessionSchedule(resultSet);
                LOG.info("Session with id " + id + " is selected");
            }
            LOG.info("The transaction was successfully");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set closing is failed", e);
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement closing is failed", e);
            }
        }
        return sessionSchedule;
    }

    /**
     * this method create session in schedule in database, this method firstly get movie id for movie name
     * and the substitute this parameter in session_schedule table column movie_id.
     *
     * @param sessionSchedule
     */
    @Override
    public void create(SessionSchedule sessionSchedule) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet2 = null;
        PreparedStatement preparedStatement3 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(SQL_GET_MOVIE_ID);
            preparedStatement1.setString(1, sessionSchedule.getMovie().getMovieName());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int movieId = resultSet1.getInt("id");

            preparedStatement2 = con.prepareStatement(SQL_GET_HALL_ID);
            preparedStatement2.setString(1, sessionSchedule.getHall().getHallName());
            resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            int hallId = resultSet2.getInt("id");

            preparedStatement3 = con.prepareStatement(SESSION_SQL_CREATE);
            setInsertValues(preparedStatement3, sessionSchedule, hallId, movieId);
            preparedStatement3.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New session was added in schedule");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set for getting movie Id closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting movie Id closing is failed", e);
            }
            try {
                if (resultSet2 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set for getting hall Id closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting movie Id closing is failed", e);
            }
            try {
                if (preparedStatement3 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement (for insert new session) closing is failed", e);
            }
        }
    }

    /**
     * this method update session in schedule in database, this method firstly get movie id for movie name
     * (in case of change movie in session)
     * and the substitute this parameter in session_schedule table column movie_id.
     *
     * @param sessionSchedule
     */
    @Override
    public void update(SessionSchedule sessionSchedule) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet2 = null;
        PreparedStatement preparedStatement3 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(SQL_GET_MOVIE_ID);
            preparedStatement1.setString(1, sessionSchedule.getMovie().getMovieName());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int movieId = resultSet1.getInt("id");

            preparedStatement2 = con.prepareStatement(SQL_GET_HALL_ID);
            preparedStatement2.setString(1, sessionSchedule.getHall().getHallName());
            resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            int hallId = resultSet2.getInt("id");

            preparedStatement3 = con.prepareStatement(SESSION_SQL_UPDATE);
            setInsertValues(preparedStatement3, sessionSchedule, hallId, movieId);
            preparedStatement3.setInt(5, sessionSchedule.getId());
            preparedStatement3.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New session with id " + sessionSchedule.getId() + "was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set for getting movie Id closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting movie Id closing is failed", e);
            }
            try {
                if (resultSet2 != null) {
                    resultSet2.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set for getting hall Id closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting movie Id closing is failed", e);
            }
            try {
                if (preparedStatement3 != null) {
                    preparedStatement3.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement (for insert new session) closing is failed", e);
            }
        }
    }

    /**
     * this method delete session from schedule
     *
     * @param sessionSchedule
     */
    @Override
    public void delete(SessionSchedule sessionSchedule) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(SESSION_SQL_DELETE);
            preparedStatement.setInt(1, sessionSchedule.getId());
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Session with id " + sessionSchedule.getId() + "was deleted");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement (for insert new session) closing is failed", e);
            }
        }
    }

    private void setInsertValues(PreparedStatement preparedStatement, SessionSchedule sessionSchedule,
                                 int hallId, int movieId)
            throws SQLException {
        preparedStatement.setString(1, String.valueOf(sessionSchedule.getWeekDay()));
        preparedStatement.setTime(2, TimeConverter.convertLocalTimeToSQLTime(sessionSchedule.getTime()));
        preparedStatement.setInt(3, hallId);
        preparedStatement.setInt(4, movieId);
    }
}
