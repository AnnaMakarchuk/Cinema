package org.study.commands.clientCommands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class DeleteClientCommand extends AbstractClientCommand {
    private static final Logger LOG = Logger.getLogger(DeleteClientCommand.class);

    private UserFacade userFacade = FacadeFactory.getInstance().getUserFacade();

    public DeleteClientCommand() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
    }

    /**
     * this method delete user
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RegisteredUserDto registeredUserDTO = (RegisteredUserDto) session.getAttribute(ParametersNames.USER);
        int userId = registeredUserDTO.getUserId();
        userFacade.deleteClient(userId);
        LOG.info("User was deleted");

        String locale = (String) session.getAttribute("locale");
        request.setAttribute(ParametersNames.LOCALE, locale);

        session.invalidate();
        LOG.info("Logout from account, session was invalidate");
        return "pages/client/client_account_delete.jsp";
    }
}
