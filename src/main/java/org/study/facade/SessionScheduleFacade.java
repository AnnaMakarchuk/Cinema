package org.study.facade;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.study.dto.SessionScheduleDto;
import org.study.dto.TimeDto;
import org.study.factories.ServiceFactory;
import org.study.models.SessionSchedule;
import org.study.models.enums.WeekDay;
import org.study.services.SessionScheduleService;

public class SessionScheduleFacade {
    private static final Logger LOG = Logger.getLogger(SessionScheduleFacade.class);

    private SessionScheduleService sessionScheduleService;

    public SessionScheduleFacade() {
        this.sessionScheduleService = ServiceFactory.getInstance().getSessionScheduleService();
    }

    /**
     * this  method create session schedule dto for concrete day of the week
     */
    public List<SessionScheduleDto> getAllScheduleByDay(String weekday) {
        List<SessionSchedule> sessionScheduleList =
                sessionScheduleService.viewAllScheduleByWeekDay(weekday.toUpperCase());
        LOG.info("SessionScheduleFacade method getUserById all sorted schedule for " + weekday);
        return createSessionScheduleDTOListWIthTimeList(sessionScheduleList);
    }

    /**
     * this method convert List of cancelled SessionSchedule in SessionSchedule dto
     */
    public List<SessionScheduleDto> cancelMovie(int movieId) {
        List<SessionSchedule> cancelledSessionSchedule = sessionScheduleService.updateScheduleCancelMovie(movieId);
        return createSessionScheduleDTOList(cancelledSessionSchedule);
    }

    /**
     * this method convert list of non active session schedule to dto list
     */
    public List<SessionScheduleDto> viewNonActiveSchedule() {
        List<SessionSchedule> nonActiveSessionScheduleList = sessionScheduleService.createNonActiveSchedule(false);
        return createSessionScheduleDTOList(nonActiveSessionScheduleList);
    }

    /**
     * this method add film is session and make it active
     */
    public boolean changeSchedule(int scheduleId, int movieId, boolean isActive) {
        return sessionScheduleService.updateSession(scheduleId, movieId, isActive);
    }

    /**
     * getUserById schedule by schedule id
     */
    public SessionScheduleDto getScheduleById(int scheduleId) {
        SessionSchedule sessionSchedule = sessionScheduleService.getByScheduleId(scheduleId);
        return createSessionScheduleDTO(sessionSchedule);
    }

    private List<SessionScheduleDto> createSessionScheduleDTOListWIthTimeList(List<SessionSchedule> sessionScheduleList) {
        List<SessionScheduleDto> sessionScheduleDtos = new ArrayList<>();

        for (int i = 0; i < sessionScheduleList.size(); i++) {
            int id = sessionScheduleList.get(i).getId();
            WeekDay weekday = sessionScheduleList.get(i).getWeekDay();
            String movieName = sessionScheduleList.get(i).getMovie().getMovieName();
            LocalTime time = sessionScheduleList.get(i).getTime();
            if (i == 0) {
                sessionScheduleDtos.add(addElementToSessionScheduleDTOList(id, weekday, movieName, time));
            } else {
                boolean isNew = true;
                for (SessionScheduleDto sessionScheduleDto : sessionScheduleDtos) {
                    if (sessionScheduleDto.getMovieName().equals(movieName)) {
                        sessionScheduleDto.getTimeList().add(new TimeDto(id, time));
                        isNew = false;
                    }
                }
                if (isNew) {
                    sessionScheduleDtos.add(addElementToSessionScheduleDTOList(id, weekday, movieName, time));
                }
            }
        }
        return sessionScheduleDtos;
    }

    private SessionScheduleDto addElementToSessionScheduleDTOList(int id, WeekDay weekday, String movieName, LocalTime time) {
        SessionScheduleDto sessionScheduleDto = new SessionScheduleDto();
        sessionScheduleDto.setScheduleId(id);
        sessionScheduleDto.setWeekDay(weekday);
        sessionScheduleDto.setMovieName(movieName);
        List<TimeDto> timeList = new ArrayList<>();
        timeList.add(new TimeDto(id, time));
        sessionScheduleDto.setTimeList(timeList);
        return sessionScheduleDto;
    }

    private List<SessionScheduleDto> createSessionScheduleDTOList(List<SessionSchedule> sessionScheduleList) {
        return sessionScheduleList.stream()
                .map(sessionSchedule -> {
                    SessionScheduleDto sessionScheduleDto = new SessionScheduleDto();
                    sessionScheduleDto.setScheduleId(sessionSchedule.getId());
                    sessionScheduleDto.setWeekDay(sessionSchedule.getWeekDay());
                    sessionScheduleDto.setTime(sessionSchedule.getTime());
                    sessionScheduleDto.setMovieName(sessionSchedule.getMovie().getMovieName());
                    return sessionScheduleDto;
                })
                .collect(Collectors.toList());
    }

    private SessionScheduleDto createSessionScheduleDTO(SessionSchedule sessionSchedule) {
        SessionScheduleDto sessionScheduleDto = new SessionScheduleDto();
        sessionScheduleDto.setScheduleId(sessionSchedule.getId());
        sessionScheduleDto.setWeekDay(sessionSchedule.getWeekDay());
        sessionScheduleDto.setTime(sessionSchedule.getTime());
        sessionScheduleDto.setMovieName(sessionSchedule.getMovie().getMovieName());
        return sessionScheduleDto;
    }

}
