package org.study.services.impl;

import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.study.dao.MovieDao;
import org.study.dao.SessionScheduleDao;
import org.study.factories.DaoFactory;
import org.study.models.Movie;
import org.study.models.SessionSchedule;
import org.study.services.SessionScheduleService;

public class SessionScheduleServiceImpl implements SessionScheduleService {
    private static final Logger LOG = Logger.getLogger(SessionScheduleServiceImpl.class);

    private SessionScheduleDao sessionScheduleDao;
    private MovieDao movieDao;

    public SessionScheduleServiceImpl() {
        this.sessionScheduleDao = DaoFactory.getInstance().getSessionScheduleDao();
        this.movieDao = DaoFactory.getInstance().getMovieDao();
    }

    /**
     * this method update movie and set status nonActive,
     * this method update IsActive field and set 'false' by id of the cancelled movie
     */
    @Override
    public List<SessionSchedule> updateScheduleCancelMovie(int movieId) {
        movieDao.updateMovieStatus(movieId, false);
        sessionScheduleDao.updateIsActive(movieId, false);
        LOG.info("SessionSchedule isActive updateIsActive on false by movie id " + movieId);
        List<SessionSchedule> sessionScheduleList = sessionScheduleDao.getScheduleByMovieId(movieId, false);
        LOG.info("SessionScheduleService getUserById non active sessions in schedule with movie id" + movieId);
        return sessionScheduleList;
    }

    /**
     * this method create all schedule
     */
    @Override
    public List<SessionSchedule> viewAllScheduleByWeekDay(String weekday) {
        List<SessionSchedule> sessionScheduleList = sessionScheduleDao.getAllByDay(weekday);
        LOG.info("SessionScheduleService getUserById all schedule " + sessionScheduleList);
        return sessionScheduleList;
    }

    /**
     * this method getUserById non active session schedule list
     */
    @Override
    public List<SessionSchedule> createNonActiveSchedule(boolean isActive) {
        return sessionScheduleDao.getAllNonActive(isActive);
    }

    /**
     * this method getUserById movie Id and movie duration by name, check possibility to add movie in session by movie duration
     */
    @Override
    public boolean updateSession(int scheduleId, int movieId, boolean isActive) {
        boolean changeSchedule;
        Movie movie = movieDao.getMovieById(movieId);
        int movieDuration = movie.getMovieDuration();
        LocalTime scheduleTime = sessionScheduleDao.getScheduleTime(scheduleId);
        if (scheduleTime == LocalTime.of(22, 0)) {
            sessionScheduleDao.updateMovieInSchedule(scheduleId, movieId, isActive);
            changeSchedule = true;
        } else {
            LocalTime changedMovie = scheduleTime.plusMinutes(movieDuration);
            LocalTime nextSchedule = sessionScheduleDao.getScheduleTime(scheduleId + 1);
            if (changedMovie.isBefore(nextSchedule)) {
                sessionScheduleDao.updateMovieInSchedule(scheduleId, movieId, isActive);
                changeSchedule = true;
            } else {
                changeSchedule = false;
            }
        }
        return changeSchedule;
    }

    /**
     * this method getUserById schedule by id
     */
    @Override
    public SessionSchedule getByScheduleId(int scheduleId) {
        return sessionScheduleDao.get(scheduleId);
    }
}
