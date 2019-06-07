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
}
