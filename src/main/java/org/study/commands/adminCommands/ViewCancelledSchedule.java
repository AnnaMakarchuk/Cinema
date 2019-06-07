package org.study.commands.adminCommands;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.MovieDto;
import org.study.dto.SessionScheduleDto;
import org.study.commands.Command;
import org.study.facade.MovieFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;

public class ViewCancelledSchedule implements Command {
    private static final Logger LOG = Logger.getLogger(ViewCancelledSchedule.class);

    private SessionScheduleFacade sessionScheduleFacade;
    private MovieFacade movieFacade;

    public ViewCancelledSchedule() {
        sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
        movieFacade = FacadeFactory.getInstance().getMovieFacade();
    }

    /**
     * this method show non active schedule with possibility of it changing
     */
    @Override
    public String execute(HttpServletRequest request) {
        boolean isActive = true;
        List<SessionScheduleDto> cancelledScheduleList = sessionScheduleFacade.viewNonActiveSchedule();
        LOG.info("Non active schedule was selected");
        request.setAttribute("schedules", cancelledScheduleList);
        LOG.info("Set attribute id schedules for next page");
        List<MovieDto> movieDtos = movieFacade.getAllMovies(true);
        request.setAttribute("movies", movieDtos);
        LOG.info("Set attribute movies for next page with session schedule");
        return "pages/admin/admin_nonactive_schedule.jsp";
    }
}
