package org.study.services;

import java.util.List;
import org.study.models.RegisteredUser;

public interface UserService {

    RegisteredUser viewUser(int userId);

    void registerNewUser(String name, String surname, String userRole, String gender, String login,
                         String userEMailAddress, String password);

    RegisteredUser updateUserInformation (int clientId, String clientLogin, String clientPassword);

    void deleteUser(int userId);

    RegisteredUser getUserByLoginPassword(String userLogin, String userPassword);

    List<RegisteredUser> createListUsersFromTicket(List<Integer> scheduleIdList);
}
