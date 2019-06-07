package org.study.commands.clientCommands;

import javax.servlet.http.HttpServletRequest;
import org.study.commands.Command;

public class ViewClientCabinet implements Command {
    /**
     * this method is a command for view client cabinet after making login
     */
    @Override
    public String execute(HttpServletRequest request) {
        return "pages/client/client_cabinet.jsp";
    }
}
