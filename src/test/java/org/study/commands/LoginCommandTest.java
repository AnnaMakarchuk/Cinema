package org.study.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static junit.framework.TestCase.assertTrue;
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
import org.study.dto.AdministratorDto;
import org.study.dto.RegisteredUserDto;
import org.study.facade.UserFacade;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {
    @InjectMocks
    private LoginCommand loginCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserFacade userFacade;

    private RegisteredUserDto registeredUserDtoClient;
    private RegisteredUserDto registeredUserDtoClient2;
    private AdministratorDto administratorDto;

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

        registeredUserDtoClient2 = new RegisteredUserDto();
        registeredUserDtoClient2.setUserId(1);
        registeredUserDtoClient2.setUserName("Alisa");
        registeredUserDtoClient2.setUserSurname("Test");
        registeredUserDtoClient2.setGender(Gender.FEMALE);
        registeredUserDtoClient2.setUserRole(UserRole.ADMINISTRATOR);
        registeredUserDtoClient2.setUserLogin("alisa");
        registeredUserDtoClient2.setUserEMailAddress("a@i.ua");

        administratorDto = new AdministratorDto();
        administratorDto.setAdministratorId(1);
        administratorDto.setAdministratorName("Alisa");
        administratorDto.setAdministratorSurname("Test");
        administratorDto.setGender(Gender.FEMALE);
        administratorDto.setUserRole(UserRole.ADMINISTRATOR);
        administratorDto.setAdministratorLogin("alisa");
        administratorDto.setAdministratorEMailAddress("a@i.ua");
    }

    @Test
    public void shouldTestSuccessfulLoginClient() {
        String expectedPath = "pages/client/client_cabinet.jsp";
        RegisteredUserDto expected = registeredUserDtoClient;

        when(request.getParameter(ParametersNames.LOGIN)).thenReturn("alisa");
        when(request.getParameter(ParametersNames.PASSWORD)).thenReturn("111");
        when(userFacade.getRegisterUser("alisa", "111")).thenReturn(expected);
        when(request.getSession()).thenReturn(session);

        String resultPath = loginCommand.execute(request);
        verify(userFacade).getRegisterUser("alisa", "111");
        verify(session).setAttribute(ParametersNames.USER, expected);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldTestSuccessfulLoginAdmin() {
        String expectedPath = "pages/admin/adminpage.jsp";
        AdministratorDto expected = administratorDto;

        when(request.getParameter(ParametersNames.LOGIN)).thenReturn("alisa");
        when(request.getParameter(ParametersNames.PASSWORD)).thenReturn("111");
        when(userFacade.getRegisterUser("alisa", "111")).thenReturn(registeredUserDtoClient2);
        when(request.getSession()).thenReturn(session);
        when(userFacade.getAdministratorByID(1)).thenReturn(administratorDto);

        String resultPath = loginCommand.execute(request);
        verify(userFacade).getRegisterUser("alisa", "111");
        verify(userFacade).getAdministratorByID(1);
        verify(session).setAttribute(ParametersNames.ADMIN, expected);
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldTestFailLogin() {
        String expectedPath = "pages/401.jsp";

        when(request.getParameter(ParametersNames.LOGIN)).thenReturn("alisa");
        when(request.getParameter(ParametersNames.PASSWORD)).thenReturn("111");
        when(userFacade.getRegisterUser("alisa", "111")).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(userFacade.getAdministratorByID(1)).thenReturn(administratorDto);

        String resultPath = loginCommand.execute(request);
        verify(userFacade).getRegisterUser("alisa", "111");
        assertThat(resultPath, equalTo(expectedPath));
    }

    @Test
    public void shouldReturnTruePermission() {
        assertTrue("permission is false", loginCommand.checkPermissions(request));
    }
}
