package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.Command;

public class ViewAdminPage implements Command {
    private static final Logger LOG = Logger.getLogger(ViewAdminPage.class);

    /**
     * this command view page with access to admin cabinet
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("AdminPage was viewed");
        return "pages/admin/adminpage.jsp";
    }
}
