package org.study.commands.clientCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class DeleteUserTicketCommand extends AbstractClientCommand {
    private static final Logger LOG = Logger.getLogger(ViewClientCabinetCommand.class);

    private TicketFacade ticketFacade;

    public DeleteUserTicketCommand() {
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method is a command for deleting selected ticket from the ticketList
     */
    @Override
    public String execute(HttpServletRequest request) {
        String idParameter = request.getParameter(ParametersNames.TICKET_ID);
        if (Objects.isNull(idParameter)) {
            return "pages/404.jsp";
        }
        int ticketId = Integer.parseInt(idParameter);
        ticketFacade.deleteTicketByID(ticketId);
        LOG.info("Ticket with id " + ticketId + " was deleted");
        return "pages/client/delete_ticket.jsp";
    }
}
