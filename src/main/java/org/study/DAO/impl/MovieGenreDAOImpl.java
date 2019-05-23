package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.models.enums.MovieGenre;
import org.study.DAO.MovieGenreDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieGenreDAOImpl implements MovieGenreDAO {

    private static final Logger LOG = Logger.getLogger(MovieGenreDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String GENRE_SQL_GET = "SELECT * FROM movie_genre WHERE genre IN (?)";
    private static final String GENRE_SQL_CREATE = "INSERT INTO movie_genre (genre) VALUES (?)";

    /**
     * this method get enum MovieGenre from DataBase
     *
     * @param genre
     * @return enum constant value
     */
    @Override
    public MovieGenre get(String genre) {
        MovieGenre movieGenre = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(GENRE_SQL_GET);
            preparedStatement.setString(1, genre);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                movieGenre = MovieGenre.valueOf(resultSet.getString("genre").toUpperCase());
                LOG.info("Movie Genre with name " + genre + " is selected");
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
        return movieGenre;
    }

    /**
     * this method add new MovieGenre in Database in case new value in enum MovieGenre
     *
     * @param movieGenre
     */
    @Override
    public void create(MovieGenre movieGenre) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(GENRE_SQL_CREATE);
            setParameters(preparedStatement, movieGenre);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New movie genre was added");
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
     * this method not implemented. Class MovieGenre is enum
     *
     * @param movieGenre
     */
    @Override
    public void update(MovieGenre movieGenre) {
    }

    /**
     * this method not implemented. Class MovieGenre is enum
     *
     * @param movieGenre
     */
    @Override
    public void delete(MovieGenre movieGenre) {
    }

    private void setParameters(PreparedStatement preparedStatement, MovieGenre movieGenre) throws SQLException {
        preparedStatement.setString(1, movieGenre.name().toLowerCase());
    }
}
