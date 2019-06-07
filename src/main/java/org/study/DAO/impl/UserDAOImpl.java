package org.study.dao.impl;

import org.apache.log4j.Logger;
import org.study.dao.mappers.Mapper;
import org.study.configuration.TransactionManager;
import org.study.factories.MapperFactory;
import org.study.models.RegisteredUser;
import org.study.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private Mapper<RegisteredUser> registeredUserMapper = MapperFactory.getInstance().getRegisteredUserMapper();

    private static final String USER_SQL_GET = "SELECT u.*, r.role FROM user as u JOIN user_role as r " +
            "ON u.role_id = r.id WHERE r.role = 'client' AND u.id = (?)";
    private static final String USER_SQL_GET_BY_LOG_PASS = "SELECT u.*, r.role FROM user as u JOIN user_role as r " +
            "ON u.role_id = r.id WHERE u.login = (?) AND u.password = (?) ";
    private static final String USER_SQL_CREATE = "INSERT INTO user (name, surname, gender, role_id, login," +
            " email_address, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String USER_SQL_UPDATE = "UPDATE user SET login = (?), password = (?) WHERE id = (?)";
    private static final String USER_SQL_DELETE = "DELETE FROM user WHERE id = (?)";

    /**
     * this method getUserById user by id from database
     */
    @Override
    public RegisteredUser getUserById(int userId) {
        RegisteredUser registeredUser = null;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(USER_SQL_GET);) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    registeredUser = registeredUserMapper.createModel(resultSet);
                    LOG.info("Registered user with id " + userId + " was selected");
                }
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return registeredUser;
    }

    /**
     * this method create new user in database, this method firstly getUserById user role id for role type
     * and the substitute this parameter in user table column role_id
     */
    @Override
    public void create(int userRoleId, RegisteredUser registeredUser) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(USER_SQL_CREATE)) {
            setInsertValues(preparedStatement, registeredUser, userRoleId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New registered user was added");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * this method updateIsActive user in database, this method firstly getUserById user role id for role type
     * (in case of roles extension this method would work correctly)
     * and the substitute this parameter in user table column role_id
     */
    @Override
    public void update(int userID, String clientLogin, String clientPassword) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(USER_SQL_UPDATE);) {
            setChangingValues(userID, clientLogin, clientPassword, preparedStatement);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Registered User with id " + userID + " and login " + clientLogin + "was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * this method delete concrete user from database
     */
    @Override
    public void delete(int userID) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(USER_SQL_DELETE);) {
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Reegistered user with id " + userID + " was deleted");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    /**
     * this method select registeredUser by login and password.
     * If no coincidence in database registeredUser will be null
     */
    @Override
    public RegisteredUser getUserByLoginAndPassword(String userLogin, String userPassword) {
        RegisteredUser registeredUser = null;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance()
                .prepareStatement(USER_SQL_GET_BY_LOG_PASS);) {
            preparedStatement.setString(1, userLogin);
            preparedStatement.setString(2, userPassword);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    registeredUser = registeredUserMapper.createModel(resultSet);
                    LOG.info("Registered user with login " + userLogin + " was selected");
                }
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return registeredUser;
    }

    private void setInsertValues(PreparedStatement preparedStatement, RegisteredUser registeredUser, int roleId)
            throws SQLException {
        preparedStatement.setString(1, registeredUser.getUserName());
        preparedStatement.setString(2, registeredUser.getUserSurname());
        preparedStatement.setString(3, registeredUser.getGender().name().toLowerCase());
        preparedStatement.setInt(4, roleId);
        preparedStatement.setString(5, registeredUser.getUserLogin());
        preparedStatement.setString(6, registeredUser.getUserEMailAddress());
        preparedStatement.setString(7, registeredUser.getUserPassword());
    }

    private void setChangingValues(int userID, String clientLogin, String clientPassword,
                                   PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, clientLogin);
        preparedStatement.setString(2, clientPassword);
        preparedStatement.setInt(3, userID);
    }
}
