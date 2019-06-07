package org.study.models;

import org.study.utils.ResultSetTablesData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ticket {
    private int id;
    private double ticketPrice;
    private SessionSchedule sessionSchedule;
    private Place place;

    public Ticket(int id, SessionSchedule sessionSchedule, Place place, double ticketPrice) {
        this.id = id;
        this.sessionSchedule = sessionSchedule;
        this.place = place;
        this.ticketPrice = ticketPrice;
    }

    /**
     * create Ticket with constructor with an input parameter ResultSet*
     */
    public Ticket(ResultSet resultSet) throws SQLException {
        this.id = ResultSetTablesData.getIntByName(ResultSetTablesData.ID, resultSet);
        this.sessionSchedule = new SessionSchedule(resultSet);
        this.place = new Place(resultSet);
        this.ticketPrice = ResultSetTablesData.getDoubleByName(ResultSetTablesData.TICKET_PRICE, resultSet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public SessionSchedule getSessionSchedule() {
        return sessionSchedule;
    }

    public void setSessionSchedule(SessionSchedule sessionSchedule) {
        this.sessionSchedule = sessionSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (Double.compare(ticket.ticketPrice, ticketPrice) != 0) return false;
        if (sessionSchedule != null ? !sessionSchedule.equals(ticket.sessionSchedule) : ticket.sessionSchedule != null)
            return false;
        return place != null ? place.equals(ticket.place) : ticket.place == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(ticketPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sessionSchedule != null ? sessionSchedule.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketPrice=" + ticketPrice +
                ", sessionSchedule=" + sessionSchedule +
                ", place=" + place +
                '}';
    }
}
