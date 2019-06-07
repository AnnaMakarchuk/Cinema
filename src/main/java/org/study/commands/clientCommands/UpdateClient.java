package org.study.commands.clientCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.commands.Command;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.StringParser;

public class UpdateClient implements Command {
    private static final Logger LOG = Logger.getLogger(UpdateClient.class);

    private UserFacade userFacade;
    private StringParser stringParser;

    public UpdateClient() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
        this.stringParser = StringParser.getInstance();
    }

    /**
     * this method updateIsActive user's login and password
     */
    @Override
    public String execute(HttpServletRequest request) {
        String clientLogin = request.getParameter("login");
        String clientPassword = request.getParameter("password");
        RegisteredUserDto registeredUserDTO = (RegisteredUserDto) request.getSession().getAttribute("user");
        if (Objects.isNull(registeredUserDTO)) {
            return "jsp/404.jsp";
        }
        int clientId = registeredUserDTO.getUserId();
        if (checkParameters(clientLogin, clientPassword)) {
            registeredUserDTO = userFacade.updateClient(clientId, clientLogin, clientPassword);
            LOG.info("User with define parameters was updated");
            request.getSession().setAttribute("user", registeredUserDTO);
            return "pages/client/client_account_update.jsp";
        } else
            return "pages/errors/401.jsp";
    }

    private boolean checkParameters(String clientLogin, String clientPassword) {
        boolean login = stringParser.checkLoginPassword(clientLogin);
        LOG.info("Login checked is " + login);
        boolean password = stringParser.checkLoginPassword(clientPassword);
        LOG.info("Password checked is " + password);
        return login && password;
    }
}
