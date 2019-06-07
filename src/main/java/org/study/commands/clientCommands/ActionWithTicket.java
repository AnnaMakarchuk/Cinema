package org.study.commands.clientCommands;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.dto.TicketDto;
import org.study.commands.Command;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;

public class ActionWithTicket implements Command {
    private static final Logger LOG = Logger.getLogger(ViewClientCabinet.class);

    private TicketFacade ticketFacade;

    public ActionWithTicket() {
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method is a command for view clients purchased tickets with active schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        RegisteredUserDto registeredUserDTO = (RegisteredUserDto) request.getSession().getAttribute("user");
        if (Objects.isNull(registeredUserDTO)) {
            return "jsp/404.jsp";
        }
        int registeredUserId = registeredUserDTO.getUserId();
        List<TicketDto> ticketDtoList = ticketFacade.getAllTicketsByUser(registeredUserId);
        LOG.info("List of tickets bought by user with id " + registeredUserId + " is creates");
        request.setAttribute("tickets", ticketDtoList);
        return "pages/client/client_tickets.jsp";
    }
}
