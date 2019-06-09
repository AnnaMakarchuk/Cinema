package org.study.commands.clientCommands;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.PositionDto;
import org.study.dto.RegisteredUserDto;
import org.study.dto.TicketDto;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class BuyTicketsNotification extends AbstractClientCommand {
    private static final Logger LOG = Logger.getLogger(BuyTicketsNotification.class);

    private TicketFacade ticketFacade;

    public BuyTicketsNotification() {
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method is a command for view clients purchased tickets
     */
    @Override
    public String execute(HttpServletRequest request) {
        RegisteredUserDto registeredUserDto = (RegisteredUserDto) request.getSession().getAttribute(ParametersNames.USER);
        int userId = registeredUserDto.getUserId();
        PositionDto places = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            places = mapper.readValue(
                    request.getReader().lines().collect(Collectors.joining(System.lineSeparator())),
                    PositionDto.class);
        } catch (IOException e) {
            LOG.error("Reader failed: " + e);
        }
        if (Objects.isNull(places) || Objects.isNull(places.getScheduleId())) {
            return "pages/404.jsp";
        }
        int scheduleId = places.getScheduleId();

        List<TicketDto> ticketDtosList = ticketFacade.addNewTickets(userId, scheduleId, places.getPlaces());
        LOG.info("New tickets was added");
        request.setAttribute(ParametersNames.TICKETS, ticketDtosList);
        return "pages/client/client_tickets.jsp";
    }
}
