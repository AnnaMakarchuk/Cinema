package org.study.services;

import org.study.models.Hall;

public interface HallService {

    Hall getHallWithPriceAndPlaces(int scheduleId);
}
