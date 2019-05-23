package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.models.*;
import org.study.DAO.TicketDAO;
import org.study.utils.TimeConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {

    private static final Logger LOG = Logger.getLogger(TicketDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String TICKET_SQL_GET = "SELECT t.`row`, t.place_number, t.price, s.day_of_week, s.time, " +
            "h.*, m.*, g.genre FROM ticket as t JOIN session_schedule as s ON t.session_schedule_id = s.id " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id " +
            "JOIN movie_genre as g ON m.genre_id = g.id " +
            "WHERE t.user_id IN (?)";
    private static final String TICKET_SQL_CREATE = "INSERT INTO ticket (user_id, session_schedule_id, `row`, " +
            "place_number, price) VALUES (?, ?, ?, ?, ?)";
    private static final String TICKET_SQL_UPDATE = "UPDATE ticket SET session_schedule_id = (?), `row` = (?), " +
            "place_number = (?), price = (?) WHERE id IN (?)";
    private static final String TICKET_SQL_DELETE = "DELETE FROM ticket WHERE id IN (?)";
    private static final String TICKET_SQL_GET_PLACES_COUNT = "SELECT count(id) as count FROM ticket " +
            "GROUP BY session_schedule_id IN (?)";
    private static final String TICKET_SQL_GET_BY_SESSION = "SELECT t.`row`, t.place_number, t.price, s.day_of_week, s.time, " +
            "h.*, m.*, g.genre FROM ticket as t JOIN session_schedule as s ON t.session_schedule_id = s.id " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id " +
            "JOIN movie_genre as g ON m.genre_id = g.id " +
            "WHERE t.id_session_schedule IN (?)";
    private static final String TICKET_SQL_DELETE_BY_SESSION = "DELETE FROM ticket WHERE session_schedule_id IN (?)";
    private static final String SESSION_SQL_GET = "SELECT s.id FROM session_schedule as s " +
            "JOIN movie as m ON s.movie_id = m.id WHERE s.day_of_week IN (?) AND s.time IN (?) AND m.name IN (?)";

    /**
     * get all tickets by user_Id
     *
     * @param userId
     * @return ticketsList
     */
    @Override
    public List<Ticket> get(int userId) {
        List<Ticket> ticketsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(TICKET_SQL_GET);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                ticketsList.add(new Ticket(resultSet));
                LOG.info("Ticket from user with id " + userId + " is selected");
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
        return ticketsList;
    }

    /**
     * add new ticket in database by user_Id, this method firstly get session id from table session_schedule,
     * and the substitute this parameter in ticket table column session_schedule_id
     *
     * @param userId
     * @param ticket
     */
    @Override
    public void create(int userId, Ticket ticket) {
        SessionSchedule sessionSchedule = ticket.getSessionSchedule();
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(SESSION_SQL_GET);
            setValuesForSession(sessionSchedule, preparedStatement1);
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int sessionId = resultSet1.getInt("id");
            preparedStatement2 = con.prepareStatement(TICKET_SQL_CREATE);
            setValuesForTicketWithUserId(preparedStatement2, ticket, userId, sessionId);
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New ticket by user with Id " + userId + "was added");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set closing for getting session schedule id is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting session schedule id closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for ticket creating closing is failed", e);
            }
        }
    }

    /**
     * this method update ticket in database by ticket id,
     * this method firstly get session id from table session_schedule,
     * and the substitute this parameter in ticket table column session_schedule_id
     *
     * @param ticket
     */
    @Override
    public void update(Ticket ticket) {
        SessionSchedule sessionSchedule = ticket.getSessionSchedule();
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(SESSION_SQL_GET);
            setValuesForSession(sessionSchedule, preparedStatement1);
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int sessionId = resultSet1.getInt("id");
            preparedStatement2 = con.prepareStatement(TICKET_SQL_UPDATE);
            setValuesForTicket(preparedStatement2, ticket, sessionId);
            preparedStatement2.setInt(5, ticket.getId());
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Ticket with id " + ticket.getId() + "was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set closing for getting session schedule id is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting session schedule id closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for ticket updating closing is failed", e);
            }
        }
    }

    /**
     * this method delete ticket by ticket id
     *
     * @param ticket
     */
    @Override
    public void delete(Ticket ticket) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(TICKET_SQL_DELETE);
            preparedStatement.setInt(1, ticket.getId());
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Ticket with id " + ticket.getId() + "was deleted");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement closing is failed", e);
            }
        }
    }

    /**
     * this method count occupied places by session_schedule_id
     *
     * @param sessionId
     * @return
     */
    @Override
    public int countOccupiedPlaces(int sessionId) {
        int occupiedPlaces = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(TICKET_SQL_GET_PLACES_COUNT);
            preparedStatement.setInt(1, sessionId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            occupiedPlaces = resultSet.getInt("count");
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Tickets quantity at session with id " + sessionId + "was found");
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
        return occupiedPlaces;
    }

    /**
     * this method get all tickets by session_schedule_id
     *
     * @param sessionId
     * @return ticketsList
     */
    @Override
    public List<Ticket> getBySession(int sessionId) {
        List<Ticket> ticketsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(TICKET_SQL_GET_BY_SESSION);
            preparedStatement.setInt(1, sessionId);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                ticketsList.add(new Ticket(resultSet));
                LOG.info("Ticket from session with id " + sessionId + " is selected");
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
        return ticketsList;
    }

    /**
     * this method delete all tickets by session_schedule_id
     *
     * @param sessionId
     */
    @Override
    public void deleteBySession(int sessionId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(TICKET_SQL_DELETE_BY_SESSION);
            preparedStatement.setInt(1, sessionId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Ticket from session id " + sessionId + "was deleted");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement closing is failed", e);
            }
        }
    }


    private void setValuesForTicketWithUserId(PreparedStatement preparedStatement, Ticket ticket, int userId, int sessionId)
            throws SQLException {
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, sessionId);
        preparedStatement.setInt(3, ticket.getPlace().getRow());
        preparedStatement.setInt(4, ticket.getPlace().getPlaceNumber());
        preparedStatement.setDouble(5, ticket.getTicketPrice());
    }

    private void setValuesForTicket(PreparedStatement preparedStatement, Ticket ticket, int sessionId)
            throws SQLException {
        preparedStatement.setInt(1, sessionId);
        preparedStatement.setInt(2, ticket.getPlace().getRow());
        preparedStatement.setInt(3, ticket.getPlace().getPlaceNumber());
        preparedStatement.setDouble(4, ticket.getTicketPrice());

    }

    private void setValuesForSession(SessionSchedule sessionSchedule, PreparedStatement preparedStatement1)
            throws SQLException {
        preparedStatement1.setString(1, sessionSchedule.getWeekDay().name());
        preparedStatement1.setTime(2, TimeConverter.
                convertLocalTimeToSQLTime(sessionSchedule.getTime()));
        preparedStatement1.setString(3, sessionSchedule.getMovie().getMovieName());
    }
}
