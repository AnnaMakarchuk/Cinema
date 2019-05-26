package org.study.DTO;

import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

public class RegisteredUserDTO {

    private int id;
    private String userName;
    private String userSurname;
    private Gender gender;
    private UserRole userRole;
    private String userLogin;
    private String userEMailAddress;

    public RegisteredUserDTO(int id, String userName, String userSurname, Gender gender, UserRole userRole,
                             String userLogin, String userEMailAddress) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.gender = gender;
        this.userRole = userRole;
        this.userLogin = userLogin;
        this.userEMailAddress = userEMailAddress;
    }

    public RegisteredUserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserEMailAddress() {
        return userEMailAddress;
    }

    public void setUserEMailAddress(String userEMailAddress) {
        this.userEMailAddress = userEMailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisteredUserDTO)) return false;

        RegisteredUserDTO that = (RegisteredUserDTO) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userSurname != null ? !userSurname.equals(that.userSurname) : that.userSurname != null) return false;
        if (gender != that.gender) return false;
        if (userRole != that.userRole) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        return userEMailAddress != null ? userEMailAddress.equals(that.userEMailAddress) : that.userEMailAddress == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userSurname != null ? userSurname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userEMailAddress != null ? userEMailAddress.hashCode() : 0);
        return result;
    }
}
