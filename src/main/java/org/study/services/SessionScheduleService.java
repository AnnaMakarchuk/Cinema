package org.study.services;

import org.study.models.SessionSchedule;

public interface SessionScheduleService {

    SessionSchedule viewSessionById(int sessionId);

    void createSession(SessionSchedule sessionSchedule);

    void updateSession(SessionSchedule sessionSchedule);

    void deleteSession(SessionSchedule sessionSchedule);

}
