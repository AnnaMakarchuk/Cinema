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
import org.study.commands.MainPageView;
import org.study.commands.RegistrationUser;
import org.study.commands.ViewHallScheme;
import org.study.commands.ViewSchedulePage;
import org.study.commands.adminCommands.AddNewMovie;
import org.study.commands.adminCommands.CancelMovie;
import org.study.commands.adminCommands.ChangeSchedule;
import org.study.commands.adminCommands.UpdateAdministrator;
import org.study.commands.adminCommands.ViewAdminCabinet;
import org.study.commands.adminCommands.ViewAdminPage;
import org.study.commands.adminCommands.ViewAvailableTickets;
import org.study.commands.adminCommands.ViewCancelledMovie;
import org.study.commands.adminCommands.ViewCancelledSchedule;
import org.study.commands.adminCommands.ViewPageAddNewMovie;
import org.study.commands.adminCommands.ViewPageWithMovies;
import org.study.commands.adminCommands.ViewPossibleAdminActions;
import org.study.commands.clientCommands.ActionWithTicket;
import org.study.commands.clientCommands.BuyTicketsNotification;
import org.study.commands.clientCommands.DeleteClient;
import org.study.commands.clientCommands.DeleteUserTicket;
import org.study.commands.clientCommands.UpdateClient;
import org.study.commands.clientCommands.ViewClientCabinet;

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
        assertThat(command, instanceOf(MainPageView.class));
    }

    @Test
    public void shouldCallViewSchedulePage() {
        when(request.getRequestURI()).thenReturn("/cinema/schedule");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewSchedulePage.class));
    }

    @Test
    public void shouldCallViewHallScheme() {
        when(request.getRequestURI()).thenReturn("/cinema/hallscheme");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewHallScheme.class));
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
        assertThat(command, instanceOf(ViewAdminPage.class));
    }

    @Test
    public void shouldCallViewAdminCabinet() {
        when(request.getRequestURI()).thenReturn("/cinema/admincabinet");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewAdminCabinet.class));
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
        assertThat(command, instanceOf(UpdateAdministrator.class));
    }

    @Test
    public void shouldCallViewPageAddNewMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/adminaddmovie");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewPageAddNewMovie.class));
    }

    @Test
    public void shouldCallAddNewMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/addmovie");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(AddNewMovie.class));
    }

    @Test
    public void shouldCallViewPageCancelMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/admincancelmovie");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewPageWithMovies.class));
    }

    @Test
    public void shouldCallCancelMovie() {
        when(request.getRequestURI()).thenReturn("/cinema/cancelMovie");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(CancelMovie.class));
    }

    @Test
    public void shouldCallViewAvailableTickets() {
        when(request.getRequestURI()).thenReturn("/cinema/adminviewtickets");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewAvailableTickets.class));
    }

    @Test
    public void shouldCallViewCancelledSchedule() {
        when(request.getRequestURI()).thenReturn("/cinema/adminnonactiveschedule");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewCancelledSchedule.class));
    }

    @Test
    public void shouldCallChangeSchedule() {
        when(request.getRequestURI()).thenReturn("/cinema/changeSchedule");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ChangeSchedule.class));
    }

    @Test
    public void shouldCallViewCancelledMovies() {
        when(request.getRequestURI()).thenReturn("/cinema/adminnonactivemovies");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewCancelledMovie.class));
    }


    @Test
    public void shouldCallViewClientCabinet() {
        when(request.getRequestURI()).thenReturn("/cinema/cabinet");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ViewClientCabinet.class));
    }

    @Test
    public void shouldCallUpdateClient() {
        when(request.getRequestURI()).thenReturn("/cinema/updateIsActive");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(UpdateClient.class));
    }

    @Test
    public void shouldCallDeleteClient() {
        when(request.getRequestURI()).thenReturn("/cinema/deleteaccount");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(DeleteClient.class));
    }

    @Test
    public void shouldCallActionWithTicket() {
        when(request.getRequestURI()).thenReturn("/cinema/tickets");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(ActionWithTicket.class));
    }

    @Test
    public void shouldCallDeleteUserTicket() {
        when(request.getRequestURI()).thenReturn("/cinema/deleteTicket");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(DeleteUserTicket.class));
    }

    @Test
    public void shouldCallBuyUserTicket() {
        when(request.getRequestURI()).thenReturn("/cinema/boughttickets");
        Command command = commandFactory.createCommand(request);
        assertThat(command, instanceOf(BuyTicketsNotification.class));
    }
}
