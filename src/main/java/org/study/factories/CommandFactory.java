package org.study.factories;

import java.util.Objects;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.commands.Error404Command;
import org.study.commands.LogoutCommand;
import org.study.commands.LoginCommand;
import org.study.commands.MainPageView;
import org.study.commands.RegistrationUser;
import org.study.commands.adminCommands.AddNewMovie;
import org.study.commands.adminCommands.CancelMovie;
import org.study.commands.adminCommands.ChangeSchedule;
import org.study.commands.adminCommands.ViewAvailableTickets;
import org.study.commands.adminCommands.ViewCancelledMovie;
import org.study.commands.adminCommands.ViewCancelledSchedule;
import org.study.commands.adminCommands.ViewPageAddNewMovie;
import org.study.commands.adminCommands.ViewPageWithMovies;
import org.study.commands.adminCommands.ViewPossibleAdminActions;
import org.study.commands.adminCommands.UpdateAdministrator;
import org.study.commands.adminCommands.ViewAdminPage;
import org.study.commands.ViewHallScheme;
import org.study.commands.ViewSchedulePage;
import org.study.commands.adminCommands.ViewAdminCabinet;
import org.study.commands.clientCommands.ActionWithTicket;
import org.study.commands.clientCommands.BuyTicketsNotification;
import org.study.commands.clientCommands.DeleteClient;
import org.study.commands.clientCommands.DeleteUserTicket;
import org.study.commands.clientCommands.UpdateClient;
import org.study.commands.clientCommands.ViewClientCabinet;
import org.study.utils.UrlEndPoints;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOG = Logger.getLogger(CommandFactory.class);
    private static final CommandFactory COMMAND_FACTORY_INSTANCE = new CommandFactory();
    private static final Map<String, Command> commandByURLMap = new HashMap<>();

    private CommandFactory() {
    }

    static {
        commandByURLMap.put(UrlEndPoints.MAIN_PAGE_URL, new MainPageView());
        commandByURLMap.put(UrlEndPoints.SCHEDULE_URL, new ViewSchedulePage());
        commandByURLMap.put(UrlEndPoints.HALL_SCHEME_URL, new ViewHallScheme());
        commandByURLMap.put(UrlEndPoints.LOGIN_URL, new LoginCommand());
        commandByURLMap.put(UrlEndPoints.REGISTRATION_URL, new RegistrationUser());
        commandByURLMap.put(UrlEndPoints.LOGOUT_URL, new LogoutCommand());

        commandByURLMap.put(UrlEndPoints.LOGIN_ADMIN_URL, new ViewAdminPage());
        commandByURLMap.put(UrlEndPoints.ADMIN_CABINET_URL, new ViewAdminCabinet());
        commandByURLMap.put(UrlEndPoints.ADMIN_ACTION_URL, new ViewPossibleAdminActions());
        commandByURLMap.put(UrlEndPoints.ADMIN_UPDATE_URL, new UpdateAdministrator());
        commandByURLMap.put(UrlEndPoints.ADMIN_ADD_MOVIE_ACTION_URL, new ViewPageAddNewMovie());
        commandByURLMap.put(UrlEndPoints.ADMIN_ADD_MOVIE_URL, new AddNewMovie());

        commandByURLMap.put(UrlEndPoints.ADMIN_All_MOVIE_URL, new ViewPageWithMovies());
        commandByURLMap.put(UrlEndPoints.ADMIN_CANCEL_MOVIE_URL, new CancelMovie());
        commandByURLMap.put(UrlEndPoints.ADMIN_VIEW_ALL_TICKETS_URL, new ViewAvailableTickets());
        commandByURLMap.put(UrlEndPoints.ADMIN_VIEW_CANCELLED_SCHEDULE_URL, new ViewCancelledSchedule());
        commandByURLMap.put(UrlEndPoints.ADMIN_CHANGE_SCHEDULE_URL, new ChangeSchedule());
        commandByURLMap.put(UrlEndPoints.ADMIN_VIEW_CANCELLED_MOVIES_URL, new ViewCancelledMovie());

        commandByURLMap.put(UrlEndPoints.USER_CABINET_URL, new ViewClientCabinet());
        commandByURLMap.put(UrlEndPoints.USER_UPDATE_URL, new UpdateClient());
        commandByURLMap.put(UrlEndPoints.USER_DELETE_URL, new DeleteClient());
        commandByURLMap.put(UrlEndPoints.USER_TICKETS_URL, new ActionWithTicket());
        commandByURLMap.put(UrlEndPoints.USER_TICKET_DELETE_URL, new DeleteUserTicket());
        commandByURLMap.put(UrlEndPoints.USER_BUY_TICKET_URL, new BuyTicketsNotification());
    }

    public static CommandFactory getCommandFactoryInstance() {
        return COMMAND_FACTORY_INSTANCE;
    }

    /**
     * this method take url from reguest and find command for this url from command map
     */
    public Command createCommand(HttpServletRequest request) {
        String url = request.getRequestURI().substring(request.getContextPath().length());
        LOG.info("URL is " + url);
        Command command = commandByURLMap.get(url);
        if (Objects.isNull(command)) {
            LOG.info("Command for url " + url + " was not found");
            return new Error404Command();
        }
        LOG.info("Command for this request is " + command.getClass());
        return command;
    }
}
