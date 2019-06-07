package org.study.models;

import org.study.models.enums.WeekDay;

import java.time.LocalTime;

public class SessionSchedule {
    private int scheduleId;
    private WeekDay weekDay;
    private LocalTime time;
    private Hall hall;
    private Movie movie;

    public SessionSchedule(int scheduleId, WeekDay weekDay, LocalTime time, Hall hall, Movie movie) {
        this.scheduleId = scheduleId;
        this.weekDay = weekDay;
        this.time = time;
        this.hall = hall;
        this.movie = movie;
    }

    public SessionSchedule() {
    }

    public int getId() {
        return scheduleId;
    }

    public void setId(int id) {
        this.scheduleId = id;
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

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionSchedule)) return false;

        SessionSchedule that = (SessionSchedule) o;

        if (scheduleId != that.scheduleId) return false;
        if (weekDay != that.weekDay) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (hall != null ? !hall.equals(that.hall) : that.hall != null) return false;
        return movie != null ? movie.equals(that.movie) : that.movie == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (hall != null ? hall.hashCode() : 0);
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SessionSchedule{" +
                "id=" + scheduleId +
                ", weekDay=" + weekDay +
                ", time=" + time +
                ", hall=" + hall +
                ", movie=" + movie +
                '}';
    }
}
