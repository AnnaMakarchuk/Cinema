package org.study.commands.adminCommands;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.TicketDto;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class ViewAvailableTicketsCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(ViewAvailableTicketsCommand.class);

    private TicketFacade ticketFacade;

    public ViewAvailableTicketsCommand() {
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method generate List of SessionSchedule and purchased ticket for each active schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        int pageQuantities = ticketFacade.countPagesQuantity();
        request.setAttribute(ParametersNames.PAGES, pageQuantities);

        String pageNumberParameter = request.getParameter(ParametersNames.PAGE);
        if (Objects.isNull(pageNumberParameter)) {
            pageNumberParameter = "1";
        }
        int page = Integer.parseInt(pageNumberParameter);
        List<TicketDto> ticketListDTO = ticketFacade.allTicketsWithPagination(page);
        LOG.info("List of tickets for page " + page + "was selected");
        request.setAttribute(ParametersNames.TICKETS, ticketListDTO);
        return "pages/admin/admin_tickets_list.jsp";
    }
}
