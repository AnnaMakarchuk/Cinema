package org.study.commands;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.SessionScheduleDto;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;

public class ViewSchedulePage implements Command {
    private static final Logger LOG = Logger.getLogger(ViewSchedulePage.class);

    private SessionScheduleFacade sessionScheduleFacade;

    public ViewSchedulePage() {
        this.sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
    }

    /**
     * this method is a command for view schedule on schedule path
     */
    @Override
    public String execute(HttpServletRequest request) {
        String weekday = request.getParameter("day");
        if (Objects.isNull(weekday)) {
            weekday = "monday";
        }
        List<SessionScheduleDto> allScheduleByDay = sessionScheduleFacade.getAllScheduleByDay(weekday);
        LOG.info("Schedule list for " + weekday + " was obtained");
        request.setAttribute("weekday", weekday);
        request.setAttribute("schedules", allScheduleByDay);
        return "pages/schedule_day.jsp";
    }
}

