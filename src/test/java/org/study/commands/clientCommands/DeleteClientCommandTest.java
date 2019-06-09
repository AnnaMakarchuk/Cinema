package org.study.commands.clientCommands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.study.dto.RegisteredUserDto;
import org.study.facade.UserFacade;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.utils.ParametersNames;
import static org.study.utils.ParametersNames.LOCALE;

@RunWith(MockitoJUnitRunner.class)
public class DeleteClientCommandTest {
    @InjectMocks
    private DeleteClientCommand deleteClientCommand;

    @Mock
    private UserFacade userFacade;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    private RegisteredUserDto registeredUserDtoClient;

    @Before
    public void setUp() {
        registeredUserDtoClient = new RegisteredUserDto();
        registeredUserDtoClient.setUserId(1);
        registeredUserDtoClient.setUserName("Alisa");
        registeredUserDtoClient.setUserSurname("Test");
        registeredUserDtoClient.setGender(Gender.FEMALE);
        registeredUserDtoClient.setUserRole(UserRole.CLIENT);
        registeredUserDtoClient.setUserLogin("alisa");
        registeredUserDtoClient.setUserEMailAddress("a@i.ua");
    }

    @Test
    public void shouldDeleteUser() {
        String expectedPath = "pages/client/client_account_delete.jsp";
        String expectedLocale = "ru";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersNames.USER)).thenReturn(registeredUserDtoClient);
        when(session.getAttribute(LOCALE)).thenReturn(expectedLocale);

        String resultPath = deleteClientCommand.execute(request);
        verify(request).setAttribute(LOCALE, expectedLocale);
        verify(session).invalidate();
        verify(userFacade).deleteClient(registeredUserDtoClient.getUserId());
        assertThat(resultPath, equalTo(expectedPath));
    }
}
