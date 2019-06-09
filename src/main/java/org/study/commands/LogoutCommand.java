package org.study.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.study.utils.ParametersNames;

public class LogoutCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    /**
     * this method logout and close session
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String locale = (String) session.getAttribute(ParametersNames.LOCALE);
        request.setAttribute(ParametersNames.LOCALE, locale);

        session.invalidate();
        LOG.info("Logout from account, session was invalidated");
        return "pages/logoutpage.jsp";
    }

    /**
     * this method always return true for indicated command. This pages is in access for any unregistered user
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        return true;
    }
}
