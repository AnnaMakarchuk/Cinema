package org.study.DAO;

import org.study.models.SessionSchedule;

public interface SessionScheduleDAO {

    SessionSchedule get(int sessionId);

    void create(SessionSchedule sessionSchedule);

    void update(SessionSchedule sessionSchedule);

    void delete(SessionSchedule sessionSchedule);
}
