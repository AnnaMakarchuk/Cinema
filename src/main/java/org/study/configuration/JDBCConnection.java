package org.study.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnection {
    private static final Logger LOG = Logger.getLogger(JDBCConnection.class);

    private final static String URL = "jdbc:mysql://localhost:3306/cinemaproject?useSSL=false";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";
    private static BasicDataSource ds = new BasicDataSource();

    private JDBCConnection() {
    }

    static {
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(URL);
        ds.setUsername(LOGIN);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(15);
        ds.setMaxTotal(15);
        LOG.info("Data Source Object is created");
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
