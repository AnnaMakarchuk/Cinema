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

@RunWith(MockitoJUnitRunner.class)
public class UpdateClientCommandTest {
    @InjectMocks
    private UpdateClientCommand updateClientCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserFacade userFacade;

    private RegisteredUserDto registeredUserDto;
    private RegisteredUserDto updateRegisteredUserDto;

    @Before
    public void setUp() {
        registeredUserDto = new RegisteredUserDto();
        registeredUserDto.setUserId(1);
        registeredUserDto.setUserName("Alisa");
        registeredUserDto.setUserSurname("Test");
        registeredUserDto.setGender(Gender.FEMALE);
        registeredUserDto.setUserRole(UserRole.CLIENT);
        registeredUserDto.setUserLogin("alisa");
        registeredUserDto.setUserEMailAddress("a@i.ua");

        updateRegisteredUserDto = new RegisteredUserDto();
        updateRegisteredUserDto.setUserId(1);
        updateRegisteredUserDto.setUserName("Alisa");
        updateRegisteredUserDto.setUserSurname("Test");
        updateRegisteredUserDto.setGender(Gender.FEMALE);
        updateRegisteredUserDto.setUserRole(UserRole.CLIENT);
        updateRegisteredUserDto.setUserLogin("anna");
        updateRegisteredUserDto.setUserEMailAddress("a@i.ua");
    }

    @Test
    public void shouldUpdateClient() {
        String expectedPath = "pages/client/client_account_update.jsp";

        when(request.getParameter(ParametersNames.LOGIN)).thenReturn("anna");
        when(request.getParameter(ParametersNames.PASSWORD)).thenReturn("111");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersNames.USER)).thenReturn(registeredUserDto);
        when(userFacade.updateClient(registeredUserDto.getUserId(), "anna", "111"))
                .thenReturn(updateRegisteredUserDto);

        String resultPath = updateClientCommand.execute(request);
        verify(session).setAttribute(ParametersNames.USER, updateRegisteredUserDto);
        assertThat(resultPath, equalTo(expectedPath));
    }
}