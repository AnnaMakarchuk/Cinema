package org.study.dto;

public class PlaceDto {
    private Integer row;
    private Integer place;

    public PlaceDto() {
    }

    public PlaceDto(Integer row, Integer place) {
        this.row = row;
        this.place = place;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaceDto)) return false;

        PlaceDto placeDto = (PlaceDto) o;

        if (row != null ? !row.equals(placeDto.row) : placeDto.row != null) return false;
        return place != null ? place.equals(placeDto.place) : placeDto.place == null;
    }

    @Override
    public int hashCode() {
        int result = row != null ? row.hashCode() : 0;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }
}
