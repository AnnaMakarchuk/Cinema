package org.study.commands;

import javax.servlet.http.HttpServletRequest;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Error404CommandTest {

    @InjectMocks
    private Error404Command error404Command;

    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnErrorPage() {
        String expectedPath = "jsp/404.jsp";
        String resultPath = error404Command.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnTruePermission() {
        assertTrue("permission is false", error404Command.checkPermissions(request));
    }
}