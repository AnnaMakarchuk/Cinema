package org.study.facade;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.study.dto.HallDto;
import org.study.dto.PlaceDto;
import org.study.factories.ServiceFactory;
import org.study.models.Hall;
import org.study.models.Place;
import org.study.services.HallService;

public class HallFacade {
    private static final Logger LOG = Logger.getLogger(HallFacade.class);

    private HallService hallService;

    public HallFacade() {
        this.hallService = ServiceFactory.getInstance().getHallService();
    }

    /**
     * this method convert hall with price type and occupied prices for concrete session
     */
    public HallDto getHallDataWithPriceAndOccuipedPlaces(int scheduleId) {
        Hall hall = hallService.getHallWithPriceAndPlaces(scheduleId);
        LOG.info("HallFacade method getUserById hall for session id " + scheduleId);
        return createHallDTO(hall);
    }

    private HallDto createHallDTO(Hall hall) {
        HallDto hallDto = new HallDto();
        hallDto.setHallId(hall.getId());
        hallDto.setHallName(hall.getHallName());
        hallDto.setMaxRow(hall.getMaxRow());
        hallDto.setMaxPlacesInRow(hall.getMaxPlacesInRow());
        hallDto.setPriceType(hall.getPriceType());
        hallDto.setOccupiedPlaces(convertPlaces(hall.getOccupiedPlaces()));
        LOG.info("HallFacade method create HallDTO object");
        return hallDto;
    }

    private List<PlaceDto> convertPlaces(List<Place> places) {
        return places.stream()
                .map(place -> new PlaceDto(place.getRow(), place.getPlaceNumber()))
                .collect(Collectors.toList());
    }
}
