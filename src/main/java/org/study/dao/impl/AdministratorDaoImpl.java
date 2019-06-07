package org.study.dao.impl;

import org.apache.log4j.Logger;
import org.study.dao.mappers.Mapper;
import org.study.configuration.TransactionManager;
import org.study.factories.MapperFactory;
import org.study.models.Administrator;
import org.study.dao.AdministratorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * work with table user. Administrator is an inheritance of RegisteredUser. This dao needed in case work only with admin.
 */
public class AdministratorDaoImpl implements AdministratorDao {
    private static final Logger LOG = Logger.getLogger(AdministratorDaoImpl.class);
    private Mapper<Administrator> administratorMapper = MapperFactory.getInstance().getAdministratorMapper();

    private static final String ADMIN_SQL_GET = "SELECT u.*, r.role FROM user as u JOIN user_role as r " +
            "ON u.role_id = r.id WHERE r.role = 'administrator' AND u.id = (?)";
    private static final String ADMIN_SQL_UPDATE = "UPDATE user SET login = (?), password = (?) WHERE id = (?)";

    /**
     * this method getUserById admin by id from database
     */
    @Override
    public Administrator get(int administratorId) {
        Administrator administrator = null;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(ADMIN_SQL_GET);) {
            preparedStatement.setInt(1, administratorId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    administrator = administratorMapper.createModel(resultSet);
                    LOG.info("Administrator with login " + administratorId + " was selected");
                }
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return administrator;
    }

    /**
     * this method updateIsActive new values of password and login
     */
    @Override
    public void update(int administratorId, String adminLogin, String adminPassword) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(ADMIN_SQL_UPDATE);) {
            setChangingValues(administratorId, adminLogin, adminPassword, preparedStatement);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Administrator with id " + administratorId + "was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    private void setChangingValues(int userID, String adminLogin, String adminPassword,
                                   PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, adminLogin);
        preparedStatement.setString(2, adminPassword);
        preparedStatement.setInt(3, userID);
    }
}
