package org.study.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.study.dao.mappers.Mapper;
import org.study.configuration.TransactionManager;
import org.study.dao.TicketDao;

import java.util.ArrayList;
import org.study.factories.MapperFactory;
import org.study.models.Ticket;

public class TicketDaoImpl implements TicketDao {

    private static final Logger LOG = Logger.getLogger(TicketDaoImpl.class);
    private Mapper<Ticket> ticketMapper = MapperFactory.getInstance().getTicketMapper();

    private static final String TICKET_SQL_GET = "SELECT t.id as id_ticket, t.`row`, t.place_number, t.price, " +
            "s.id as id_schedule, s.day_of_week, s.time, h.id as id_hall, h.name as name_hall, h.max_row, " +
            "h.places_in_row, m.id as id_movie, m.name as name_movie, m.duration, m.ageLimit, m.description, g.genre " +
            "FROM ticket as t JOIN session_schedule as s ON t.session_schedule_id = s.id " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id " +
            "JOIN movie_genre as g ON m.genre_id = g.id WHERE t.user_id = (?)";
    private static final String TICKET_SQL_CREATE = "INSERT INTO ticket (user_id, session_schedule_id, `row`, " +
            "place_number, price) VALUES (?, ?, ?, ?, ?)";
    private static final String TICKET_SQL_DELETE = "DELETE FROM ticket WHERE id = (?)";
    private static final String TICKET_SQL_PLACES_COUNT = "SELECT count(id) as count FROM ticket";
    private static final String TICKET_SQL_GET_BY_SESSION = "SELECT t.id as id_ticket, t.`row`, t.place_number, t.price, " +
            "s.id as id_schedule, s.day_of_week, s.time, h.id as id_hall, h.name as name_hall, h.max_row, " +
            "h.places_in_row, m.id as id_movie, m.name as name_movie, m.duration, m.ageLimit, m.description, g.genre " +
            "FROM ticket as t JOIN session_schedule as s ON t.session_schedule_id = s.id " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id " +
            "JOIN movie_genre as g ON m.genre_id = g.id " +
            "WHERE t.session_schedule_id = (?)";
    private static final String TICKET_SQL_DELETE_BY_SESSION = "DELETE FROM ticket WHERE session_schedule_id = (?)";
    private static final String TICKET_SQL_GET_ALL = "SELECT t.id as id_ticket, t.`row`, t.place_number, t.price, " +
            "s.id as id_schedule, s.day_of_week, s.time, h.id as id_hall, h.name as name_hall, h.max_row, " +
            "h.places_in_row, m.id as id_movie, m.name as name_movie, m.duration, m.ageLimit, m.description, g.genre " +
            "FROM ticket as t JOIN session_schedule as s ON t.session_schedule_id = s.id " +
            "JOIN hall as h ON s.hall_id = h.id " +
            "JOIN movie as m ON s.movie_id = m.id " +
            "JOIN movie_genre as g ON m.genre_id = g.id ORDER BY s.id LIMIT ?, ?";
    private static final String TICKET_SQL_GET_USERID = "SELECT user_id FROM ticket WHERE session_schedule_id = (?)";

    /**
     * getUserById all tickets by user_Id
     */
    @Override
    public List<Ticket> get(int userId) {
        List<Ticket> ticketsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_GET);) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    ticketsList.add(ticketMapper.createModel(resultSet));
                    LOG.info("Ticket from user with id " + userId + " was selected");
                }
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return ticketsList;
    }

    /**
     * add new ticket in database by user_Id, this method firstly getUserById session id from table session_schedule,
     * and the substitute this parameter in ticket table column session_schedule_id
     */
    @Override
    public void create(int userId, int scheduleId, Ticket ticket) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_CREATE);) {
            setValuesForTicketWithUserId(preparedStatement, ticket, userId, scheduleId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New ticket by user with Id " + userId + "was added");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * this method delete ticket by ticket id
     */
    @Override
    public void delete(int ticketId) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_DELETE);) {
            preparedStatement.setInt(1, ticketId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Ticket with id " + ticketId + "was deleted");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * this method count occupied places by session_schedule_id
     */
    @Override
    public int countOccupiedPlaces() {
        int occupiedPlaces = 0;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_PLACES_COUNT); ResultSet resultSet = preparedStatement.executeQuery();) {
            resultSet.next();
            occupiedPlaces = resultSet.getInt("count");
            LOG.info("Tickets quantity at session with was found");
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return occupiedPlaces;
    }

    /**
     * this method getUserById all tickets by session_schedule_id
     */
    @Override
    public List<Ticket> getTicketsBySchedule(int scheduleId) {
        List<Ticket> ticketsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_GET_BY_SESSION);) {
            preparedStatement.setInt(1, scheduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    ticketsList.add(ticketMapper.createModel(resultSet));
                    LOG.info("Ticket from session with id " + scheduleId + " was selected");
                }
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return ticketsList;
    }

    /**
     * this method delete all tickets by session_schedule_id
     */
    @Override
    public void deleteBySchedule(int scheduleId) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_DELETE_BY_SESSION);) {
            preparedStatement.setInt(1, scheduleId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Ticket from session id " + scheduleId + "was deleted");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * this method select all tickets from database
     */
    @Override
    public List<Ticket> getAllTicketsByPages(int pageQuantities, int rowsOnPage) {
        List<Ticket> ticketsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_GET_ALL);) {
            preparedStatement.setInt(1, (pageQuantities - 1) * rowsOnPage);
            preparedStatement.setInt(2, rowsOnPage);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    ticketsList.add(ticketMapper.createModel(resultSet));
                }
                LOG.info("Ticket list was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return ticketsList;
    }

    /**
     * this method getUserById user id from tickets by session schedule
     */
    @Override
    public int getUserIdByScheduleId(int scheduleId) {
        int userId = 0;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(TICKET_SQL_GET_USERID);) {
            preparedStatement.setInt(1, scheduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    userId = resultSet.getInt("user_id");
                }
                LOG.info("User id was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return userId;
    }

    private void setValuesForTicketWithUserId(PreparedStatement preparedStatement, Ticket ticket, int userId,
                                              int scheduleId) throws SQLException {
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, scheduleId);
        preparedStatement.setInt(3, ticket.getPlace().getRow());
        preparedStatement.setInt(4, ticket.getPlace().getPlaceNumber());
        preparedStatement.setDouble(5, ticket.getTicketPrice());
    }
}
