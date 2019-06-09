package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.clientCommands.ViewClientCabinetCommand;

public class ViewAdminCabinetCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(ViewClientCabinetCommand.class);

    /**
     * this method is a command for view client cabinet after making login
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Administrator logged in");
        return "pages/admin/admin_cabinet.jsp";
    }
}
