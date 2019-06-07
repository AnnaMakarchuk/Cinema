package org.study.DAO;

import org.study.models.Hall;

public interface HallDAO {

    Hall get(int hallId);

    void create(Hall hall);

    void update(Hall hall);

    void delete(Hall hall);
}
