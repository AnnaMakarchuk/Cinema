package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class ViewPossibleAdminActions extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(ViewPossibleAdminActions.class);

    /**
     * this method is a command for view admin actions page
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("AdminActionPage was viewed");
        return "pages/admin/admin_actions.jsp";
    }
}
