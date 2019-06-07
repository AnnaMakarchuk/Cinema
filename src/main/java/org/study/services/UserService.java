package org.study.services;

import org.study.models.RegisteredUser;

import java.util.List;

public interface UserService {

    RegisteredUser viewUser(int userId);

    List<RegisteredUser> viewAllAvailableUsers();

    void registerNewUser(RegisteredUser registeredUser);

    void updateUserInformation(RegisteredUser registeredUser);

    void deleteUser(RegisteredUser registeredUser);

}
