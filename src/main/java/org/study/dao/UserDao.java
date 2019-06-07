package org.study.dao;

import org.study.models.RegisteredUser;

public interface UserDao {

    RegisteredUser getUserById(int userId);

    void create(int userRoleId, RegisteredUser registeredUser);

    void delete(int userID);

    void update(int userID, String clientLogin, String clientPassword);

    RegisteredUser getUserByLoginAndPassword(String userLogin, String userPassword);
}
