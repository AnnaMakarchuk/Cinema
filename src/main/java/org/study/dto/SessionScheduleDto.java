package org.study.dto;

import java.time.LocalTime;
import java.util.List;
import org.study.models.Ticket;
import org.study.models.enums.WeekDay;

public class SessionScheduleDto {
    private int scheduleId;
    private WeekDay weekDay;
    private LocalTime time;
    private List<TimeDto> timeList;
    private String movieName;
    private List<Ticket> ticketList;

    public SessionScheduleDto() {
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<TimeDto> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<TimeDto> timeList) {
        this.timeList = timeList;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionScheduleDto)) return false;

        SessionScheduleDto that = (SessionScheduleDto) o;

        if (scheduleId != that.scheduleId) return false;
        if (weekDay != that.weekDay) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (timeList != null ? !timeList.equals(that.timeList) : that.timeList != null) return false;
        if (movieName != null ? !movieName.equals(that.movieName) : that.movieName != null) return false;
        return ticketList != null ? ticketList.equals(that.ticketList) : that.ticketList == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (timeList != null ? timeList.hashCode() : 0);
        result = 31 * result + (movieName != null ? movieName.hashCode() : 0);
        result = 31 * result + (ticketList != null ? ticketList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SessionScheduleDTO{" +
                "scheduleId=" + scheduleId +
                ", weekDay=" + weekDay +
                ", time=" + time +
                ", movieName='" + movieName + '\'' +
                ", ticketList=" + ticketList +
                '}';
    }
}
