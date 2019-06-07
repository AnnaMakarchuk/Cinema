package org.study.commands.adminCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.AdministratorDto;
import org.study.commands.Command;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.StringParser;

public class UpdateAdministrator implements Command {
    private static final Logger LOG = Logger.getLogger(UpdateAdministrator.class);

    private UserFacade userFacade;
    private StringParser stringParser;

    public UpdateAdministrator() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
        this.stringParser = StringParser.getInstance();
    }

    /**
     * this method updateIsActive administrator's login and password
     */
    @Override
    public String execute(HttpServletRequest request) {
        String adminLogin = request.getParameter("login");
        String adminPassword = request.getParameter("password");
        AdministratorDto registeredUserDTO = (AdministratorDto) request.getSession().getAttribute("admin");
        if (Objects.isNull(registeredUserDTO)) {
            return "jsp/404.jsp";
        }
        int adminId = registeredUserDTO.getAdministratorId();

        if (checkParameters(adminLogin, adminPassword)) {
            AdministratorDto administratorDTO = userFacade.updateAdministrator(adminId, adminLogin, adminPassword);
            LOG.info("User with define parameters was updated");
            request.getSession().setAttribute("admin", administratorDTO);
            return "pages/admin/admin_account_update.jsp";
        } else
            return "pages/errors/401.jsp";
    }

    private boolean checkParameters(String adminLogin, String adminPassword) {
        boolean login = stringParser.checkLoginPassword(adminLogin);
        LOG.info("Login checked is " + login);
        boolean password = stringParser.checkLoginPassword(adminPassword);
        LOG.info("Password checked is " + password);
        return login && password;
    }
}
