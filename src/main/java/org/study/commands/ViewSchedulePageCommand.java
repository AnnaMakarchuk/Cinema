package org.study.commands;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.SessionScheduleDto;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class ViewSchedulePageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ViewSchedulePageCommand.class);

    private SessionScheduleFacade sessionScheduleFacade;

    public ViewSchedulePageCommand() {
        this.sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
    }

    /**
     * this method is a command for view schedule on schedule path
     */
    @Override
    public String execute(HttpServletRequest request) {
        String weekday = request.getParameter(ParametersNames.SCHEDULE_DAY);
        if (Objects.isNull(weekday)) {
            weekday = "monday";
        }
        List<SessionScheduleDto> allScheduleByDay = sessionScheduleFacade.getAllScheduleByDay(weekday);
        LOG.info("Schedule list for " + weekday + " was obtained");
        request.setAttribute(ParametersNames.SCHEDULE_WEEKDAY, weekday);
        request.setAttribute(ParametersNames.SCHEDULES, allScheduleByDay);
        return "pages/schedule_day.jsp";
    }

    /**
     * this method always return true for indicated command. This pages is in access for any unregistered user
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        return true;
    }
}

