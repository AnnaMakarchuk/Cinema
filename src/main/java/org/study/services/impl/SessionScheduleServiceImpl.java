package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.SessionScheduleDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.SessionSchedule;
import org.study.services.SessionScheduleService;

public class SessionScheduleServiceImpl implements SessionScheduleService {
    /**
     * create instance of DAO class with type SCHEDULE
     */
    private static final SessionScheduleDAO INSTANCE =
            (SessionScheduleDAO) DAOFactory.createDAO(DAOType.SCHEDULE).create();
    private static final Logger LOG = Logger.getLogger(SessionScheduleServiceImpl.class);

    /**
     * this method show concrete session in schedule
     *
     * @param sessionId
     * @return
     */
    @Override
    public SessionSchedule viewSessionById(int sessionId) {
        SessionSchedule sessionSchedule = INSTANCE.get(sessionId);
        LOG.info("SessionScheduleService view session in schedule with id" + sessionId);
        return sessionSchedule;
    }

    /**
     * this method create new session in schedule
     *
     * @param sessionSchedule
     */
    @Override
    public void createSession(SessionSchedule sessionSchedule) {
        INSTANCE.create(sessionSchedule);
        LOG.info("SessionScheduleService crete new session in schedule with id" + sessionSchedule.getId());
    }

    /**
     * this method update data in concrete session in schedule
     *
     * @param sessionSchedule
     */
    @Override
    public void updateSession(SessionSchedule sessionSchedule) {
        INSTANCE.update(sessionSchedule);
        LOG.info("SessionScheduleService update session in schedule with id" + sessionSchedule.getId());
    }

    /**
     * this method delete concrete session in schedule
     *
     * @param sessionSchedule
     */
    @Override
    public void deleteSession(SessionSchedule sessionSchedule) {
        INSTANCE.create(sessionSchedule);
        LOG.info("SessionScheduleService delete session in schedule with id" + sessionSchedule.getId());
    }
}
