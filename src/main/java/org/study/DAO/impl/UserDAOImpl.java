package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.models.RegisteredUser;
import org.study.DAO.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String USER_SQL_GET = "SELECT u.*, r.role FROM user as u JOIN user_role as r " +
            "ON u.role_id = r.id WHERE r.role = 'client' AND u.id IN (?)";
    private static final String USER_SQL_CREATE = "INSERT INTO user (name, surname, gender, role_id, login," +
            " email_address, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String USER_SQL_UPDATE = "UPDATE user SET name = (?), surname = (?), gender = (?), " +
            "role_id  = (?), login = (?), email_address = (?), password = (?) WHERE id IN (?)";
    private static final String USER_SQL_DELETE = "DELETE FROM user WHERE login IN (?)";
    private static final String USER_SQL_GET_ALL = "SELECT u.*, r.role FROM user as u JOIN user_role as r " +
            "ON u.role_id = r.id WHERE r.role = 'client'";
    private static final String ROLE_SQL_GETID = "SELECT id FROM user_role WHERE role IN (?)";

    /**
     * this method get user by id from database
     *
     * @param userId
     * @return user
     */
    @Override
    public RegisteredUser get(int userId) {
        RegisteredUser registeredUser = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(USER_SQL_GET);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                registeredUser = new RegisteredUser(resultSet);
                LOG.info("Registered user with login " + userId + " is selected");
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
        return registeredUser;
    }

    /**
     * this method create new user in database, this method firstly get user role id for role type
     * and the substitute this parameter in user table column role_id
     *
     * @param registeredUser
     */
    @Override
    public void create(RegisteredUser registeredUser) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(ROLE_SQL_GETID);
            preparedStatement1.setString(1, registeredUser.getUserRole().name().toLowerCase());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int roleId = resultSet1.getInt("id");
            preparedStatement2 = con.prepareStatement(USER_SQL_CREATE);
            setInsertValues(preparedStatement2, registeredUser, roleId);
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New registered user was added");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set for getting role id closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting role id closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for create user closing is failed", e);
            }
        }
    }

    /**
     * this method update user in database, this method firstly get user role id for role type
     * (in case of roles extension this method would work correctly)
     * and the substitute this parameter in user table column role_id
     *
     * @param registeredUser
     */
    @Override
    public void update(RegisteredUser registeredUser) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(ROLE_SQL_GETID);
            preparedStatement1.setString(1, registeredUser.getUserRole().name().toLowerCase());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int roleId = resultSet1.getInt("id");
            preparedStatement2 = con.prepareStatement(USER_SQL_UPDATE);
            setInsertValues(preparedStatement2, registeredUser, roleId);
            preparedStatement2.setInt(8, registeredUser.getId());
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Registered User with id " + registeredUser.getId() + " and login " +
                    registeredUser.getUserLogin() + "was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set for getting role id closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting role id closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for user update closing is failed", e);
            }
        }
    }

    /**
     * this method delete concrete user from database
     *
     * @param registeredUser
     */
    @Override
    public void delete(RegisteredUser registeredUser) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(USER_SQL_DELETE);
            preparedStatement.setString(1, registeredUser.getUserLogin());
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Reegistered user with login " + registeredUser.getUserLogin() + " was deleted");
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
     * this method get all registered users from database
     *
     * @return registeredUserList
     */
    @Override
    public List<RegisteredUser> getAll() {
        List<RegisteredUser> registeredUserList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(USER_SQL_GET_ALL);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                registeredUserList.add(new RegisteredUser(resultSet));
                LOG.info("List of all registered users is create");
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
        return registeredUserList;
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
}
