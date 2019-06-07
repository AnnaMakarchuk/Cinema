package org.study.models;

import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

public class Administrator extends RegisteredUser {
    private double monthSalary;
    private int workingHoursWeek;

    public Administrator(int administratorId, String administratorName, String administratorSurname, Gender gender,
                         UserRole userRole, String administratorLogin, String administratorEMailAddress,
                         String administratorPassword, double monthSalary, int workingHoursWeek) {
        super(administratorId, administratorName, administratorSurname, gender, userRole, administratorLogin,
                administratorEMailAddress, administratorPassword);
        this.monthSalary = monthSalary;
        this.workingHoursWeek = workingHoursWeek;
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
