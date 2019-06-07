package org.study.dto;

import java.time.LocalTime;
import org.study.models.enums.WeekDay;

public class TicketDto {
    private int ticketId;
    private double ticketPrice;
    private String movieName;
    private WeekDay weekDay;
    private LocalTime scheduleTime;
    private String hallName;
    private int placeRow;
    private int placeNumber;

    public TicketDto() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getPlaceRow() {
        return placeRow;
    }

    public void setPlaceRow(int placeRow) {
        this.placeRow = placeRow;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketDto)) return false;

        TicketDto ticketDto = (TicketDto) o;

        if (ticketId != ticketDto.ticketId) return false;
        if (Double.compare(ticketDto.ticketPrice, ticketPrice) != 0) return false;
        if (placeRow != ticketDto.placeRow) return false;
        if (placeNumber != ticketDto.placeNumber) return false;
        if (movieName != null ? !movieName.equals(ticketDto.movieName) : ticketDto.movieName != null) return false;
        if (weekDay != ticketDto.weekDay) return false;
        if (scheduleTime != null ? !scheduleTime.equals(ticketDto.scheduleTime) : ticketDto.scheduleTime != null) return false;
        return hallName != null ? hallName.equals(ticketDto.hallName) : ticketDto.hallName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ticketId;
        temp = Double.doubleToLongBits(ticketPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (movieName != null ? movieName.hashCode() : 0);
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        result = 31 * result + (scheduleTime != null ? scheduleTime.hashCode() : 0);
        result = 31 * result + (hallName != null ? hallName.hashCode() : 0);
        result = 31 * result + placeRow;
        result = 31 * result + placeNumber;
        return result;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "ticketId=" + ticketId +
                ", ticketPrice=" + ticketPrice +
                ", movieName='" + movieName + '\'' +
                ", weekDay=" + weekDay +
                ", time=" + scheduleTime +
                ", hallName='" + hallName + '\'' +
                ", row=" + placeRow +
                ", placeNumber=" + placeNumber +
                '}';
    }
}
