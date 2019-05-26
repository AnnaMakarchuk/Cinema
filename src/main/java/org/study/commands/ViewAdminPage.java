package org.study.commands;

import org.apache.log4j.*;

import javax.servlet.http.*;

public class ViewAdminPage implements Command {
    private static final Logger LOG = Logger.getLogger(ViewAdminPage.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("AdminPage was viewed");
        return "jsp/adminpage.jsp";
    }
}
