package org.study.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
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
import org.study.dto.SessionScheduleDto;
import org.study.models.Administrator;
import org.study.models.RegisteredUser;
import org.study.models.SessionSchedule;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;
import org.study.services.AdministratorService;
import org.study.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {
    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserService userService;

    @Mock
    private AdministratorService administratorService;

    @Before
    public void setUp() {
        RegisteredUser registeredUser = new RegisteredUser(1, "Alisa", "Test",
                Gender.FEMALE, UserRole.CLIENT, "alisa", "a@i.ua", "111");
        List<RegisteredUser> registeredUserList = new ArrayList<>();
        registeredUserList.add(registeredUser);
        Administrator administrator = new Administrator(1, "Cate", "Test",
                Gender.FEMALE, UserRole.ADMINISTRATOR, "cat", "a@i.ua",
                "111", 25.00, 40);
        List<Integer> scheduleIdList = Collections.singletonList(1);

        when(userService.getUserByLoginPassword("alisa", DigestUtils.md5Hex("111")))
                .thenReturn(registeredUser);
        when(userService.viewUser(1)).thenReturn(registeredUser);
        when(userService.createListUsersFromTicket(scheduleIdList)).thenReturn(registeredUserList);
        when(administratorService.viewAdmin(1)).thenReturn(administrator);
        when(administratorService.getUpdateAdministrator(1, "anna", DigestUtils.md5Hex("111")))
                .thenReturn(administrator);
    }

    @Test
    public void shouldCallGetByLogPassServiceMethod() {
        userFacade.getRegisterUser("alisa", "111");
        verify(userService).getUserByLoginPassword("alisa", DigestUtils.md5Hex("111"));
    }

    @Test
    public void shouldReturnDTOByLogPass() {
        assertThat(userFacade.getRegisterUser("alisa", "111"), instanceOf(RegisteredUserDto.class));
    }

    @Test
    public void shouldReturnUserByLogPass() {
        RegisteredUserDto expected = new RegisteredUserDto();
        expected.setUserId(1);
        expected.setUserName("Alisa");
        expected.setUserSurname("Test");
        expected.setGender(Gender.FEMALE);
        expected.setUserRole(UserRole.CLIENT);
        expected.setUserLogin("alisa");
        expected.setUserEMailAddress("a@i.ua");

        RegisteredUserDto result = userFacade.getRegisterUser("alisa", "111");

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallGetServiceMethod() {
        userFacade.getRegisterUserByID(1);
        verify(userService).viewUser(1);
    }

    @Test
    public void shouldReturnUserByID() {
        RegisteredUserDto expected = new RegisteredUserDto();
        expected.setUserId(1);
        expected.setUserName("Alisa");
        expected.setUserSurname("Test");
        expected.setGender(Gender.FEMALE);
        expected.setUserRole(UserRole.CLIENT);
        expected.setUserLogin("alisa");
        expected.setUserEMailAddress("a@i.ua");

        RegisteredUserDto result = userFacade.getRegisterUserByID(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallCreateServiceMethod() {
        userFacade.createNewUser("Anna", "Mak", "female", "anna",
                "a@gmail.com", "111");
        verify(userService).registerNewUser("Anna", "Mak", "client", "female",
                "anna", "a@gmail.com", "111");
    }

    @Test
    public void shouldCallGetUserListServiceMethod() {
        List<Integer> scheduleIdList = Arrays.asList(1, 3, 5);
        userFacade.createUserListWithCancelledSchedule(scheduleIdList);
        verify(userService).createListUsersFromTicket(scheduleIdList);
    }

    @Test
    public void shouldReturnListDySchedule() {
        List<Integer> scheduleIdList = Collections.singletonList(1);
        List<RegisteredUserDto> expected = new ArrayList<>();
        RegisteredUserDto registeredUserDTO = new RegisteredUserDto();
        registeredUserDTO.setUserId(1);
        registeredUserDTO.setUserName("Alisa");
        registeredUserDTO.setUserSurname("Test");
        registeredUserDTO.setGender(Gender.FEMALE);
        registeredUserDTO.setUserRole(UserRole.CLIENT);
        registeredUserDTO.setUserLogin("alisa");
        registeredUserDTO.setUserEMailAddress("a@i.ua");
        expected.add(registeredUserDTO);

        List<RegisteredUserDto> result = userFacade.createUserListWithCancelledSchedule(scheduleIdList);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallDeleteServiceMethod() {
        userFacade.deleteClient(1);
        verify(userService).deleteUser(1);
    }

    @Test
    public void shouldCallGetAdminServiceMethod() {
        userFacade.getAdministratorByID(1);
        verify(administratorService).viewAdmin(1);
    }

    @Test
    public void shouldReturnAdminDTOById() {
        AdministratorDto expected = new AdministratorDto();
        expected.setAdministratorId(1);
        expected.setAdministratorName("Cate");
        expected.setAdministratorSurname("Test");
        expected.setGender(Gender.FEMALE);
        expected.setUserRole(UserRole.ADMINISTRATOR);
        expected.setAdministratorLogin("cat");
        expected.setAdministratorEMailAddress("a@i.ua");
        expected.setAdministratorWorkingHoursPerWeek(40);

        AdministratorDto result = userFacade.getAdministratorByID(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallUpdateAdminServiceMethod() {
        userFacade.updateAdministrator(1, "anna", "111");
        verify(administratorService).getUpdateAdministrator(1, "anna", DigestUtils.md5Hex("111"));
    }

    @Test
    public void shouldReturnUpdateAdminDTO() {
        AdministratorDto expected = new AdministratorDto();
        expected.setAdministratorId(1);
        expected.setAdministratorName("Cate");
        expected.setAdministratorSurname("Test");
        expected.setGender(Gender.FEMALE);
        expected.setUserRole(UserRole.ADMINISTRATOR);
        expected.setAdministratorLogin("cat");
        expected.setAdministratorEMailAddress("a@i.ua");
        expected.setAdministratorWorkingHoursPerWeek(40);

        AdministratorDto result = userFacade.updateAdministrator(1, "anna", "111");

        assertThat(result, equalTo(expected));
    }
}
