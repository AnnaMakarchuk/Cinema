package org.study.commands;

import javax.servlet.http.HttpServletRequest;

public class Error404Command implements Command {

    /**
     * this command return error 404 page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return "jsp/404.jsp";
    }

    /**
     * this method always return true for indicated command. This pages is in access for any unregistered user
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        return true;
    }
}
