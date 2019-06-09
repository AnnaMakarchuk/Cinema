package org.study.commands.clientCommands;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.dto.TicketDto;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class ActionWithTicketCommand extends AbstractClientCommand {
    private static final Logger LOG = Logger.getLogger(ViewClientCabinetCommand.class);

    private TicketFacade ticketFacade;

    public ActionWithTicketCommand() {
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method is a command for view clients purchased tickets with active schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        RegisteredUserDto registeredUserDTO = (RegisteredUserDto) request.getSession().getAttribute(ParametersNames.USER);
        int registeredUserId = registeredUserDTO.getUserId();
        List<TicketDto> ticketDtoList = ticketFacade.getAllTicketsByUser(registeredUserId);
        LOG.info("List of tickets bought by user with id " + registeredUserId + " is creates");
        request.setAttribute(ParametersNames.TICKETS, ticketDtoList);
        return "pages/client/client_tickets.jsp";
    }
}
