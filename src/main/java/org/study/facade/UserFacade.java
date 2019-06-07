package org.study.facade;

import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDTO;
import org.study.factories.ServiceFactory;
import org.study.models.RegisteredUser;
import org.study.services.UserService;

public class UserFacade {

    private static final Logger LOG = Logger.getLogger(UserFacade.class);

    private UserService userService = ServiceFactory.getInstance().getUserService();

    /**
     * this method create RegisteredUserDTO object with concrete login and password
     * In case RegisteredUser is null method return null value also
     * this method for login command
     *
     * @param login
     * @param password
     * @return RegisteredUserDTO
     */
    public RegisteredUserDTO getRegisterUser(String login, String password) {
        RegisteredUser registeredUser = userService.getUserByLoginPassword(login, password);
        if (registeredUser == null) {
            LOG.info("User Facade get null user with login " + login + ", not found in database");
            return null;
        } else {
            LOG.info("User Facade get user with login " + login + " found in database");
            return getRegisteredUserDTO(registeredUser);
        }
    }

    private RegisteredUserDTO getRegisteredUserDTO(RegisteredUser registeredUser) {
        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO();
        registeredUserDTO.setId(registeredUser.getId());
        registeredUserDTO.setUserName(registeredUser.getUserName());
        registeredUserDTO.setUserSurname(registeredUser.getUserPassword());
        registeredUserDTO.setGender(registeredUser.getGender());
        registeredUserDTO.setUserRole(registeredUser.getUserRole());
        registeredUserDTO.setUserLogin(registeredUser.getUserLogin());
        registeredUserDTO.setUserEMailAddress(registeredUser.getUserEMailAddress());
        LOG.info("RegisteredUserDTO was created");
        return registeredUserDTO;
    }
}
