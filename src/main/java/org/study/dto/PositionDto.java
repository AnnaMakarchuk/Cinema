package org.study.dto;

import java.util.List;

public class PositionDto {

    private Integer scheduleId;
    private List<PlaceDto> places;

    public void setPlaces(List<PlaceDto> places) {
        this.places = places;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getScheduleId() {

        return scheduleId;
    }

    public List<PlaceDto> getPlaces() {
        return places;
    }
}
