package org.study.commands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.HallDto;
import org.study.dto.SessionScheduleDto;
import org.study.facade.HallFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;

public class ViewHallScheme implements Command {
    private static final Logger LOG = Logger.getLogger(ViewHallScheme.class);

    private SessionScheduleFacade sessionScheduleFacade;
    private HallFacade hallFacade;

    public ViewHallScheme() {
        this.sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
        this.hallFacade = FacadeFactory.getInstance().getHallFacade();
    }

    /**
     * this method is a command for view hall scheme for concrete schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        String idParameter = request.getParameter("schedule_id");
        if (Objects.isNull(idParameter)) {
            return "jsp/404.jsp";
        }
        int scheduleId = Integer.parseInt(idParameter);

        SessionScheduleDto sessionScheduleDto = sessionScheduleFacade.getScheduleById(scheduleId);
        LOG.info("Schedule for scheme was getUserById");
        request.setAttribute("schedule", sessionScheduleDto);

        HallDto hallDto = hallFacade.getHallDataWithPriceAndOccuipedPlaces(scheduleId);
        LOG.info("Hall scheme data was getUserById");
        request.setAttribute("hall", hallDto);

        return "pages/hall_scheme.jsp";
    }
}
