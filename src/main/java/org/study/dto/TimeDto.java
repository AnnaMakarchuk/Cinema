package org.study.dto;

import java.time.LocalTime;

/**
 * this class is needed to save sessionSchedule id when create view for schedule page and join time for similar movies
 */
public class TimeDto {
    private int scheduleId;
    private LocalTime time;

    public TimeDto(int scheduleId, LocalTime time) {
        this.scheduleId = scheduleId;
        this.time = time;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeDto)) return false;

        TimeDto timeDto = (TimeDto) o;

        if (scheduleId != timeDto.scheduleId) return false;
        return time != null ? time.equals(timeDto.time) : timeDto.time == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimeDTO{" +
                "scheduleId=" + scheduleId +
                ", time=" + time +
                '}';
    }
}
