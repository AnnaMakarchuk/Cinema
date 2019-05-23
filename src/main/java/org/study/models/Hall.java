package org.study.models;

import org.study.utils.ResultSetTablesData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Hall {
    private int id;
    private String hallName;
    private int maxRow;
    private int maxPlacesInRow;

    public Hall(int id, String hallName, int maxRow, int maxPlacesInRow) {
        this.id = id;
        this.hallName = hallName;
        this.maxRow = maxRow;
        this.maxPlacesInRow = maxPlacesInRow;
    }

    /**
     * create Hall with constructor with an input parameter ResultSet*
     */
    public Hall(ResultSet resultSet) throws SQLException {
        this.id = ResultSetTablesData.getIntByName(ResultSetTablesData.ID, resultSet);
        this.hallName = ResultSetTablesData.getStringByName(ResultSetTablesData.NAME, resultSet);
        this.maxRow = ResultSetTablesData.getIntByName(ResultSetTablesData.HALL_ROW_NUMBER, resultSet);
        this.maxPlacesInRow = ResultSetTablesData.getIntByName(ResultSetTablesData.HALL_PLACES_IN_ROW, resultSet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;

        Hall hall = (Hall) o;

        if (id != hall.id) return false;
        if (maxRow != hall.maxRow) return false;
        if (maxPlacesInRow != hall.maxPlacesInRow) return false;
        return hallName != null ? hallName.equals(hall.hallName) : hall.hallName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hallName != null ? hallName.hashCode() : 0);
        result = 31 * result + maxRow;
        result = 31 * result + maxPlacesInRow;
        return result;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", hallName='" + hallName + '\'' +
                ", maxRow=" + maxRow +
                ", maxPlacesInRow=" + maxPlacesInRow +
                '}';
    }
}
