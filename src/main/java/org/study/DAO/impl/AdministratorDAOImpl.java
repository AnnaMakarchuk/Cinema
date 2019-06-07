package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.models.Administrator;
import org.study.DAO.AdministratorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDAOImpl implements AdministratorDAO {

    private static final Logger LOG = Logger.getLogger(AdministratorDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String ADMIN_SQL_GET = "SELECT u.*, r.role FROM user as u JOIN user_role as r " +
            "ON u.role_id = r_id WHERE r_role = 'administrator' AND u.id IN (?)";
    private static final String ADMIN_SQL_CREATE = "INSERT INTO user (name, surname, gender, role_id, login, " +
            "email_address, password, salary, working_hours_week) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ADMIN_SQL_UPDATE = "UPDATE user SET name = (?), surname = (?), gender = (?), " +
            "role_id  = (?), login = (?), email_address = (?), password = (?), salary = (?), " +
            "working_hours_week  = (?) WHERE id IN (?)";
    private static final String ADMIN_SQL_DELETE = "DELETE FROM user WHERE login IN (?)";
    private static final String ROLE_SQL_GETID = "SELECT id FROM user_role WHERE role IN (?)";

    /**
     * this method get admin by id from database
     *
     * @param id
     * @return administrator
     */
    @Override
    public Administrator get(int id) {
        Administrator administrator = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(ADMIN_SQL_GET);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                administrator = new Administrator(resultSet);
                LOG.info("Administrator with login " + id + " is selected");
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
        return administrator;
    }

    /**
     * this method create new admin in database, this method firstly get user role id for role type
     * and the substitute this parameter in user table column role_id
     *
     * @param administrator
     */
    @Override
    public void create(Administrator administrator) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(ROLE_SQL_GETID);
            preparedStatement1.setString(1, administrator.getUserRole().name().toLowerCase());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int roleId = resultSet1.getInt("id");
            preparedStatement2 = con.prepareStatement(ADMIN_SQL_CREATE);
            setInsertValues(preparedStatement2, administrator, roleId);
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New administrator was added");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement closing is failed", e);
            }
        }
    }

    /**
     * this method update admin in database, this method firstly get user role id for role type
     * (in case of roles extension this method would work correctly)
     * and the substitute this parameter in user table column role_id
     *
     * @param administrator
     */

    @Override
    public void update(Administrator administrator) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(ROLE_SQL_GETID);
            preparedStatement1.setString(1, administrator.getUserRole().name().toLowerCase());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int roleId = resultSet1.getInt("id");
            preparedStatement2 = con.prepareStatement(ADMIN_SQL_UPDATE);
            setInsertValues(preparedStatement2, administrator, roleId);
            preparedStatement2.setInt(10, administrator.getId());
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Administrator with id " + administrator.getId() + " and login " +
                    administrator.getUserLogin() + "was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement closing is failed", e);
            }
        }
    }

    /**
     * this method delete admin from database
     *
     * @param administrator
     */
    @Override
    public void delete(Administrator administrator) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(ADMIN_SQL_DELETE);
            preparedStatement.setString(1, administrator.getUserLogin());
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Reegistered user with login " + administrator.getUserLogin() + " was deleted");
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

    private void setInsertValues(PreparedStatement preparedStatement, Administrator administrator, int roleId)
            throws SQLException {
        preparedStatement.setString(1, administrator.getUserName());
        preparedStatement.setString(2, administrator.getUserSurname());
        preparedStatement.setString(3, administrator.getGender().name().toLowerCase());
        preparedStatement.setInt(4, roleId);
        preparedStatement.setString(5, administrator.getUserLogin());
        preparedStatement.setString(6, administrator.getUserEMailAddress());
        preparedStatement.setString(7, administrator.getUserPassword());
        preparedStatement.setDouble(8, administrator.getMonthSalary());
        preparedStatement.setInt(9, administrator.getWorkingHoursWeek());
    }
}
