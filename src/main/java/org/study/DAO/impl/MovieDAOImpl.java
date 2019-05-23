package org.study.DAO.impl;

import org.apache.log4j.Logger;
import org.study.configuration.TransactionManager;
import org.study.models.Movie;
import org.study.DAO.MovieDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    private static final Logger LOG = Logger.getLogger(MovieDAOImpl.class);
    private static final Connection INSTANCE = TransactionManager.getInstance();

    private static final String MOVIE_SQL_GET = "SELECT m.*, g.genre FROM movie as m JOIN movie_genre as g " +
            "ON m.genre_id = g.id WHERE m.id IN (?)";
    private static final String MOVIE_SQL_CREATE = "INSERT INTO movie (name, genre_id, duration, ageLimit, " +
            "description) VALUES (?, ?, ?, ?, ?)";
    private static final String MOVIE_SQL_UPDATE = "UPDATE movie SET name = (?), genre_id = (?), duration = (?), " +
            "ageLimit = (?), description = (?) WHERE id IN (?)";
    private static final String MOVIE_SQL_DELETE = "DELETE FROM movie WHERE name IN (?)";
    private static final String MOVIE_SQL_GET_ALL = "SELECT m.*, g.genre FROM movie as m JOIN movie_genre as g " +
            "ON m.genre_id = g.id";
    private static final String GENRE_SQL_GETID = "SELECT id FROM movie_genre WHERE genre IN (?)";


    /**
     * this method get movie from database by id
     *
     * @param movieId
     * @return movie
     */
    @Override
    public Movie get(int movieId) {
        Movie movie = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(MOVIE_SQL_GET);
            preparedStatement.setInt(1, movieId);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                movie = new Movie(resultSet);
                LOG.info("Movie with id " + movieId + " is selected");
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
        return movie;
    }

    /**
     * this method create new movie in database, this method firstly get moviegenre id for genre name
     * and the substitute this parameter in movie table column genre_id
     *
     * @param movie
     */
    @Override
    public void create(Movie movie) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(GENRE_SQL_GETID);
            preparedStatement1.setString(1, movie.getMovieGenre().name().toLowerCase());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int genreId = resultSet1.getInt("id");
            preparedStatement2 = INSTANCE.prepareStatement(MOVIE_SQL_CREATE);
            setInsertValues(preparedStatement2, movie, genreId);
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New movie was added");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set closing for getting genre id closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting genre id closing is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for movie create closing is failed", e);
            }
        }
    }

    /**
     * this method update movie in database, this method firstly get moviegenre id for genre name
     * (genre can be also changed in movie)
     * and the substitute this parameter in movie table column genre_id.
     *
     * @param movie
     */
    @Override
    public void update(Movie movie) {
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection con = null;
        try {
            con = INSTANCE;
            preparedStatement1 = con.prepareStatement(GENRE_SQL_GETID);
            preparedStatement1.setString(1, movie.getMovieGenre().name().toLowerCase());
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int genreId = resultSet1.getInt("id");
            preparedStatement2 = INSTANCE.prepareStatement(MOVIE_SQL_UPDATE);
            setInsertValues(preparedStatement2, movie, genreId);
            preparedStatement2.setInt(6, movie.getId());
            preparedStatement2.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("New movie" + movie.getMovieName() + "was updated");
        } catch (SQLException e) {
            LOG.error("Transaction failed, rollback the transaction", e);
            TransactionManager.endTransaction();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
            } catch (SQLException e) {
                LOG.error("Result Set for getting genre id closing is failed", e);
            }
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for getting genre id closing  is failed", e);
            }
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                LOG.error("Prepared Statement for movie update closing is failed", e);
            }
        }
    }

    /**
     * this method delete movie from database
     *
     * @param movie
     */
    @Override
    public void delete(Movie movie) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(MOVIE_SQL_DELETE);
            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.executeUpdate();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            LOG.info("The transaction was successfully");
            LOG.info("The movie with name " + movie.getMovieName() + "was delete");
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
     * this method get all movies from database, create arraylist
     *
     * @return
     */
    @Override
    public List<Movie> getAll() {
        List<Movie> movieList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = INSTANCE.prepareStatement(MOVIE_SQL_GET_ALL);
            resultSet = preparedStatement.executeQuery();
            LOG.info("Committing data here...");
            TransactionManager.beginCommit();
            while (resultSet.next()) {
                movieList.add(new Movie(resultSet));
                LOG.info("List of all movies is create");
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
        return movieList;
    }

    private void setInsertValues(PreparedStatement preparedStatement, Movie movie, int genreId) throws SQLException {
        preparedStatement.setString(1, movie.getMovieName());
        preparedStatement.setInt(2, genreId);
        preparedStatement.setInt(3, movie.getMovieDuration());
        preparedStatement.setInt(4, movie.getAgeLimit());
        preparedStatement.setString(5, movie.getMovieDescription());
    }
}
