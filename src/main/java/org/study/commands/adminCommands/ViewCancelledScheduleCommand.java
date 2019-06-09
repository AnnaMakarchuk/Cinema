package org.study.commands.adminCommands;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.MovieDto;
import org.study.dto.SessionScheduleDto;
import org.study.facade.MovieFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class ViewCancelledScheduleCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(ViewCancelledScheduleCommand.class);

    private SessionScheduleFacade sessionScheduleFacade;
    private MovieFacade movieFacade;

    public ViewCancelledScheduleCommand() {
        sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
        movieFacade = FacadeFactory.getInstance().getMovieFacade();
    }

    /**
     * this method show non active schedule with possibility of it changing
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<SessionScheduleDto> cancelledScheduleList = sessionScheduleFacade.viewNonActiveSchedule();
        LOG.info("Non active schedule was selected");
        request.setAttribute(ParametersNames.SCHEDULES, cancelledScheduleList);
        LOG.info("Set attribute id schedules for next page");
        List<MovieDto> movieDtos = movieFacade.getAllMovies(true);
        request.setAttribute(ParametersNames.MOVIES, movieDtos);
        LOG.info("Set attribute movies for next page with session schedule");
        return "pages/admin/admin_nonactive_schedule.jsp";
    }
}
