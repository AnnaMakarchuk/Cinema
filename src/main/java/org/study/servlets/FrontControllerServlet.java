package org.study.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.factories.CommandFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(FrontControllerServlet.class);
    private static final CommandFactory COMMAND_FACTORY = CommandFactory.getCommandFactoryInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = COMMAND_FACTORY.createCommand(request);
        String path = command.execute(request);
        LOG.info("Get method. Path is " + path);
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = COMMAND_FACTORY.createCommand(request);
        String path = command.execute(request);
        LOG.info("Post method. Path is " + path);
        request.getRequestDispatcher(path).forward(request, response);
    }
}