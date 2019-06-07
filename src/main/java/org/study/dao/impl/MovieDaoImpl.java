package org.study.dao.impl;

import org.apache.log4j.Logger;
import org.study.dao.mappers.Mapper;
import org.study.configuration.TransactionManager;
import org.study.factories.MapperFactory;
import org.study.models.Movie;
import org.study.dao.MovieDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
    private static final Logger LOG = Logger.getLogger(MovieDaoImpl.class);
    private Mapper<Movie> movieMapper = MapperFactory.getInstance().getMovieMapper();

    private static final String MOVIE_SQL_GET = "SELECT m.id as id_movie, m.name as name_movie, " +
            "m.duration, m.ageLimit, m.description, g.genre FROM movie as m " +
            "JOIN movie_genre as g ON m.genre_id = g.id WHERE m.id = (?)";
    private static final String MOVIE_SQL_GET_ALL = "SELECT m.id as id_movie, m.name as name_movie, " +
            "m.duration, m.ageLimit, m.description, g.genre FROM movie as m " +
            "JOIN movie_genre as g ON m.genre_id = g.id WHERE m.isActive = (?)";
    private static final String MOVIE_SQL_CREATE = "INSERT INTO movie (name, genre_id, duration, ageLimit, " +
            "description, isActive) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String MOVIE_SQL_UPDATE = "UPDATE movie SET isActive = (?) WHERE id = (?)";


    /**
     * this method getUserById Movie object by movie name
     */
    @Override
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(MOVIE_SQL_GET);) {
            preparedStatement.setInt(1, movieId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    movie = movieMapper.createModel(resultSet);
                }
                LOG.info("Movie with id " + movieId + " was selected");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return movie;
    }

    /**
     * this method getUserById all movies from database
     */
    @Override
    public List<Movie> getAll(boolean isActive) {
        List<Movie> movieList = new ArrayList<>();
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(MOVIE_SQL_GET_ALL);) {
            preparedStatement.setBoolean(1, isActive);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    movieList.add(movieMapper.createModel(resultSet));
                }
                LOG.info("List of all movies with status " + isActive + " was created");
            }
        } catch (SQLException e) {
            LOG.error("Connection failed", e);
        }
        return movieList;
    }

/**
 * this method create new movie in database, this method firstly getUserById moviegenre id for genre name
 * and the substitute this parameter in movie table column genre_id
 */
    @Override
    public void create(int movieGenreId, Movie movie) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(MOVIE_SQL_CREATE);) {
            setInsertValues(preparedStatement, movie, movieGenreId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }
/**
 * update movie status isActive
 */
    @Override
    public void updateMovieStatus(int movieId, boolean isActive) {
        try (PreparedStatement preparedStatement = TransactionManager.getInstance().prepareStatement(MOVIE_SQL_UPDATE);) {
            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setInt(2, movieId);
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("Movie status was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        }
    }

    private void setInsertValues(PreparedStatement preparedStatement, Movie movie, int genreId) throws SQLException {
        preparedStatement.setInt(3, movie.getMovieDuration());
        preparedStatement.setInt(4, movie.getAgeLimit());
        preparedStatement.setString(5, movie.getMovieDescription());
        preparedStatement.setBoolean(6, true);
    }


}
