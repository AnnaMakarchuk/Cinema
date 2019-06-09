package org.study.commands.clientCommands;

import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ViewClientCabinetCommandTest {
    @InjectMocks
    private ViewClientCabinetCommand viewClientCabinetCommand;

    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnMainPageWithMovies() {
        String expectedPath = "pages/client/client_cabinet.jsp";
        String resultPath = viewClientCabinetCommand.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }
}
