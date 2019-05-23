package org.study.models;

import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.utils.ResultSetTablesData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends RegisteredUser {
    private double monthSalary;
    private int workingHoursWeek;

    public Administrator(int id, String userName, String userSurname, Gender gender, UserRole userRole,
                         String userLogin, String userEMailAddress, String userPassword, double monthSalary,
                         int workingHoursWeek) {
        super(id, userName, userSurname, gender, userRole, userLogin, userEMailAddress, userPassword);
        this.monthSalary = monthSalary;
        this.workingHoursWeek = workingHoursWeek;
    }

    /**
     * create Administrator with constructor with an input parameter ResultSet*
     */
    public Administrator(ResultSet resultSet) throws SQLException {
        super(resultSet);
        this.monthSalary = ResultSetTablesData.getDoubleByName(ResultSetTablesData.USER_SALARY, resultSet);
        this.workingHoursWeek = ResultSetTablesData.getIntByName(ResultSetTablesData.USER_HOURS, resultSet);
    }

    public double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(double monthSalary) {
        this.monthSalary = monthSalary;
    }

    public int getWorkingHoursWeek() {
        return workingHoursWeek;
    }

    public void setWorkingHoursWeek(int workingHoursWeek) {
        this.workingHoursWeek = workingHoursWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        if (!super.equals(o)) return false;

        Administrator that = (Administrator) o;

        if (Double.compare(that.monthSalary, monthSalary) != 0) return false;
        return workingHoursWeek == that.workingHoursWeek;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(monthSalary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + workingHoursWeek;
        return result;
    }

    @Override
    public String toString() {
        return "\nAdministrator{" + super.toString() +
                "monthSalary=" + monthSalary +
                ", workingHoursWeek=" + workingHoursWeek +
                '}';
    }
}
