package org.study.models;

import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.utils.ResultSetTablesData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisteredUser extends User {

    private String userLogin;
    private String userEMailAddress;
    private String userPassword;

    public RegisteredUser(int id, String userName, String userSurname, Gender gender, UserRole userRole,
                          String userLogin, String userEMailAddress, String userPassword) {
        super(id, userName, userSurname, gender, userRole);
        this.userLogin = userLogin;
        this.userEMailAddress = userEMailAddress;
        this.userPassword = userPassword;
    }

    /**
     * create RegisteredUser with constructor with an input parameter ResultSet*
     */
    public RegisteredUser(ResultSet resultSet) throws SQLException {
        super(resultSet);
        this.userLogin = ResultSetTablesData.getStringByName(ResultSetTablesData.USER_LOGIN, resultSet);
        this.userEMailAddress = ResultSetTablesData.getStringByName(ResultSetTablesData.USER_EMAIL, resultSet);
        this.userPassword = ResultSetTablesData.getStringByName(ResultSetTablesData.USER_PASSWORD, resultSet);
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
        this.userPassword = userPassword;
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
