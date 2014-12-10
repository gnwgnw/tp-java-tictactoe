package tests.account;

import accounting.AccountService;
import accounting.AccountServiceImpl;
import frontend.ResponsesCode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {

    final AccountService accountService = new AccountServiceImpl();
    ResponsesCode response;

    @Test
    public void testSignUpOk() throws Exception {
        response = accountService.signup("testUser", "testUser@mail.ru", "123");
        assertEquals(ResponsesCode.OK, response);
    }

    @Test
    public void testSignUpAlreadyExist() throws Exception {
        response = accountService.signup("defaultUser1", "defaultUser1@mail.ru", "123");
        response = accountService.signup("defaultUser1", "defaultUser1@mail.ru", "123");
        assertEquals(ResponsesCode.ALREADY_EXISTS, response);
    }
/*
@Test
public void testSignInOk() throws Exception {
response = accountService.login("defaultUser1", "123", "session");
assertEquals(ResponsesCode.OK, response);
}

@Test
public void testSignInWrongSighIn() throws Exception {
response = accountService.login("unknownUser", "123", "unknownSession");
assertEquals(ResponsesCode.WRONG_LOGIN, response);
}

@Test
public void testSignOut() throws Exception {
accountService.login("defaultUser1", "123", "session");
assertEquals(1, accountService.getCountActiveUsers());
accountService.logout("session");
assertEquals(0, accountService.getCountActiveUsers());
}

@Test
public void testCountSignUp() throws Exception {
accountService.signup("testUser", "testUser@mail.ru", "123");
assertEquals(3, accountService.getCountSignupUsers());
}

@Test
public void testCountSignIn() throws Exception {
accountService.login("defaultUser1", "123", "session");
assertEquals(1, accountService.getCountActiveUsers());
}

@Test
public void testGetCurrentUserProfile() throws Exception {
UserProfile userProfile;
accountService.login("defaultUser1", "123", "session");
userProfile = accountService.getUserProfile("session");
assertEquals(userProfile.getLogin(), "defaultUser1");
assertEquals(userProfile.getEmail(), "defaultUser1@mail.ru");
assertEquals(userProfile.getPassword(), "123");
}*/
}

