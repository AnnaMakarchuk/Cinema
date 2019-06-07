package org.study.dao.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.study.dao.PriceDao;

public class PriceDaoImpl implements PriceDao {
    private static final Logger LOG = Logger.getLogger(PriceDaoImpl.class);

    private static final String PRICE_SQL_GET = "SELECT price FROM price_type " +
            "WHERE hall_id = (?) AND `row_number` = (?)";

    /**
     * this method getUserById price from database concrete row in concrete hall
     */
    @Override
    public double get(int rowNumber, int hallId) {
        double price = 0.00;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(PRICE_SQL_GET);) {
            preparedStatement.setInt(1, hallId);
            preparedStatement.setInt(2, rowNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                resultSet.next();
                price = resultSet.getDouble("price");
                LOG.info("Price for row " + rowNumber + " in hall " + hallId + " was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return price;
    }
}
