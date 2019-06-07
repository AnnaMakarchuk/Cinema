package org.study.DAO;

import org.study.models.Administrator;

public interface AdministratorDAO {

    Administrator get(int id);

    void create(Administrator administrator);

    void update(Administrator administrator);

    void delete(Administrator administrator);

}
