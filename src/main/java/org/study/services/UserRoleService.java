package org.study.services;

import org.study.models.enums.UserRole;

public interface UserRoleService {

    UserRole showUserRole(String userRoleName);

    void addNewUserRole(UserRole userRole);
}
