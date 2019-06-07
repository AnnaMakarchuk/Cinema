package org.study.dao.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.dao.MovieGenreDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieGenreDaoImpl implements MovieGenreDao {

    private static final Logger LOG = Logger.getLogger(MovieGenreDaoImpl.class);

    private static final String GENRE_SQL_GET = "SELECT id FROM movie_genre WHERE genre = ?";

    /**
     * this method getUserById enum MovieGenre from DataBase
     */
    @Override
    public int get(String genre) {
        int movieGenre = 0;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(GENRE_SQL_GET)) {
            preparedStatement.setString(1, genre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                movieGenre = resultSet.getInt(1);
                LOG.info("Movie Genre id with name " + genre + " was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return movieGenre;
    }
}
