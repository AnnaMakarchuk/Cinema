package org.study.services;

import org.study.models.Administrator;

public interface AdministratorService {

    Administrator viewAdmin(int adminId);

    Administrator getUpdateAdministrator(int adminId, String adminLogin, String adminPassword);
}