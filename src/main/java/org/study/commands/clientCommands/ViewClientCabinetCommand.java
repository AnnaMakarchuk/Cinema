package org.study.commands.clientCommands;

import javax.servlet.http.HttpServletRequest;

public class ViewClientCabinetCommand extends AbstractClientCommand {

    /**
     * this method is a command for view client cabinet after making login
     */
    @Override
    public String execute(HttpServletRequest request) {
        return "pages/client/client_cabinet.jsp";
    }
}
