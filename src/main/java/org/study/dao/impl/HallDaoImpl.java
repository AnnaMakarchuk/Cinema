package org.study.dao.impl;

import org.apache.log4j.Logger;
import org.study.dao.mappers.Mapper;
import org.study.configuration.TransactionManager;
import org.study.factories.MapperFactory;
import org.study.models.Hall;
import org.study.dao.HallDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HallDaoImpl implements HallDao {
    private static final Logger LOG = Logger.getLogger(HallDaoImpl.class);
    private Mapper<Hall> hallMapper = MapperFactory.getInstance().getHallMapper();

    private static final String HALL_SQL_GET = "SELECT h.id as id_hall, h.name as name_hall, h.max_row, " +
            "h.places_in_row FROM hall as h WHERE h.id = (?)";

    /**
     * this method getUserById hall by id from database
     */
    @Override
    public Hall get(int hallId) {
        Hall hall = null;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(HALL_SQL_GET);) {
            preparedStatement.setInt(1, hallId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    hall = hallMapper.createModel(resultSet);
                }
                LOG.info("Hall with id  " + hallId + "was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return hall;
    }
}
