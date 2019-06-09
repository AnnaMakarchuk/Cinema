package org.study.commands.adminCommands;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.dto.SessionScheduleDto;
import org.study.facade.SessionScheduleFacade;
import org.study.facade.TicketFacade;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class CancelMovieCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(AddNewMovieCommand.class);
    public static final String CLIENTS = "clients";

    private SessionScheduleFacade sessionScheduleFacade;
    private UserFacade userFacade;
    private TicketFacade ticketFacade;

    public CancelMovieCommand() {
        this.sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method is cancel movie from the schedule and make this schedule non active.
     * method show tickets List of cancelled session
     * save session with this film.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String idParameter = request.getParameter(ParametersNames.MOVIE_ID);
        if (Objects.isNull(idParameter)) {
            return "pages/404.jsp";
        }
        int movieId = Integer.parseInt(idParameter);

        List<SessionScheduleDto> cancelledScheduleList = sessionScheduleFacade.cancelMovie(movieId);
        LOG.info("Movie with id " + movieId + " no active");

        List<RegisteredUserDto> userDTOList = userFacade.createUserListWithCancelledSchedule
                (getSessionScheduleId(cancelledScheduleList));
        LOG.info("RegisteredUserDTO list for deleted tickets is created");
        request.setAttribute(ParametersNames.SCHEDULES, cancelledScheduleList);
        request.setAttribute(ParametersNames.CLIENTS, userDTOList);

        ticketFacade.deleteTicketBySessionScheduleID((getSessionScheduleId(cancelledScheduleList)));

        return "pages/admin/admin_cancel_schedule.jsp";
    }

    private List<Integer> getSessionScheduleId(List<SessionScheduleDto> cancelledScheduleList) {
        return cancelledScheduleList.stream().map(SessionScheduleDto::getScheduleId).collect(Collectors.toList());
    }
}
