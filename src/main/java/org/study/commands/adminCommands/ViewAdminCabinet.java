package org.study.commands.adminCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.AdministratorDto;
import org.study.dto.RegisteredUserDto;
import org.study.commands.Command;
import org.study.commands.clientCommands.ViewClientCabinet;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;

public class ViewAdminCabinet implements Command {
    private static final Logger LOG = Logger.getLogger(ViewClientCabinet.class);

    private UserFacade userFacade;

    public ViewAdminCabinet() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
    }

    /**
     * this method is a command for view client cabinet after making login
     */
    @Override
    public String execute(HttpServletRequest request) {
        AdministratorDto administratorDto = (AdministratorDto) request.getSession().getAttribute("admin");
        if (Objects.isNull(administratorDto)) {
            return "jsp/404.jsp";
        }
        LOG.info("Administrator with id " + administratorDto.getAdministratorId() + " logined");
        return "pages/admin/admin_cabinet.jsp";
    }
}
