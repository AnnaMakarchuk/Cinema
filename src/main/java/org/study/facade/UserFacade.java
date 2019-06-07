package org.study.facade;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.study.dto.AdministratorDto;
import org.study.dto.RegisteredUserDto;
import org.study.dto.SessionScheduleDto;
import org.study.factories.ServiceFactory;
import org.study.models.Administrator;
import org.study.models.RegisteredUser;
import org.study.models.enums.UserRole;
import org.study.services.AdministratorService;
import org.study.services.UserService;

public class UserFacade {
    private static final Logger LOG = Logger.getLogger(UserFacade.class);

    private UserService userService;
    private AdministratorService administratorService;

    public UserFacade() {
        this.userService = ServiceFactory.getInstance().getUserService();
        this.administratorService = ServiceFactory.getInstance().getAdministratorService();
    }

    /**
     * this method create RegisteredUserDto object with concrete login and password
     * In case RegisteredUser is null method return null value also
     * this method for login command
     */
    public RegisteredUserDto getRegisterUser(String userLogin, String userPassword) {
        String codePassword = DigestUtils.md5Hex(userPassword);
        RegisteredUser registeredUser = userService.getUserByLoginPassword(userLogin, codePassword);
        if (Objects.isNull(registeredUser)) {
            LOG.info("User Facade getUserById null user with login " + userLogin + ", not found in database");
            return null;
        } else {
            LOG.info("User Facade getUserById user with login " + userLogin + " found in database");
            return getRegisteredUserDto(registeredUser);
        }
    }

    /**
     * this method getUserById RegisteredUserDTO object by login user id
     */
    public RegisteredUserDto getRegisterUserByID(int userId) {
        RegisteredUser registeredUser = userService.viewUser(userId);
        return getRegisteredUserDto(registeredUser);
    }

    /**
     * this method create new User
     */
    public void createNewUser(String userName, String userSurname, String userGender,
                              String userLogin, String userEMailAddress, String userPassword) {
        String userRole = UserRole.CLIENT.name().toLowerCase();
        userService.registerNewUser(userName, userSurname, userRole, userGender, userLogin,
                userEMailAddress, userPassword);
    }

    /**
     * this method update client password and login
     */
    public RegisteredUserDto updateClient( int clientId, String clientLogin, String clientPassword) {
        String codePassword = DigestUtils.md5Hex(clientPassword);
        RegisteredUser registeredUser = userService.updateUserInformation(clientId, clientLogin, codePassword);
        return getRegisteredUserDto(registeredUser);
    }

    /**
     * this method delete client from cinema system
     */
    public void deleteClient(int userId) {
        userService.deleteUser(userId);
    }

    /**
     * this method receive list of cancelled session schedule, find id of this schedule and
     * get all users bought tickets on this schedule
     */
    public List<RegisteredUserDto> createUserListWithCancelledSchedule(List<Integer> scheduleIdList) {
        List<RegisteredUser> registeredUser = userService.createListUsersFromTicket(scheduleIdList);
        return getRegisteredUserDTOList(registeredUser);
    }

    /**
     * this method create AdministratorDTO object by login user id i case role=ADMINISTRATOR
     */
    public AdministratorDto getAdministratorByID(int administratorId) {
        Administrator administrator = administratorService.viewAdmin(administratorId);
        return getAdministratorDto(administrator);
    }

    /**
     * this method updateIsActive administrator password and login
     */
    public AdministratorDto updateAdministrator(int adminId, String adminLogin, String adminPassword) {
        String codePassword = DigestUtils.md5Hex(adminPassword);
        Administrator administrator = administratorService.getUpdateAdministrator(adminId, adminLogin, codePassword);
        return getAdministratorDto(administrator);
    }

    private RegisteredUserDto getRegisteredUserDto(RegisteredUser registeredUser) {
        RegisteredUserDto RegisteredUserDto = new RegisteredUserDto();
        RegisteredUserDto.setUserId(registeredUser.getId());
        RegisteredUserDto.setUserName(registeredUser.getUserName());
        RegisteredUserDto.setUserSurname(registeredUser.getUserSurname());
        RegisteredUserDto.setGender(registeredUser.getGender());
        RegisteredUserDto.setUserRole(registeredUser.getUserRole());
        RegisteredUserDto.setUserLogin(registeredUser.getUserLogin());
        RegisteredUserDto.setUserEMailAddress(registeredUser.getUserEMailAddress());
        LOG.info("RegisteredUserDTO was created");
        return RegisteredUserDto;
    }

    private AdministratorDto getAdministratorDto(Administrator administrator) {
        AdministratorDto AdministratorDto = new AdministratorDto();
        AdministratorDto.setAdministratorId(administrator.getId());
        AdministratorDto.setAdministratorName(administrator.getUserName());
        AdministratorDto.setAdministratorSurname(administrator.getUserSurname());
        AdministratorDto.setGender(administrator.getGender());
        AdministratorDto.setUserRole(administrator.getUserRole());
        AdministratorDto.setAdministratorLogin(administrator.getUserLogin());
        AdministratorDto.setAdministratorEMailAddress(administrator.getUserEMailAddress());
        AdministratorDto.setAdministratorWorkingHoursPerWeek(administrator.getWorkingHoursWeek());
        LOG.info("AdministratorDto was created");
        return AdministratorDto;
    }

    private List<RegisteredUserDto> getRegisteredUserDTOList(List<RegisteredUser> registeredUser) {
        return registeredUser.stream()
                .map(user -> {
                    RegisteredUserDto RegisteredUserDto = new RegisteredUserDto();
                    RegisteredUserDto.setUserId(user.getId());
                    RegisteredUserDto.setUserName(user.getUserName());
                    RegisteredUserDto.setUserSurname(user.getUserSurname());
                    RegisteredUserDto.setGender(user.getGender());
                    RegisteredUserDto.setUserRole(user.getUserRole());
                    RegisteredUserDto.setUserLogin(user.getUserLogin());
                    RegisteredUserDto.setUserEMailAddress(user.getUserEMailAddress());
                    return RegisteredUserDto;
                })
                .collect(Collectors.toList());
    }
}
