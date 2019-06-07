package org.study.commands.clientCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.commands.Command;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;

public class DeleteClient implements Command {
    private static final Logger LOG = Logger.getLogger(DeleteClient.class);

    private UserFacade userFacade = FacadeFactory.getInstance().getUserFacade();

    public DeleteClient() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
    }

    /**
     * this method delete user
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        RegisteredUserDto registeredUserDTO = (RegisteredUserDto) session.getAttribute("user");
        if (Objects.isNull(registeredUserDTO)) {
            return "jsp/404.jsp";
        }
        int userId = registeredUserDTO.getUserId();
        userFacade.deleteClient(userId);
        LOG.info("User was deleted");

        String locale = (String) session.getAttribute("locale");
        request.setAttribute("locale", locale);

        session.invalidate();
        LOG.info("Logout from account, session was invalidate");
        return "pages/client/client_account_delete.jsp";
    }
}
