package org.study.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {
    private static final String LOCALE = "locale";

    @InjectMocks
    private LogoutCommand logoutCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Test
    public void shouldLogOut() {
        String expectedLocale = "ru";
        String expectedPath = "pages/logoutpage.jsp";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(expectedLocale);

        String resultPath = logoutCommand.execute(request);
        verify(request).setAttribute(LOCALE, expectedLocale);
        verify(session).invalidate();
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnTruePermission() {
        assertTrue("permission is false", logoutCommand.checkPermissions(request));
    }
}
