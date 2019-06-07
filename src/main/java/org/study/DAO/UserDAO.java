package org.study.DAO;

import org.study.models.RegisteredUser;

import java.util.List;

public interface UserDAO {

    RegisteredUser get(int userId);

    void create(RegisteredUser registeredUser);

    void delete(RegisteredUser registeredUser);

    void update(RegisteredUser registeredUser);

    List<RegisteredUser> getAll();

}
