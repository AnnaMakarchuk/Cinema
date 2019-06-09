package org.study.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.HallDto;
import org.study.dto.SessionScheduleDto;
import org.study.facade.HallFacade;
import org.study.facade.SessionScheduleFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class ViewHallSchemeCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ViewHallSchemeCommand.class);

    private SessionScheduleFacade sessionScheduleFacade;
    private HallFacade hallFacade;

    public ViewHallSchemeCommand() {
        this.sessionScheduleFacade = FacadeFactory.getInstance().getSessionScheduleFacade();
        this.hallFacade = FacadeFactory.getInstance().getHallFacade();
    }

    /**
     * this method is a command for view hall scheme for concrete schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        String idParameter = request.getParameter(ParametersNames.SCHEDULE_ID);
        if (Objects.isNull(idParameter)) {
            return "pages/404.jsp";
        }
        int scheduleId = Integer.parseInt(idParameter);

        SessionScheduleDto sessionScheduleDto = sessionScheduleFacade.getScheduleById(scheduleId);
        LOG.info("Schedule for scheme was getUserById");
        request.setAttribute(ParametersNames.SCHEDULE, sessionScheduleDto);

        HallDto hallDto = hallFacade.getHallDataWithPriceAndOccuipedPlaces(scheduleId);
        LOG.info("Hall scheme data was getUserById");
        request.setAttribute(ParametersNames.HALL, hallDto);

        ObjectMapper mapper = new ObjectMapper();
        try {
            request.setAttribute(ParametersNames.OCCUPIED_PLACES, mapper.writeValueAsString(hallDto.getOccupiedPlaces()));
        } catch (JsonProcessingException e) {
            LOG.error("Json exception: ", e);
        }
        return "pages/hall_scheme.jsp";
    }

    /**
     * this method always return true for indicated command. This pages is in access for any unregistered user
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        return true;
    }
}
