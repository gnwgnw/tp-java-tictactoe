package tests.account;

import base.AccountService;
import org.junit.Test;
import service.AccountServiceImpl;
import servlets.LogOutServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class LogOutServletTest {
    final AccountServiceImpl accountServiceReal = new AccountServiceImpl();
    final AccountService accountService = mock(AccountService.class);
    final LogOutServlet logOutServlet = new LogOutServlet(accountService);
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession httpSession = mock(HttpSession.class);
    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);

    String loginString = "defaultUser1";
    String passwordString = "123";
    String sessionString = "session";

    @Test
    public void testDoPost() throws Exception {
        accountServiceReal.signIn(loginString, passwordString, sessionString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.toString()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        accountService.signIn(loginString, passwordString, sessionString);

        logOutServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).signOut(sessionString);
        assertTrue(stringWriter.toString().contains("Done"));
    }
}