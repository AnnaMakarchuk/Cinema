package org.study.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class JDBCConnection {

    private static final Logger LOG = Logger.getLogger(JDBCConnection.class);

    private final static String URL = "jdbc:mysql://localhost:3306/cinemaproject?verifyServerCertificate=false" +
            "&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone="
            + TimeZone.getDefault().getID();
    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";

    private static BasicDataSource basicDataSource;

    /**
     * Create a simple datasource of the type: org.apache.commons.dbcp2.BasicDataSource
     * for connection pool
     */
    private static BasicDataSource getBasicDataSource() {
        if (basicDataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(URL);
            ds.setUsername(LOGIN);
            ds.setPassword(PASSWORD);
            ds.setMinIdle(5);
            ds.setMaxIdle(15);
            ds.setMaxTotal(15);
            basicDataSource = ds;
            LOG.info("Data Source Object is created " + basicDataSource.toString());
        }
        return basicDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getBasicDataSource().getConnection();
    }
}
