package org.study.dto;

import java.util.List;
import java.util.Map;
import org.study.models.Place;

public class HallDto {
    private int hallId;
    private String hallName;
    private int maxRow;
    private int maxPlacesInRow;
    private Map<Integer,Double> priceType;
    private List<PlaceDto> occupiedPlaces;

    public HallDto() {
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getMaxPlacesInRow() {
        return maxPlacesInRow;
    }

    public void setMaxPlacesInRow(int maxPlacesInRow) {
        this.maxPlacesInRow = maxPlacesInRow;
    }

    public Map<Integer, Double> getPriceType() {
        return priceType;
    }

    public void setPriceType(Map<Integer, Double> priceType) {
        this.priceType = priceType;
    }

    public List<PlaceDto> getOccupiedPlaces() {
        return occupiedPlaces;
    }

    public void setOccupiedPlaces(List<PlaceDto> occupiedPlaces) {
        this.occupiedPlaces = occupiedPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HallDto)) return false;

        HallDto hallDto = (HallDto) o;

        if (hallId != hallDto.hallId) return false;
        if (maxRow != hallDto.maxRow) return false;
        if (maxPlacesInRow != hallDto.maxPlacesInRow) return false;
        if (hallName != null ? !hallName.equals(hallDto.hallName) : hallDto.hallName != null) return false;
        if (priceType != null ? !priceType.equals(hallDto.priceType) : hallDto.priceType != null) return false;
        return occupiedPlaces != null ? occupiedPlaces.equals(hallDto.occupiedPlaces) : hallDto.occupiedPlaces == null;
    }

    @Override
    public int hashCode() {
        int result = hallId;
        result = 31 * result + (hallName != null ? hallName.hashCode() : 0);
        result = 31 * result + maxRow;
        result = 31 * result + maxPlacesInRow;
        result = 31 * result + (priceType != null ? priceType.hashCode() : 0);
        result = 31 * result + (occupiedPlaces != null ? occupiedPlaces.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HallDTO{" +
                "hallId=" + hallId +
                ", hallName='" + hallName + '\'' +
                ", maxRow=" + maxRow +
                ", maxPlacesInRow=" + maxPlacesInRow +
                ", priceType=" + priceType +
                ", occupiedPlaces=" + occupiedPlaces +
                '}';
    }
}
