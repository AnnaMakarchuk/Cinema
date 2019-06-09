package org.study.commands.adminCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class ChangeScheduleCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(ViewCancelledScheduleCommand.class);

    private SessionScheduleFacade sessionScheduleFacade;

    public ChangeScheduleCommand() {
        this.sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
    }

    /**
     * this method try to add movie in non-active Schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        String path = " ";
        String idParameterMovie = request.getParameter(ParametersNames.MOVIE_ID);
        if (Objects.isNull(idParameterMovie)) {
            return "pages/404.jsp";
        }
        int movieId = Integer.parseInt(idParameterMovie);

        String idParameterSchedule = request.getParameter("schedule_id");
        if (Objects.isNull(idParameterSchedule)) {
            return "pages/404.jsp";
        }
        int scheduleId = Integer.parseInt(idParameterSchedule);

        boolean changeSchedule = sessionScheduleFacade.changeSchedule(scheduleId, movieId, true);
        if (changeSchedule) {
            LOG.info("Schedule changed");
            path = "pages/admin/admin_schedule_changed.jsp";
        } else {
            LOG.info("New film duration is too long");
            path = "pages/admin/admin_schedule_nochanged.jsp";
        }
        return path;
    }
}
