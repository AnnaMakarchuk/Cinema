package org.study.models;

public class Ticket {
    private int ticketId;
    private double ticketPrice;
    private SessionSchedule sessionSchedule;
    private Place place;

    public Ticket(int ticketId, SessionSchedule sessionSchedule, Place place, double ticketPrice) {
        this.ticketId = ticketId;
        this.sessionSchedule = sessionSchedule;
        this.place = place;
        this.ticketPrice = ticketPrice;
    }

    public Ticket(Place place, double ticketPrice) {
        this.place = place;
        this.ticketPrice = ticketPrice;
    }

    public int getId() {
        return ticketId;
    }

    public void setId(int id) {
        this.ticketId = ticketId;
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

        if (ticketId != ticket.ticketId) return false;
        if (Double.compare(ticket.ticketPrice, ticketPrice) != 0) return false;
        if (sessionSchedule != null ? !sessionSchedule.equals(ticket.sessionSchedule) : ticket.sessionSchedule != null)
            return false;
        return place != null ? place.equals(ticket.place) : ticket.place == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ticketId;
        temp = Double.doubleToLongBits(ticketPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sessionSchedule != null ? sessionSchedule.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + ticketId +
                ", ticketPrice=" + ticketPrice +
                ", sessionSchedule=" + sessionSchedule +
                ", place=" + place +
                '}';
    }
}
