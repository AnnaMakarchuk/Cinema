package org.study.commands.clientCommands;

import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.facade.TicketFacade;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserTicketCommandTest {
    @InjectMocks
    private DeleteUserTicketCommand deleteUserTicketCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private TicketFacade ticketFacade;

    @Test
    public void shouldDeleteTicketByUser() {
        String expectedPath = "pages/client/delete_ticket.jsp";
        when(request.getParameter(ParametersNames.TICKET_ID)).thenReturn("1");

        String resultPath = deleteUserTicketCommand.execute(request);
        verify(ticketFacade).deleteTicketByID(1);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldFailDeleteTicketByUser() {
        String expectedPath = "pages/404.jsp";
        when(request.getParameter(ParametersNames.TICKET_ID)).thenReturn(null);
        String resultPath = deleteUserTicketCommand.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }
}
