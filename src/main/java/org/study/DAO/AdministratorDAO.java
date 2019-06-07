package org.study.dao;

import org.study.models.Administrator;

public interface AdministratorDao {

    Administrator get(int administratorId);

    void update(int administratorId, String adminLogin, String adminPassword);
}
