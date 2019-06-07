package org.study.commands;

import org.apache.log4j.*;

import javax.servlet.http.*;

public class ViewClientPage implements Command{
    private static final Logger LOG = Logger.getLogger(ViewClientPage.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("ClientPage was viewed");
        return "jsp/clientpage.jsp";
    }
}
