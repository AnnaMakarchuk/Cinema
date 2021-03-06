package org.study.models;

import java.util.List;
import java.util.Map;

public class Hall {
    private int hallId;
    private String hallName;
    private int maxRow;
    private int maxPlacesInRow;
    private Map<Integer, Double> priceType;
    private List<Place> occupiedPlaces;

    public Hall(int hallId, String hallName, int maxRow, int maxPlacesInRow) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.maxRow = maxRow;
        this.maxPlacesInRow = maxPlacesInRow;
    }

    public int getId() {
        return hallId;
    }

    public void setId(int hallId) {
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

    public List<Place> getOccupiedPlaces() {
        return occupiedPlaces;
    }

    public void setOccupiedPlaces(List<Place> occupiedPlaces) {
        this.occupiedPlaces = occupiedPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;

        Hall hall = (Hall) o;

        if (hallId != hall.hallId) return false;
        if (maxRow != hall.maxRow) return false;
        if (maxPlacesInRow != hall.maxPlacesInRow) return false;
        return hallName != null ? hallName.equals(hall.hallName) : hall.hallName == null;
    }

    @Override
    public int hashCode() {
        int result = hallId;
        result = 31 * result + (hallName != null ? hallName.hashCode() : 0);
        result = 31 * result + maxRow;
        result = 31 * result + maxPlacesInRow;
        return result;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + hallId +
                ", hallName='" + hallName + '\'' +
                ", maxRow=" + maxRow +
                ", maxPlacesInRow=" + maxPlacesInRow +
                '}';
    }
}
