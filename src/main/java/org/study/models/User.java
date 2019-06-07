package org.study.models;

import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

public class User {
    private int userId;
    private String userName;
    private String userSurname;
    private Gender gender;
    private UserRole userRole;

    public User(int userId, String userName, String userSurname, Gender gender, UserRole userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.gender = gender;
        this.userRole = userRole;
    }

    public User(String userName, String userSurname, Gender gender) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.gender = gender;
    }

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
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

        if (userId != user.userId) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userSurname != null ? !userSurname.equals(user.userSurname) : user.userSurname != null) return false;
        if (gender != user.gender) return false;
        return userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userSurname != null ? userSurname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", gender=" + gender +
                ", userRole=" + userRole +
                '}';
    }
}
