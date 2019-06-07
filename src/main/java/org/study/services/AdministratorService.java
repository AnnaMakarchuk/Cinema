package org.study.services;

import org.study.models.Administrator;

public interface AdministratorService {

    Administrator viewAdmin(int adminId);

    void registerNewAdmin(Administrator administrator);

    void updateAdminInformation(Administrator administrator);

    void deleteAdmin(Administrator administrator);
}