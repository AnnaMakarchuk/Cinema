package org.study.facade;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.study.dto.SessionScheduleDto;
import org.study.dto.TicketDto;
import org.study.factories.ServiceFactory;
import org.study.models.Place;
import org.study.models.Ticket;
import org.study.services.TicketService;

public class TicketFacade {
    private static final Logger LOG = Logger.getLogger(TicketFacade.class);
    private static final Integer ROW_ON_PAGE = 10;

    private TicketService ticketService;

    public TicketFacade() {
        this.ticketService = ServiceFactory.getInstance().getTicketService();
    }

    /**
     * this method convert list of tickets to list of tickets dto
     */
    public List<TicketDto> getAllTicketsByUser(int userId) {
        List<Ticket> ticketList = ticketService.viewAllTicketsByUser(userId);
        LOG.info("TicketFacade method getUserById all tickets by user with id " + userId);
        return createTicketDtoList(ticketList);
    }

    /**
     * this method delete ticket from user cabinet
     */
    public void deleteTicketByID(int ticketId) {
        ticketService.deleteTicketByUser(ticketId);
        LOG.info("TicketFacade method delete ticket with id " + ticketId);
    }

    /**
     * this method calculate pages quantity for admin page
     */
    public int countPagesQuantity() {
        int totalTicketsQuantity = ticketService.countOccupiedPlaces();
        return totalTicketsQuantity / ROW_ON_PAGE;
    }

    /**
     * this method convert list of tickets with pagination in list of dto tickets
     */
    public List<TicketDto> allTicketsWithPagination(int pageNumber) {
        List<Ticket> ticketList = ticketService.viewAllTicketsByPages(pageNumber, ROW_ON_PAGE);
        return createTicketDtoList(ticketList);
    }

    /**
     * this method delete tickets by Session Schedule id
     */
    public void deleteTicketBySessionScheduleID(List<Integer> scheduleIList) {
        ticketService.deleteTicketByAdmin(scheduleIList);
    }

    /**
     * this method convert ticketDTO in ticket and create new tickets
     */
    public void addNewTickets(int userId, int scheduleId, List<TicketDto> ticketDtoList) {
        List<Ticket> ticketList = getTicketsListFromDto(ticketDtoList);
        ticketService.createTicketByUser(userId, scheduleId, ticketList);
        LOG.info("TicketFacade method add new tickets for user id " + userId);
    }

    private List<TicketDto> createTicketDtoList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(ticket -> {
                    TicketDto ticketDto = new TicketDto();
                    ticketDto.setTicketId(ticket.getId());
                    ticketDto.setTicketPrice(ticket.getTicketPrice());
                    ticketDto.setMovieName(ticket.getSessionSchedule().getMovie().getMovieName());
                    ticketDto.setWeekDay(ticket.getSessionSchedule().getWeekDay());
                    ticketDto.setScheduleTime(ticket.getSessionSchedule().getTime());
                    ticketDto.setHallName(ticket.getSessionSchedule().getHall().getHallName());
                    ticketDto.setPlaceRow(ticket.getPlace().getRow());
                    ticketDto.setPlaceNumber(ticket.getPlace().getPlaceNumber());
                    return ticketDto;
                })
                .collect(Collectors.toList());
    }

    private List<Integer> getSessionScheduleId(List<SessionScheduleDto> cancelledScheduleList) {
        return cancelledScheduleList.stream()
                .map(SessionScheduleDto::getScheduleId)
                .collect(Collectors.toList());
    }

    private List<Ticket> getTicketsListFromDto(List<TicketDto> ticketDtoList) {
        return ticketDtoList.stream()
                .map(ticketDto -> new Ticket(new Place(ticketDto.getPlaceRow(), ticketDto.getPlaceNumber()),
                        ticketDto.getTicketPrice()))
                .collect(Collectors.toList());
    }
}
