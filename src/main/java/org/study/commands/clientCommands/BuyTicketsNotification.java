package org.study.commands.clientCommands;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.dto.TicketDto;
import org.study.commands.Command;
import org.study.facade.TicketFacade;
import org.study.factories.FacadeFactory;
import org.study.models.enums.WeekDay;

public class BuyTicketsNotification implements Command {
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
//        String day = request.getParameter("day");
//        String movieName = (String) request.getAttribute("schedule.movieName");
//        TimeDTO time = (TimeDTO) request.getAttribute("schedule.timeList.time");
//        int scheduleId = time.getScheduleId();
//
//        User user = (User) request.getSession().getAttribute("client");
//        List<TicketDTO> ticketDTOList = (List<TicketDTO>) request.getAttribute("tickets");
//
//        ticketFacade.addNewTickets(user.getMovieId(), scheduleId, ticketDTOList);

        RegisteredUserDto registeredUserDto = (RegisteredUserDto) request.getSession().getAttribute("user");
        if (Objects.isNull(registeredUserDto)) {
            return "jsp/403_scheme.jsp";
        }


        try {
            System.out.println(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        int userId = registeredUserDto.getUserId();
//
//        String idParameter = request.getParameter("schedule_id");
//        if (Objects.isNull(idParameter)) {
//            return "jsp/404.jsp";
//        }
//        int scheduleId = Integer.parseInt(idParameter);
//
//
//        Enumeration rowType = request.getParameterNames();
//
//        String row = "";
//        String place = "";
//        while (rowType.hasMoreElements()) {
//            row = (String) rowType.nextElement();
//        }
//        List<String> rowList = new ArrayList<>();
//
//        if (row.equals("row_number")) rowList.add(request.getParameter("row_number"));



/*
        TicketDto ticketDto1 = new TicketDto();
        ticketDto1.setMovieName("Dark Phoenix");
        ticketDto1.setScheduleTime(LocalTime.of(10, 30));
        ticketDto1.setWeekDay(WeekDay.SUNDAY);
        ticketDto1.setPlaceNumber(4);
        ticketDto1.setPlaceRow(2);
        ticketDto1.setTicketPrice(50.00);

        TicketDto ticketDto2 = new TicketDto();
        ticketDto2.setMovieName("Dark Phoenix");
        ticketDto2.setScheduleTime(LocalTime.of(10, 30));
        ticketDto2.setWeekDay(WeekDay.SUNDAY);
        ticketDto2.setPlaceNumber(3);
        ticketDto2.setPlaceRow(2);
        ticketDto2.setTicketPrice(50.00);

        List<TicketDto> ticketDtoList = new ArrayList<>();
        ticketDtoList.add(ticketDto2);
        ticketDtoList.add(ticketDto1);

        ticketFacade.addNewTickets(3, 38, ticketDtoList);

        LOG.info("New tickets add");

        request.setAttribute("tickets", ticketDtoList);*/

        return "pages/client/client_add_tickets.jsp";
    }
}
