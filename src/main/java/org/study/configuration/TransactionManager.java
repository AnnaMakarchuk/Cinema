package org.study.configuration;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    private TransactionManager() {
    }

    /**
     * Create instance of TransactionManager with connection autocommit is false
     */
    private static class TransactionManagerHolder {
        private static final ThreadLocal<Connection> INSTANCE =
                ThreadLocal.withInitial(TransactionManager::getTransactionManager);
    }

    public static Connection getInstance() {
        return TransactionManagerHolder.INSTANCE.get();
    }

    /**
     * Create connection with SQL statements are grouped into
     * transactions that are terminated
     */
    private static Connection getTransactionManager() {
        Connection connection = null;
        try {
            connection = JDBCConnection.getConnection();
            LOG.info("Connection is made" + connection);
            connection.setAutoCommit(false);
            LOG.info("The autocommit was disabled!" + connection.getAutoCommit());
        } catch (SQLException e) {
            LOG.info("Connection failed" + e);
        }
        return connection;
    }

    /**
     * Begin the transaction with commit()
     */
    public static void beginCommit() {
        try {
            TransactionManager.getInstance().commit();
            LOG.info("Transaction commit is made");
        } catch (SQLException e) {
            LOG.info("Commit is failed" + e);
        }
    }

    /**
     * End the transaction with rollback()
     */
    public static void endTransaction() {
        try {
            TransactionManager.getInstance().rollback();
            LOG.info("Transaction rollback is made");
        } catch (SQLException e) {
            LOG.info("rollback is failed" + e);
        }
    }
}
