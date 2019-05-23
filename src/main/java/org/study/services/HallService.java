package org.study.services;

import org.study.models.Hall;

public interface HallService {

    Hall viewHallFromDataBase(int hallId);

    void addNewHall(Hall hall);

    void setNewHallFeature(Hall hall);

}
