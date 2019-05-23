package org.study.models;

import org.study.utils.ResultSetTablesData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Place {

    private int row;
    private int placeNumber;

    public Place(int row, int placeNumber) {
        this.row = row;
        this.placeNumber = placeNumber;
    }

    /**
     * create Place with constructor with an input parameter ResultSet*
     */
    public Place(ResultSet resultSet) throws SQLException {
        this.row = ResultSetTablesData.getIntByName(ResultSetTablesData.TICKET_ROW, resultSet);
        this.placeNumber = ResultSetTablesData.getIntByName(ResultSetTablesData.TICKET_PLACE, resultSet);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
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

        if (row != hallPlace.row) return false;
        return placeNumber == hallPlace.placeNumber;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + placeNumber;
        return result;
    }

    @Override
    public String toString() {
        return "HallPlace{" +
                "row=" + row +
                ", placeNumber=" + placeNumber +
                '}';
    }
}
