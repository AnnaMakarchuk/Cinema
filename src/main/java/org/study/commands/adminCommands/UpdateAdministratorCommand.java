package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.AdministratorDto;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;
import org.study.utils.StringParser;

public class UpdateAdministratorCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(UpdateAdministratorCommand.class);

    private UserFacade userFacade;
    private StringParser stringParser;

    public UpdateAdministratorCommand() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
        this.stringParser = StringParser.getInstance();
    }

    /**
     * this method updateIsActive administrator's login and password
     */
    @Override
    public String execute(HttpServletRequest request) {
        String adminLogin = request.getParameter(ParametersNames.LOGIN);
        String adminPassword = request.getParameter(ParametersNames.PASSWORD);
        AdministratorDto registeredUserDTO = (AdministratorDto) request.getSession().getAttribute(ParametersNames.ADMIN);
        int adminId = registeredUserDTO.getAdministratorId();
        if (checkParameters(adminLogin, adminPassword)) {
            AdministratorDto administratorDto = userFacade.updateAdministrator(adminId, adminLogin, adminPassword);
            LOG.info("User with define parameters was updated");
            request.getSession().setAttribute("admin", administratorDto);
            return "pages/admin/admin_account_update.jsp";
        } else
            return "pages/401.jsp";
    }

    private boolean checkParameters(String adminLogin, String adminPassword) {
        boolean login = stringParser.checkLoginPassword(adminLogin);
        LOG.info("Login checked is " + login);
        boolean password = stringParser.checkLoginPassword(adminPassword);
        LOG.info("Password checked is " + password);
        return login && password;
    }
}
