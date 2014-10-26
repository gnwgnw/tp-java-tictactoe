package tests;

import base.AccountService;
import org.junit.Test;
import servlets.SignOutServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SignOutServletTest {
    final AccountService accountService = mock(AccountService.class);
    //TODO possible?!
    final SignOutServlet signOutServlet = new SignOutServlet(accountService);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void testDoPost() throws Exception {
        accountService.signIn("defaultUser1", "123", "session");
        assertEquals(1, accountService.countSignIn());
        //TODO WTF?!
//        signOutServlet.doPost(request, response);
//        assertEquals(0, accountService.countSignIn());
        //TODO like this or read read return value of response method...?
    }
}