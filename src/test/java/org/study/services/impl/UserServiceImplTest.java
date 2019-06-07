package org.study.services.impl;

import java.util.ArrayList;
import java.util.List;
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
import org.study.dao.TicketDao;
import org.study.dao.UserDao;
import org.study.dao.UserRoleDao;
import org.study.models.RegisteredUser;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Mock
    private UserRoleDao userRoleDao;

    @Mock
    private TicketDao ticketDao;

    @Before
    public void setUp() {
        RegisteredUser registeredUser = new RegisteredUser(1, "Alisa", "Test",
                Gender.FEMALE, UserRole.CLIENT, "alisa", "a@i.ua", "111");
        when(userDao.getUserById(1)).thenReturn(registeredUser);
        when(userDao.getUserByLoginAndPassword("alisa", "111")).thenReturn(registeredUser);
        when(userRoleDao.getId("client")).thenReturn(1);
        when(ticketDao.getUserIdByScheduleId(1)).thenReturn(1);
    }

    @Test
    public void shouldCallGetMethodUserDAO() {
        userService.viewUser(1);
        verify(userDao).getUserById(1);
    }

    @Test
    public void shouldReturnClientById() {
        RegisteredUser expected = new RegisteredUser(1, "Alisa", "Test",
                Gender.FEMALE, UserRole.CLIENT, "alisa", "a@i.ua", "111");

        RegisteredUser result = userService.viewUser(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallGetByLoginPassMethodUserDAO() {
        userService.getUserByLoginPassword("alisa", "111");
        verify(userDao).getUserByLoginAndPassword("alisa", "111");
    }

    @Test
    public void shouldReturnClientByLoginPassword() {
        RegisteredUser expected = new RegisteredUser(1, "Alisa", "Test",
                Gender.FEMALE, UserRole.CLIENT, "alisa", "a@i.ua", "111");

        RegisteredUser result = userService.getUserByLoginPassword("alisa", "111");

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallCreateMethodUserDAO() {
        userService.registerNewUser("anna", "mak", "client", "female",
                "login", "e-mail@i.ua", "pass");
        RegisteredUser registeredUser = new RegisteredUser("anna", "mak", Gender.FEMALE,
                "login", "e-mail@i.ua", "pass");
        verify(userDao).create(1, registeredUser);
    }

    @Test
    public void shouldCallUpdateGetMethodUserDAO() {
        userService.updateUserInformation(1, "al", "123");
        verify(userDao).update(1, "al", "123");
        verify(userDao).getUserById(1);
    }

    @Test
    public void shouldUpdateLogPassById() {
        RegisteredUser expected = new RegisteredUser(1, "Alisa", "Test",
                Gender.FEMALE, UserRole.CLIENT, "alisa", "a@i.ua", "111");

        RegisteredUser result = userService.updateUserInformation(1, "al", "123");

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallDeleteMethodUserDAO() {
        userService.deleteUser(1);
        verify(userDao).delete(1);
    }

    @Test
    public void shouldCallCreateListByTicketMethodUserDAO() {
        List<Integer> scheduleId = new ArrayList<>();
        scheduleId.add(1);
        scheduleId.add(2);
        userService.createListUsersFromTicket(scheduleId);
        verify(ticketDao).getUserIdByScheduleId(1);
        verify(userDao).getUserById(1);
    }

    @Test
    public void createListUsersFromTicket() {
        List<Integer> scheduleId = new ArrayList<>();
        scheduleId.add(1);
        scheduleId.add(2);
        List<RegisteredUser> result = userService.createListUsersFromTicket(scheduleId);
        RegisteredUser registeredUser = new RegisteredUser(1, "Alisa", "Test",
                Gender.FEMALE, UserRole.CLIENT, "alisa", "a@i.ua", "111");
        List<RegisteredUser> expected = new ArrayList<>();
        expected.add(registeredUser);

        assertThat(result, equalTo(expected));
    }
}
