package tests;

import base.ResponsesCode;
import base.UserProfile;
import org.junit.Test;
import service.AccountServiceImpl;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {

    final AccountServiceImpl accountService = new AccountServiceImpl();

    @Test
    public void testSignUpOk() throws Exception {
        ResponsesCode response;
        response = accountService.signUp("testUser", "testUser@mail.ru", "123");
        assertEquals(ResponsesCode.OK, response);
    }

    @Test
    public void testSignUpAlreadyExist() throws Exception {
        ResponsesCode response;
        response = accountService.signUp("defaultUser1", "defaultUser1@mail.ru", "123");
        assertEquals(ResponsesCode.ALREADY_EXISTS, response);
    }

    @Test
    public void testSignInOk() throws Exception {
        ResponsesCode response;
        response = accountService.signIn("defaultUser1", "123", "session");
        assertEquals(ResponsesCode.OK, response);
    }

    @Test
    public void testSignInWrongSighIn() throws Exception {
        ResponsesCode response;
        response = accountService.signIn("unknownUser", "123", "unknownSession");
        assertEquals(ResponsesCode.WRONG_SIGNIN, response);
    }

    @Test
    public void testSignOut() throws Exception {
        accountService.signIn("defaultUser1", "123", "session");
        assertEquals(1, accountService.countSignIn());
        accountService.signOut("session");
        assertEquals(0, accountService.countSignIn());
    }

    @Test
    public void testCountSignUp() throws Exception {
        accountService.signUp("testUser", "testUser@mail.ru", "123");
        assertEquals(3, accountService.countSignUp());
    }

    @Test
    public void testCountSignIn() throws Exception {
        accountService.signIn("defaultUser1", "123", "session");
        assertEquals(1, accountService.countSignIn());
    }

    @Test
    public void testGetCurrentUserProfile() throws Exception {
        UserProfile userProfile;
        accountService.signIn("defaultUser1", "123", "session");
        userProfile = accountService.getUserProfile("session");
        assertEquals(userProfile.getLogin(), "defaultUser1");
        assertEquals(userProfile.getEmail(), "defaultUser1@mail.ru");
        assertEquals(userProfile.getPassword(), "123");
    }
}