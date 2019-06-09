package org.study.factories;

import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.commands.Command;
import org.study.commands.LogoutCommand;
import org.study.commands.LoginCommand;
import org.study.commands.MainPageViewCommand;
import org.study.commands.RegistrationUser;
import org.study.commands.ViewHallSchemeCommand;
import org.study.commands.ViewSchedulePageCommand;
import org.study.commands.adminCommands.AddNewMovieCommand;
import org.study.commands.adminCommands.CancelMovieCommand;
import org.study.commands.adminCommands.ChangeScheduleCommand;
import org.study.commands.adminCommands.UpdateAdministratorCommand;
import org.study.commands.adminCommands.ViewAdminCabinetCommand;
import org.study.commands.adminCommands.ViewAdminPageCommand;
import org.study.commands.adminCommands.ViewAvailableTicketsCommand;
import org.study.commands.adminCommands.ViewCancelledMovieCommand;
import org.study.commands.adminCommands.ViewCancelledScheduleCommand;
import org.study.commands.adminCommands.ViewPageAddNewMovieCommand;
import org.study.commands.adminCommands.ViewPageWithMovies;
import org.study.commands.adminCommands.ViewPossibleAdminActions;
import org.study.commands.clientCommands.ActionWithTicketCommand;
import org.study.commands.clientCommands.BuyTicketsNotification;
import org.study.commands.clientCommands.DeleteClientCommand;
import org.study.commands.clientCommands.DeleteUserTicketCommand;
import org.study.commands.clientCommands.UpdateClientCommand;
import org.study.commands.clientCommands.ViewClientCabinetCommand;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryTest {

    private CommandFactory commandFactory = CommandFactory.getCommandFactoryInstance();

    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() {
        when(request.getContextPath()).thenReturn("/cinema");
    }




    @Test
    public void shouldCallMainPageView() {
        when(request.getRequestURI()).thenReturn("/cinema/");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(MainPageViewCommand.class));
    }

    @Test
    public void shouldCallViewSchedulePage() {
        when(request.getRequestURI()).thenReturn("/cinema/schedule");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewSchedulePageCommand.class));
    }

    @Test
    public void shouldCallViewHallScheme() {
        when(request.getRequestURI()).thenReturn("/cinema/hallscheme");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewHallSchemeCommand.class));
    }

    @Test
    public void shouldCallLogin() {
        when(request.getRequestURI()).thenReturn("/cinema/login");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(LoginCommand.class));
    }

    @Test
    public void shouldCallLogOut() {
        when(request.getRequestURI()).thenReturn("/cinema/logout");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(LogoutCommand.class));
    }

    @Test
    public void shouldCallRegistrationUser() {
        when(request.getRequestURI()).thenReturn("/cinema/register");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(RegistrationUser.class));
    }

    @Test
    public void shouldCallViewAdminPage() {
        when(request.getRequestURI()).thenReturn("/cinema/login/admin");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewAdminPageCommand.class));
    }

    @Test
    public void shouldCallViewAdminCabinet() {
        when(request.getRequestURI()).thenReturn("/cinema/admincabinet");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewAdminCabinetCommand.class));
    }

    @Test
    public void shouldCallViewPossibleAdminActions() {
        when(request.getRequestURI()).thenReturn("/cinema/adminaction");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewPossibleAdminActions.class));
    }

    @Test
    public void shouldCallUpdateAdministrator() {
        when(request.getRequestURI()).thenReturn("/cinema/adminupdate");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(UpdateAdministratorCommand.class));
    }

    @Test
    public void shouldCallViewPageAddNewMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/adminmovielist");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewPageAddNewMovieCommand.class));
    }

    @Test
    public void shouldCallAddNewMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/adminaddmovie");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(AddNewMovieCommand.class));
    }

    @Test
    public void shouldCallViewPageCancelMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/admincancelmovie");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(CancelMovieCommand.class));
    }

    @Test
    public void shouldCallViewPageWithMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/adminmovielistcancel");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewPageWithMovies.class));
    }

    @Test
    public void shouldCallChangeScheduleCommand() {
        when(request.getRequestURI()).thenReturn("/cinema/changeschedule");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ChangeScheduleCommand.class));
    }

    @Test
    public void shouldCallViewAvailableTickets() {
        when(request.getRequestURI()).thenReturn("/cinema/adminviewtickets");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewAvailableTicketsCommand.class));
    }

    @Test
    public void shouldCallViewCancelledSchedule() {
        when(request.getRequestURI()).thenReturn("/cinema/adminnonactiveschedule");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewCancelledScheduleCommand.class));
    }

    @Test
    public void shouldCallViewCancelledMovies() {
        when(request.getRequestURI()).thenReturn("/cinema/adminnonactivemovies");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewCancelledMovieCommand.class));
    }


    @Test
    public void shouldCallViewClientCabinet() {
        when(request.getRequestURI()).thenReturn("/cinema/cabinet");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewClientCabinetCommand.class));
    }

    @Test
    public void shouldCallUpdateClient() {
        when(request.getRequestURI()).thenReturn("/cinema/update");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(UpdateClientCommand.class));
    }

    @Test
    public void shouldCallDeleteClient() {
        when(request.getRequestURI()).thenReturn("/cinema/deleteaccount");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(DeleteClientCommand.class));
    }

    @Test
    public void shouldCallActionWithTicket() {
        when(request.getRequestURI()).thenReturn("/cinema/tickets");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ActionWithTicketCommand.class));
    }

    @Test
    public void shouldCallDeleteUserTicket() {
        when(request.getRequestURI()).thenReturn("/cinema/deleteticket");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(DeleteUserTicketCommand.class));
    }

    @Test
    public void shouldCallBuyUserTicket() {
        when(request.getRequestURI()).thenReturn("/cinema/boughttickets");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(BuyTicketsNotification.class));
    }
}
