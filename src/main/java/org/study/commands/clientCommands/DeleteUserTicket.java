package org.study.commands.clientCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;

public class DeleteUserTicket implements Command {
    private static final Logger LOG = Logger.getLogger(ViewClientCabinet.class);

    private TicketFacade ticketFacade;

    public DeleteUserTicket() {
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method is a command for deleting selected ticket from the ticketList
     */
    @Override
    public String execute(HttpServletRequest request) {
        String idParameter = request.getParameter("ticket_id");
        if (Objects.isNull(idParameter)) {
            return "jsp/404.jsp";
        }
        int ticketId = Integer.parseInt(idParameter);
        ticketFacade.deleteTicketByID(ticketId);
        LOG.info("Ticket with id " + ticketId + " was deleted");
        return "pages/client/delete_ticket.jsp";
    }
}
