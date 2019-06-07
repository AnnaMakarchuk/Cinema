package org.study.commands.adminCommands;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.TicketDto;
import org.study.commands.Command;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;

public class ViewAvailableTickets implements Command {
    private static final Logger LOG = Logger.getLogger(ViewAvailableTickets.class);

    private TicketFacade ticketFacade;

    public ViewAvailableTickets() {
        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
    }

    /**
     * this method generate List of SessionSchedule and purchased ticket for each active schedule
     */
    @Override
    public String execute(HttpServletRequest request) {
        int pageQuantities = ticketFacade.countPagesQuantity();
        request.setAttribute("pages", pageQuantities);

        String pageNumberParameter = request.getParameter("page");
        if (Objects.isNull(pageNumberParameter)) {
            pageNumberParameter = "1";
        }
        int page = Integer.parseInt(pageNumberParameter);
        List<TicketDto> ticketListDTO = ticketFacade.allTicketsWithPagination(page);
        LOG.info("List of tickets for page " + page + "was selected");
        request.setAttribute("tickets", ticketListDTO);
        return "pages/admin/admin_tickets_list.jsp";
    }
}
