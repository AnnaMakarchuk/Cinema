package org.study.models;

public class Place {

    private int placeRow;
    private int placeNumber;

    public Place(int placeRow, int placeNumber) {
        this.placeRow = placeRow;
        this.placeNumber = placeNumber;
    }

    public int getRow() {
        return placeRow;
    }

    public void setRow(int placeRow) {
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
        if (!(o instanceof Place)) return false;

        Place hallPlace = (Place) o;

        if (placeRow != hallPlace.placeRow) return false;
        return placeNumber == hallPlace.placeNumber;
    }

    @Override
    public int hashCode() {
        int result = placeRow;
        result = 31 * result + placeNumber;
        return result;
    }

    @Override
    public String toString() {
        return "HallPlace{" +
                "row=" + placeRow +
                ", placeNumber=" + placeNumber +
                '}';
    }
}
