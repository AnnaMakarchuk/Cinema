package org.study.services;

import java.util.List;
import org.study.models.SessionSchedule;

public interface SessionScheduleService {

    List<SessionSchedule> updateScheduleCancelMovie(int movieId);

    List<SessionSchedule> viewAllScheduleByWeekDay(String weekday);

    List<SessionSchedule> createNonActiveSchedule(boolean isActive);

    boolean updateSession(int scheduleId, int movieId, boolean isActive);

    SessionSchedule getByScheduleId(int scheduleId);
}
