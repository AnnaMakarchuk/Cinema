package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.DAO.UserRoleDAO;
import org.study.configuration.TransactionManager;
import org.study.models.enums.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDAOImpl implements UserRoleDAO {

    private static final Logger LOG = Logger.getLogger(UserRoleDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String ROLE_SQL_GET = "SELECT * FROM user_role WHERE role IN (?)";
    private static final String ROLE_SQL_CREATE = "INSERT INTO user_role (role) VALUES (?)";
    private static final String ROLE_SQL_DELETE = "DELETE FROM user_role WHERE role IN (?)";

    /**
     * this method get userRoly by id from database
     *
     * @param roleName
     * @return
     */
    @Override
    public UserRole get(String roleName) {
        UserRole userRole = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(ROLE_SQL_GET);
            preparedStatement.setString(1, roleName);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                userRole = UserRole.valueOf(resultSet.getString("role").toUpperCase());
                LOG.info("User role with name " + roleName + " is selected");
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
        return userRole;
    }

    /**
     * this method create new row Role in database
     *
     * @param userRole
     */
    @Override
    public void create(UserRole userRole) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(ROLE_SQL_CREATE);
            setParameters(preparedStatement, userRole);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New userRole was added");
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
    public void update(int id, UserRole userRole) {
    }

    @Override
    public void delete(UserRole userRole) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(ROLE_SQL_DELETE);
            setParameters(preparedStatement, userRole);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("UserRole with name" + userRole.name() + "was deleted");
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

    private void setParameters(PreparedStatement preparedStatement, UserRole userRole) throws SQLException {
        preparedStatement.setString(1, userRole.name().toLowerCase());
    }

}
