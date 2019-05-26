package org.study.servlets;

import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.factories.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(FrontControllerServlet.class);
//    private static final Command command = CommandFactory.getInstance().createCommand();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = new CommandFactory().createCommand(request);
        String path = command.execute(request);
        LOG.info("Get method. Path is " + path);
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = new CommandFactory().createCommand(request);
        String path = command.execute(request);
        LOG.info("Post method. Path is " + path);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
