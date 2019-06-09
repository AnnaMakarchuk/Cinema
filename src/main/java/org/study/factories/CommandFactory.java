package org.study.factories;

import java.util.Objects;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.commands.Error404Command;
import org.study.commands.LogoutCommand;
import org.study.commands.LoginCommand;
import org.study.commands.MainPageViewCommand;
import org.study.commands.RegistrationUser;
import org.study.commands.adminCommands.AddNewMovieCommand;
import org.study.commands.adminCommands.CancelMovieCommand;
import org.study.commands.adminCommands.ChangeScheduleCommand;
import org.study.commands.adminCommands.ViewAvailableTicketsCommand;
import org.study.commands.adminCommands.ViewCancelledMovieCommand;
import org.study.commands.adminCommands.ViewCancelledScheduleCommand;
import org.study.commands.adminCommands.ViewPageAddNewMovieCommand;
import org.study.commands.adminCommands.ViewPageWithMovies;
import org.study.commands.adminCommands.ViewPossibleAdminActions;
import org.study.commands.adminCommands.UpdateAdministratorCommand;
import org.study.commands.adminCommands.ViewAdminPageCommand;
import org.study.commands.ViewHallSchemeCommand;
import org.study.commands.ViewSchedulePageCommand;
import org.study.commands.adminCommands.ViewAdminCabinetCommand;
import org.study.commands.clientCommands.ActionWithTicketCommand;
import org.study.commands.clientCommands.BuyTicketsNotification;
import org.study.commands.clientCommands.DeleteClientCommand;
import org.study.commands.clientCommands.DeleteUserTicketCommand;
import org.study.commands.clientCommands.UpdateClientCommand;
import org.study.commands.clientCommands.ViewClientCabinetCommand;
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
        commandByURLMap.put(UrlEndPoints.MAIN_PAGE_URL, new MainPageViewCommand());
        commandByURLMap.put(UrlEndPoints.SCHEDULE_URL, new ViewSchedulePageCommand());
        commandByURLMap.put(UrlEndPoints.HALL_SCHEME_URL, new ViewHallSchemeCommand());
        commandByURLMap.put(UrlEndPoints.LOGIN_URL, new LoginCommand());
        commandByURLMap.put(UrlEndPoints.REGISTRATION_URL, new RegistrationUser());
        commandByURLMap.put(UrlEndPoints.LOGOUT_URL, new LogoutCommand());

        commandByURLMap.put(UrlEndPoints.LOGIN_ADMIN_URL, new ViewAdminPageCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_CABINET_URL, new ViewAdminCabinetCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_ACTION_URL, new ViewPossibleAdminActions());
        commandByURLMap.put(UrlEndPoints.ADMIN_UPDATE_URL, new UpdateAdministratorCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_ADD_MOVIE_ACTION_URL, new ViewPageAddNewMovieCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_ADD_MOVIE_URL, new AddNewMovieCommand());

        commandByURLMap.put(UrlEndPoints.ADMIN_All_MOVIE_URL, new ViewPageWithMovies());
        commandByURLMap.put(UrlEndPoints.ADMIN_CANCEL_MOVIE_URL, new CancelMovieCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_VIEW_ALL_TICKETS_URL, new ViewAvailableTicketsCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_VIEW_CANCELLED_SCHEDULE_URL, new ViewCancelledScheduleCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_CHANGE_SCHEDULE_URL, new ChangeScheduleCommand());
        commandByURLMap.put(UrlEndPoints.ADMIN_VIEW_CANCELLED_MOVIES_URL, new ViewCancelledMovieCommand());

        commandByURLMap.put(UrlEndPoints.USER_CABINET_URL, new ViewClientCabinetCommand());
        commandByURLMap.put(UrlEndPoints.USER_UPDATE_URL, new UpdateClientCommand());
        commandByURLMap.put(UrlEndPoints.USER_DELETE_URL, new DeleteClientCommand());
        commandByURLMap.put(UrlEndPoints.USER_TICKETS_URL, new ActionWithTicketCommand());
        commandByURLMap.put(UrlEndPoints.USER_TICKET_DELETE_URL, new DeleteUserTicketCommand());
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
