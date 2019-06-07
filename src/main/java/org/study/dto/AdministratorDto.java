package org.study.dto;

import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

public class AdministratorDto {
    private int administratorId;
    private String administratorName;
    private String administratorSurname;
    private Gender gender;
    private UserRole userRole;
    private String administratorLogin;
    private String administratorEMailAddress;
    private int administratorWorkingHoursPerWeek;

    public AdministratorDto() {
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public String getAdministratorSurname() {
        return administratorSurname;
    }

    public void setAdministratorSurname(String administratorSurname) {
        this.administratorSurname = administratorSurname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getAdministratorLogin() {
        return administratorLogin;
    }

    public void setAdministratorLogin(String administratorLogin) {
        this.administratorLogin = administratorLogin;
    }

    public String getAdministratorEMailAddress() {
        return administratorEMailAddress;
    }

    public void setAdministratorEMailAddress(String administratorEMailAddress) {
        this.administratorEMailAddress = administratorEMailAddress;
    }

    public int getAdministratorWorkingHoursPerWeek() {
        return administratorWorkingHoursPerWeek;
    }

    public void setAdministratorWorkingHoursPerWeek(int administratorWorkingHoursPerWeek) {
        this.administratorWorkingHoursPerWeek = administratorWorkingHoursPerWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdministratorDto)) return false;

        AdministratorDto that = (AdministratorDto) o;

        if (administratorId != that.administratorId) return false;
        if (administratorWorkingHoursPerWeek != that.administratorWorkingHoursPerWeek) return false;
        if (administratorName != null ? !administratorName.equals(that.administratorName) : that.administratorName != null) return false;
        if (administratorSurname != null ? !administratorSurname.equals(that.administratorSurname) : that.administratorSurname != null) return false;
        if (gender != that.gender) return false;
        if (userRole != that.userRole) return false;
        if (administratorLogin != null ? !administratorLogin.equals(that.administratorLogin) : that.administratorLogin != null) return false;
        return administratorEMailAddress != null ? administratorEMailAddress.equals(that.administratorEMailAddress) : that.administratorEMailAddress == null;
    }

    @Override
    public int hashCode() {
        int result = administratorId;
        result = 31 * result + (administratorName != null ? administratorName.hashCode() : 0);
        result = 31 * result + (administratorSurname != null ? administratorSurname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (administratorLogin != null ? administratorLogin.hashCode() : 0);
        result = 31 * result + (administratorEMailAddress != null ? administratorEMailAddress.hashCode() : 0);
        result = 31 * result + administratorWorkingHoursPerWeek;
        return result;
    }

    @Override
    public String toString() {
        return "AdministratorDTO{" +
                "administratorId=" + administratorId +
                ", administratorName='" + administratorName + '\'' +
                ", administratorSurname='" + administratorSurname + '\'' +
                ", gender=" + gender +
                ", userRole=" + userRole +
                ", administratorLogin='" + administratorLogin + '\'' +
                ", administratorEMailAddress='" + administratorEMailAddress + '\'' +
                ", administratorWorkingHoursPerWeek=" + administratorWorkingHoursPerWeek +
                '}';
    }
}
