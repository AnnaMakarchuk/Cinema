package org.study.dao;

import java.time.LocalTime;
import java.util.List;
import org.study.models.SessionSchedule;

public interface SessionScheduleDao {

    SessionSchedule get(int scheduleId);

    void updateIsActive(int movieId, boolean isActive);

    void updateMovieInSchedule(int scheduleId, int movieId, boolean isActive);

    List<SessionSchedule> getAllByDay(String weekday);

    List<SessionSchedule> getScheduleByMovieId(int movieId, boolean isActive);

    List<SessionSchedule> getAllNonActive(boolean isActive);

    LocalTime getScheduleTime(int scheduleId);
}
