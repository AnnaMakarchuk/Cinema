package org.study.factories;

import org.apache.log4j.Logger;
import org.study.commands.*;
import org.study.utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOG = Logger.getLogger(CommandFactory.class);

    private Map<String, Command> commandByURLMap = createURLMap();


    private Map<String, Command> createURLMap() {
        commandByURLMap = new HashMap<>();
        commandByURLMap.put(Pages.MAIN_PAGE, new MainPageView());
        commandByURLMap.put(Pages.LOGIN_PAGE, new LogIn());
        commandByURLMap.put(Pages.ADMIN_PAGE, new ViewAdminPage());
        commandByURLMap.put(Pages.CLIENT_PAGE, new ViewClientPage());
        return commandByURLMap;
    }


    public Command createCommand(HttpServletRequest request) {
        Command command = null;
        LOG.info(request.getRequestURI());
        String url = request.getRequestURI().substring(request.getContextPath().length());
        LOG.info("URL is " + url);
        if (commandByURLMap.containsKey(url)) {
            command = commandByURLMap.get(url);
            LOG.info("Command for this request is " + command.getClass());
        } else {
            command = new ErrorCommand();
        }
        return command;
    }
}
