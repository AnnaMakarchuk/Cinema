package org.study.models;

import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.utils.ResultSetTablesData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String userName;
    private String userSurname;
    private Gender gender;
    private UserRole userRole;

    public User(int id, String userName, String userSurname, Gender gender, UserRole userRole) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.gender = gender;
        this.userRole = userRole;
    }

    /**
     * create User with constructor with an input parameter ResultSet*
     */
    public User(ResultSet resultSet) throws SQLException {
        this.id = ResultSetTablesData.getIntByName(ResultSetTablesData.ID, resultSet);
        this.userName = ResultSetTablesData.getStringByName(ResultSetTablesData.NAME, resultSet);
        this.userSurname = ResultSetTablesData.getStringByName(ResultSetTablesData.USER_SURNAME, resultSet);
        this.gender = ResultSetTablesData.getGender(resultSet);
        this.userRole = ResultSetTablesData.getUserRole(resultSet);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userSurname != null ? !userSurname.equals(user.userSurname) : user.userSurname != null) return false;
        if (gender != user.gender) return false;
        return userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userSurname != null ? userSurname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", gender=" + gender +
                ", userRole=" + userRole +
                '}';
    }
}
