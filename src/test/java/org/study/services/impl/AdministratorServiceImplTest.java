package org.study.services.impl;

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
import org.study.dao.AdministratorDao;
import org.study.models.Administrator;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class AdministratorServiceImplTest {
    @InjectMocks
    private AdministratorServiceImpl administratorService;

    @Mock
    private AdministratorDao administratorDao;

    @Before
    public void setUp() {
        Administrator administrator = new Administrator(1, "Cate",
                "Test", Gender.FEMALE, UserRole.ADMINISTRATOR, "cat",
                "a@i.ua", "111",25.00, 40);
        when(administratorDao.get(1)).thenReturn(administrator);
    }

    @Test
    public void shouldCallGetMethodAdminDAO() {
        administratorService.viewAdmin(1);
        verify(administratorDao).get(1);
    }

    @Test
    public void shouldReturnAdminById() {
        Administrator expected = new Administrator(1, "Cate", "Test",
                Gender.FEMALE, UserRole.ADMINISTRATOR, "cat", "a@i.ua",
                "111",25.00, 40);

        Administrator result = administratorService.viewAdmin(1);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldCallUpdateMethodAdminDAO() {
        administratorService.getUpdateAdministrator(1, "anna", "111");
        verify(administratorDao).update(1, "anna", "111");
    }

    @Test
    public void shouldReturnUpdateAdministrator() {
        Administrator expected = new Administrator(1, "Cate", "Test",
                Gender.FEMALE, UserRole.ADMINISTRATOR, "cat", "a@i.ua",
                "111",25.00, 40);

        Administrator result = administratorService.getUpdateAdministrator(1, "anna",
                "111");

        assertThat(result, equalTo(expected));
    }
}
