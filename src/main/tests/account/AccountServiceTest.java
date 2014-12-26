package account;

import accounting.AccountService;
import accounting.AccountServiceImpl;
import accounting.database.DatabaseBuilder;
import accounting.database.UserDataSet;
import frontend.ResponsesCode;
import messageSystem.MessageSystem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {

    static int signUpCount;

    static MessageSystem messageSystem = new MessageSystem();
    static DatabaseBuilder databaseBuilder = new DatabaseBuilder("hibernate_test.cfg.xml");

    static AccountService accountService = new AccountServiceImpl(messageSystem, databaseBuilder.getUsersDAO());
    ResponsesCode response;

    void incSignUpUsers(ResponsesCode res) {
        if (res.equals(ResponsesCode.OK))
            ++signUpCount;
    }

    @Test
    public void testSignUpOk() throws Exception {
        response = accountService.signup("testSignUpOk", "testUser@mail.ru", "123");
        incSignUpUsers(response);

        assertEquals(ResponsesCode.OK, response);
    }

    @Test
    public void testSignUpAlreadyExist() throws Exception {

        response = accountService.signup("testSignUpAlreadyExist", "defaultUser1@mail.ru", "123");
        incSignUpUsers(response);

        response = accountService.signup("testSignUpAlreadyExist", "defaultUser1@mail.ru", "123");
        incSignUpUsers(response);

        assertEquals(ResponsesCode.ALREADY_EXISTS, response);
    }

    @Test
    public void testSignInOk() throws Exception {

        response = accountService.signup("testSignInOk", "defaultUser1@mail.ru", "123");
        incSignUpUsers(response);

        response = accountService.login("testSignInOk", "123", "session");
        assertEquals(ResponsesCode.OK, response);
        accountService.logout("session");
    }

    @Test
    public void testSignInWrongSighIn() throws Exception {
        response = accountService.login("testSignInWrongSighIn", "123", "unknownSession");
        assertEquals(ResponsesCode.WRONG_LOGIN, response);
    }

    @Test
    public void testSignOut() throws Exception {
        response = accountService.signup("testSignOut", "defaultUser1@mail.ru", "123");
        incSignUpUsers(response);

        accountService.login("testSignOut", "123", "session");
        assertEquals(1, accountService.getCountActiveUsers());

        accountService.logout("session");
        assertEquals(0, accountService.getCountActiveUsers());
    }

    @Test
    public void testCountSignUp() throws Exception {
        response = accountService.signup("testCountSignUp", "defaultUser1@mail.ru", "123");
        incSignUpUsers(response);

        assertEquals(signUpCount, accountService.getCountSignupUsers());
    }

    @Test
    public void testCountSignIn() throws Exception {
        response = accountService.signup("testCountSignIn", "defaultUser1@mail.ru", "123");
        incSignUpUsers(response);

        accountService.login("testCountSignIn", "123", "session");

        assertEquals(1, accountService.getCountActiveUsers());
    }

    @Test
    public void testGetCurrentUserData() throws Exception {
        UserDataSet user;

        response = accountService.signup("testGetCurrentUserData", "testUserData@mail.ru", "123");
        incSignUpUsers(response);

        accountService.login("testGetCurrentUserData", "123", "session");
        user = accountService.getUserDataSet("session");
        assertEquals(user.getLogin(), "testGetCurrentUserData");
        assertEquals(user.getEmail(), "testUserData@mail.ru");
        assertEquals(user.getPassword(), "123");

        accountService.logout("session");
    }
}