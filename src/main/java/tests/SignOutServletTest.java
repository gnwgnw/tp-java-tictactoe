package tests;

import base.AccountService;
import org.junit.Test;
import servlets.SignOutServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SignOutServletTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    final AccountService accountService = mock(AccountService.class);
    //TODO possible?!
    final SignOutServlet signOutServlet = new SignOutServlet(accountService);

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