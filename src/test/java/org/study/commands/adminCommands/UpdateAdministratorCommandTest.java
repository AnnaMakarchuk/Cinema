package org.study.commands.adminCommands;

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
import org.study.dto.AdministratorDto;
import org.study.facade.UserFacade;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.utils.ParametersNames;

@RunWith(MockitoJUnitRunner.class)
public class UpdateAdministratorCommandTest {
    @InjectMocks
    private UpdateAdministratorCommand updateAdministratorCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserFacade userFacade;

    private AdministratorDto administratorDto;
    private AdministratorDto updateAdministratorDto;

    @Before
    public void setUp() {
        administratorDto = new AdministratorDto();
        administratorDto.setAdministratorId(1);
        administratorDto.setAdministratorName("Alisa");
        administratorDto.setAdministratorSurname("Test");
        administratorDto.setGender(Gender.FEMALE);
        administratorDto.setUserRole(UserRole.ADMINISTRATOR);
        administratorDto.setAdministratorLogin("alisa");
        administratorDto.setAdministratorEMailAddress("a@i.ua");

        updateAdministratorDto = new AdministratorDto();
        updateAdministratorDto.setAdministratorId(1);
        updateAdministratorDto.setAdministratorName("Alisa");
        updateAdministratorDto.setAdministratorSurname("Test");
        updateAdministratorDto.setGender(Gender.FEMALE);
        updateAdministratorDto.setUserRole(UserRole.ADMINISTRATOR);
        updateAdministratorDto.setAdministratorLogin("anna");
        updateAdministratorDto.setAdministratorEMailAddress("a@i.ua");
    }



    @Test
    public void shouldUpdateAdmin(){
        String expectedPath = "pages/admin/admin_account_update.jsp";

        when(request.getParameter(ParametersNames.LOGIN)).thenReturn("anna");
        when(request.getParameter(ParametersNames.PASSWORD)).thenReturn("111");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersNames.ADMIN)).thenReturn(administratorDto);
        when(userFacade.updateAdministrator(administratorDto.getAdministratorId(),
                "anna", "111")).thenReturn(updateAdministratorDto);

        String resultPath = updateAdministratorCommand.execute(request);
        verify(session).setAttribute(ParametersNames.ADMIN, updateAdministratorDto);
        assertThat(resultPath, equalTo(expectedPath));

    }

}