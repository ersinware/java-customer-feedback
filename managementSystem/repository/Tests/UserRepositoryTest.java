package repository.Tests;

import constants.Constants;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static repository.Repository.addNewUser;
import static repository.Repository.getPasswordAndAuthorization;

public class UserRepositoryTest {

    @Test
    public void addNewUserTest() {
        assertTrue(addNewUser("Test User", "testpass", "testname", "testsurname"));
    }

    @Test
    public void addExistingUserTest() {
        assertFalse(addNewUser("ekaraer", "password", "Ersin", "Karaer"));
    }

    @Test
    public void addNewUserMissingParameters() {
        assertThrows(RuntimeException.class, () -> addNewUser(null, "password", "x", "y"));
        assertThrows(RuntimeException.class, () -> addNewUser("newuser", null, "x", "y"));
        assertThrows(RuntimeException.class, () -> addNewUser("newuser", "password", null, "y"));
        assertThrows(RuntimeException.class, () -> addNewUser("newuser", "password", "x", null));
    }

    @Test
    public void getPasswordAndAuthorizationExistingUserTest() {
        String[] result = getPasswordAndAuthorization("ekaraer");
        assertNotNull(result);
        assertEquals("pass", result[0]);
        assertEquals(Constants.AUTHENTICATED_ADMIN, result[1]);
    }

    @Test
    public void getPasswordAndAuthorizationNonExistingUserTest() {
        assertNull(getPasswordAndAuthorization("Non existed user"));
    }

    @Test
    public void getPasswordAndAuthorizationMissingParameters() {
        assertNull(getPasswordAndAuthorization(null));
    }
}