package org.study.models;

import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

public class RegisteredUser extends User {

    private String userLogin;
    private String userEMailAddress;
    private String userPassword;
    private List<Ticket> tickets;

    public RegisteredUser(int userId, String userName, String userSurname, Gender gender, UserRole userRole,
                          String userLogin, String userEMailAddress, String userPassword) {
        super(userId, userName, userSurname, gender, userRole);
        this.userLogin = userLogin;
        this.userEMailAddress = userEMailAddress;
        setUserPassword(userPassword);
    }

    public RegisteredUser(String userName, String userSurname, Gender gender,
                          String userLogin, String userEMailAddress, String userPassword) {
        super(userName, userSurname, gender);
        this.userLogin = userLogin;
        this.userEMailAddress = userEMailAddress;
        setUserPassword(userPassword);
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = DigestUtils.md5Hex(userPassword);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" + super.toString() +
                "userLogin='" + userLogin + '\'' +
                ", userEMailAddress='" + userEMailAddress + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
