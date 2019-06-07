package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.models.Hall;
import org.study.DAO.HallDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HallDAOImpl implements HallDAO {

    private static final Logger LOG = Logger.getLogger(HallDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String HALL_SQL_GET = "SELECT * FROM hall WHERE id IN (?)";
    private static final String HALL_SQL_CREATE = "INSERT INTO hall (name, max_row, places_in_row) VALUES (?, ?, ?)";
    private static final String HALL_SQL_UPDATE = "UPDATE hall SET name = (?), max_row = (?), places_in_row = (?) " +
            "WHERE id IN (?)";

    /**
     * this method get hall by id from database
     *
     * @param hallId
     * @return
     */
    @Override
    public Hall get(int hallId) {
        Hall hall = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(HALL_SQL_GET);
            preparedStatement.setInt(1, hallId);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                hall = new Hall(resultSet);
                LOG.info("Hall with id " + hallId + " is selected");
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
        return hall;
    }

    /**
     * this method create new row for Hall in database
     *
     * @param hall
     */
    @Override
    public void create(Hall hall) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(HALL_SQL_CREATE);
            setParameters(preparedStatement, hall);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New hall in the cinema was added");
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
     * this method update hall in database
     *
     * @param hall
     */
    @Override
    public void update(Hall hall) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(HALL_SQL_UPDATE);
            preparedStatement.setString(1, hall.getHallName());
            preparedStatement.setInt(2, hall.getMaxRow());
            preparedStatement.setInt(3, hall.getMaxPlacesInRow());
            preparedStatement.setInt(4, hall.getId());
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New hall with the name " + hall.getHallName() + " in the cinema was updated");
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
     * this method is never used
     */
    @Override
    public void delete(Hall hall) {
    }

    private void setParameters(PreparedStatement preparedStatement, Hall hall) throws SQLException {
        preparedStatement.setString(1, hall.getHallName());
        preparedStatement.setInt(2, hall.getMaxRow());
        preparedStatement.setInt(3, hall.getMaxPlacesInRow());
    }
}
