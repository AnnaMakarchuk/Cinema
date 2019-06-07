package org.study.commands;

import java.util.Objects;
import org.apache.log4j.Logger;
import org.study.dto.AdministratorDto;
import org.study.dto.RegisteredUserDto;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.models.Administrator;
import org.study.models.enums.UserRole;
import org.study.servlets.FrontControllerServlet;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private static final Logger LOG = Logger.getLogger(FrontControllerServlet.class);

    private UserFacade userFacade;

    public LoginCommand() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
    }

    /**
     * this method getUserById parameters login and password from request,
     * find user with this parameters and create path in case of user role
     */
    @Override
    public String execute(HttpServletRequest request) {
        String path = " ";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        RegisteredUserDto registeredUserDTO = userFacade.getRegisterUser(login, password);
        if (Objects.isNull(registeredUserDTO)) {
            LOG.info("Login or password incorrect, send to error page");
            path = "pages/401.jsp";
        } else if (registeredUserDTO.getUserRole() == UserRole.CLIENT) {
            LOG.info("User is a client");
            request.getSession().setAttribute("user", registeredUserDTO);
            path = "pages/client/client_cabinet.jsp";
        } else if (registeredUserDTO.getUserRole() == UserRole.ADMINISTRATOR) {
            AdministratorDto administratorDto = userFacade.getAdministratorByID(registeredUserDTO.getUserId());
            LOG.info("User is an admin");
            request.getSession().setAttribute("admin", administratorDto);
            path = "pages/admin/adminpage.jsp";
        }
        return path;
    }
}
