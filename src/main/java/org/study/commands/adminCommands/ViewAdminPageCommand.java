package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class ViewAdminPageCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(ViewAdminPageCommand.class);

    /**
     * this command view page with access to admin cabinet
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("AdminPage was viewed");
        return "pages/admin/adminpage.jsp";
    }
}
