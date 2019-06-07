package org.study.commands;

import org.apache.log4j.Logger;
import org.study.DTO.RegisteredUserDTO;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.models.enums.UserRole;
import org.study.servlets.FrontControllerServlet;

import javax.servlet.http.HttpServletRequest;

public class LogIn implements Command {

    private static final Logger LOG = Logger.getLogger(FrontControllerServlet.class);

    private UserFacade userFacade = FacadeFactory.getInstance().getUserFacade();

    /**
     * this method get parameters login and password from request,
     * find user with this parameters and create path in case of user role
     *
     * @param request
     * @return path
     */
    @Override
    public String execute(HttpServletRequest request) {
        String path = " ";

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        LOG.info("Input parameters from user is login " + login + " and password " + password);

        RegisteredUserDTO registeredUserDTO = userFacade.getRegisterUser(login, password);
        LOG.info("User with define parameters is exist");
        request.getSession().setAttribute("user", registeredUserDTO);

        if (registeredUserDTO != null) {
            path = "jsp/login.jsp";
        } else {
            LOG.info("Login or password incorrect, send to error page");
            path = "jsp/401.jsp";
        }
//        if (registeredUserDTO != null && registeredUserDTO.getUserRole() == UserRole.ADMINISTRATOR) {
//            path = "jsp/adminpage.jsp";
//        } else if (registeredUserDTO != null && registeredUserDTO.getUserRole() == UserRole.CLIENT) {
//            path = "jsp/clientpage.jsp";
//        } else {
//            LOG.info("Login or password incorrect, send to error page");
//            path = "jsp/401.jsp";
//        }
        return path;
    }
}
