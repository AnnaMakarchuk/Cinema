package org.study.commands;

import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.facade.UserFacade;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationUserTest {

    @InjectMocks
    private RegistrationUser registrationUser;

    @Mock
    private HttpServletRequest request;

    @Mock
    private UserFacade userFacade;

    @Test
    public void shouldRegisterNewUser() {
        String expectedPath = "pages/registration.jsp";

        when(request.getParameter(ParametersNames.NAME)).thenReturn("Alisa");
        when(request.getParameter(ParametersNames.SURNAME)).thenReturn("Test");
        when(request.getParameter(ParametersNames.GENDER)).thenReturn("female");
        when(request.getParameter(ParametersNames.LOGIN)).thenReturn("alisa");
        when(request.getParameter(ParametersNames.EMAIL)).thenReturn("a@i.ua");
        when(request.getParameter(ParametersNames.PASSWORD)).thenReturn("111");

        String resultPath = registrationUser.execute(request);
        verify(userFacade).createNewUser("Alisa", "Test", "female", "alisa",
                "a@i.ua", "111");
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldNotRegisterNewUser() {
        String expectedPath = "pages/401.jsp";

        when(request.getParameter(ParametersNames.NAME)).thenReturn("Alisa");
        when(request.getParameter(ParametersNames.SURNAME)).thenReturn("111");
        when(request.getParameter(ParametersNames.GENDER)).thenReturn("female");
        when(request.getParameter(ParametersNames.LOGIN)).thenReturn("alisa");
        when(request.getParameter(ParametersNames.EMAIL)).thenReturn("ai.ua");
        when(request.getParameter(ParametersNames.PASSWORD)).thenReturn("111");

        String resultPath = registrationUser.execute(request);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnTruePermission() {
        assertTrue("permission is false", registrationUser.checkPermissions(request));
    }
}