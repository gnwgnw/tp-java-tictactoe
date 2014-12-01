package tests.account;

import base.AccountService;
import base.ResponsesCode;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AccountServiceDBTest {

    final AccountService accountService = new AccountServiceImplTest();
    ResponsesCode response;

    @Test
    public void testSignUpOk() throws Exception {
        Random random = new Random();
        int suffix = random.nextInt(1000);
        response = accountService.signUp("Random1" + suffix, "testUser@mail.ru", "123");
        assertEquals(ResponsesCode.OK, response);
    }

    @Test
    public void testSignUpAlreadyExist() throws Exception {
        response = accountService.signUp("AlreadyExistsUser", "q@q", "123");
        assertEquals(ResponsesCode.ALREADY_EXISTS, response);
    }

    @Test
    public void testSignInOk() throws Exception {
        response = accountService.signIn("AlreadyExistsUser", "123", "session");
        assertEquals(ResponsesCode.OK, response);
    }

    @Test
    public void testSignInWrongSighIn() throws Exception {
        response = accountService.signIn("unknownUser", "123", "unknownSession");
        assertEquals(ResponsesCode.WRONG_LOGIN, response);
    }

    @Test
    public void testSignOut() throws Exception {
        accountService.signIn("AlreadyExistsUser", "123", "session");
        assertEquals(1, accountService.countSignIn());
        accountService.signOut("session");
        assertEquals(0, accountService.countSignIn());
    }

    @Test
    public void testCountSignUp() throws Exception {
        accountService.signUp("NewUser1", "testUser@mail.ru", "123");
        accountService.signUp("NewUser2", "testUser@mail.ru", "123");
        accountService.signUp("NewUser3", "testUser@mail.ru", "123");
        assertEquals(3, accountService.countSignUp());
    }
/*
@Test
public void testCountSignIn() throws Exception {
accountService.signIn("defaultUser1", "123", "session");
assertEquals(1, accountService.countSignIn());
}

@Test
public void testGetCurrentUserProfile() throws Exception {
UserDataSet userProfile;
accountService.signIn("defaultUser1", "123", "session");
userProfile = accountService.getUserDataSet("session");
assertEquals(userProfile.getLogin(), "defaultUser1");
assertEquals(userProfile.getEmail(), "defaultUser1@mail.ru");
assertEquals(userProfile.getPassword(), "123");
}*/
}

