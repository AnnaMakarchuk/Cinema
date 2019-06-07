package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.DAO.PriceDAO;
import org.study.configuration.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceDAOImpl implements PriceDAO {
    private static final Logger LOG = Logger.getLogger(PriceDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String PRICE_SQL_GET = "SELECT price FROM price_type " +
            "WHERE hall_id IN (?) AND `row_number` IN (?)";
    private static final String PRICE_SQL_CREATE = "INSERT INTO price_type (`row_number`, price, hall_id) " +
            "VALUES (?, ?, ?)";
    private static final String PRICE_SQL_UPDATE = "UPDATE price_type SET price = (?) " +
            "WHERE hall_id IN (?) AND `row_number` IN (?)";
    private static final String PRICE_SQL_DELETE = "DELETE FROM price_type " +
            "WHERE hall_id IN (?) AND `row_number` IN (?)";


    /**
     * this method get price from database concrete row in concrete hall
     *
     * @param rowNumber
     * @param hallId
     * @return price
     */
    @Override
    public double get(int rowNumber, int hallId) {
        double price = 0.00;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(PRICE_SQL_GET);
            preparedStatement.setInt(1, hallId);
            preparedStatement.setInt(2, rowNumber);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            resultSet.next();
            price = resultSet.getDouble("price");
            LOG.info("The transaction was successfully");
            LOG.info("Price for row " + rowNumber + " in hall " + hallId + " is selected");
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
        return price;
    }

    /**
     * this method create new price in database
     *
     * @param rowNumber
     * @param price
     * @param hallId
     */
    @Override
    public void create(int rowNumber, double price, int hallId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(PRICE_SQL_CREATE);
            preparedStatement.setInt(1, rowNumber);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, hallId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Price for row number " + rowNumber + " in hall " + hallId + "was added");
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
     * this method update price in database for concrete row and hall
     *
     * @param rowNumber
     * @param price
     * @param hallId
     */
    @Override
    public void update(int rowNumber, double price, int hallId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(PRICE_SQL_UPDATE);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, hallId);
            preparedStatement.setInt(3, rowNumber);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Price for row number " + rowNumber + " and hall " + hallId + "was updated");
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
     * this method delete price from database in case wrong param
     *
     * @param rowNumber
     * @param hallId
     */
    @Override
    public void delete(int rowNumber, int hallId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(PRICE_SQL_DELETE);
            preparedStatement.setInt(1, hallId);
            preparedStatement.setInt(2, rowNumber);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Price with from hall" + hallId + "with row " + rowNumber + " was deleted");
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
}
