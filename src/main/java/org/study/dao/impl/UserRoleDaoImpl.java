package org.study.dao.impl;

import org.apache.log4j.Logger;
import org.study.dao.UserRoleDao;
import org.study.configuration.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDaoImpl implements UserRoleDao {

    private static final Logger LOG = Logger.getLogger(UserRoleDaoImpl.class);

    private static final String ROLE_SQL_GET = "SELECT id FROM user_role WHERE role IN (?)";

    /**
     * this method getUserById userRole id by role name
     */
    @Override
    public int getId(String roleName) {
        int userRoleId = 0;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(ROLE_SQL_GET)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                userRoleId = resultSet.getInt("id");
                LOG.info("User role id was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return userRoleId;
    }
}
