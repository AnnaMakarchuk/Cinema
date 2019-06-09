package org.study.commands.clientCommands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;
import org.study.utils.StringParser;

public class UpdateClientCommand extends AbstractClientCommand {
    private static final Logger LOG = Logger.getLogger(UpdateClientCommand.class);

    private UserFacade userFacade;
    private StringParser stringParser;

    public UpdateClientCommand() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
        this.stringParser = StringParser.getInstance();
    }

    /**
     * this method updateIsActive user's login and password
     */
    @Override
    public String execute(HttpServletRequest request) {
        String clientLogin = request.getParameter(ParametersNames.LOGIN);
        String clientPassword = request.getParameter(ParametersNames.PASSWORD);
        RegisteredUserDto registeredUserDTO = (RegisteredUserDto) request.getSession().getAttribute(ParametersNames.USER);
        int clientId = registeredUserDTO.getUserId();
        if (checkParameters(clientLogin, clientPassword)) {
            registeredUserDTO = userFacade.updateClient(clientId, clientLogin, clientPassword);
            LOG.info("User with define parameters was updated");
            request.getSession().setAttribute(ParametersNames.USER, registeredUserDTO);
            return "pages/client/client_account_update.jsp";
        } else
            return "pages/401.jsp";
    }

    private boolean checkParameters(String clientLogin, String clientPassword) {
        boolean login = stringParser.checkLoginPassword(clientLogin);
        LOG.info("Login checked is " + login);
        boolean password = stringParser.checkLoginPassword(clientPassword);
        LOG.info("Password checked is " + password);
        return login && password;
    }
}
