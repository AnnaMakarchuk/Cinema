package org.study.DAO;

import org.study.models.enums.UserRole;

public interface UserRoleDAO {

    UserRole get(String roleName);

    void create(UserRole userRole);

    void update(int id, UserRole userRole);

    void delete(UserRole userRole);
}

