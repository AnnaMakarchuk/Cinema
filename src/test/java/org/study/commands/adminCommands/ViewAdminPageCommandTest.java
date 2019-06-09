package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ViewAdminPageCommandTest {
    @InjectMocks
    private ViewAdminPageCommand adminPageCommand;

    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnMainPageWithMovies() {
        String expectedPath = "pages/admin/adminpage.jsp";
        String resultPath = adminPageCommand.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }
}
