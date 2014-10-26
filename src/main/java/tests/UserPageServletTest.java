package tests;

import base.AccountService;
import org.junit.Test;
import servlets.UserPageServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserPageServletTest {
    final AccountService accountService = mock(AccountService.class);
    //TODO possible?!
    final UserPageServlet userPageServlet = new UserPageServlet(accountService);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void testDoGet() throws Exception {
        accountService.signIn("defaultUser1", "123", "session");
        when(request.getSession().getId()).thenReturn("session");
        //TODO WTF?!
//        userPageServlet.doGet(request, response);
        //TODO assert. Read local variable or read return value of response method...?
    }
}