package org.study.commands.adminCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;

public class ChangeSchedule implements Command {
    private static final Logger LOG = Logger.getLogger(ViewCancelledSchedule.class);

    private SessionScheduleFacade sessionScheduleFacade;

    public ChangeSchedule() {
        this.sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
    }

    /**
     * this method try to add movie in non-active Schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        String path = " ";
        String idParameterMovie = request.getParameter("movie_id");
        if (Objects.isNull(idParameterMovie)) {
            return "jsp/404.jsp";
        }
        int movieId = Integer.parseInt(idParameterMovie);

//        String idParameterSchedule = request.getParameter("schedule_id");
//        if (Objects.isNull(idParameterSchedule)) {
//            return "jsp/404.jsp";
//        }
//        int scheduleId = Integer.parseInt(idParameterSchedule);
        int scheduleId = 4;

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
