package tests.account;

import accounting.AccountService;
import accounting.AccountServiceImpl;
import frontend.servlets.LogoutServlet;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class LogoutServletTest {
    final AccountServiceImpl accountServiceReal = new AccountServiceImpl();
    final AccountService accountService = mock(AccountService.class);
    final LogoutServlet logoutServlet = new LogoutServlet(accountService);
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
        accountServiceReal.login(loginString, passwordString, sessionString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.toString()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        accountService.login(loginString, passwordString, sessionString);

        logoutServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).logout(sessionString);
        assertTrue(stringWriter.toString().contains("Done"));
    }
}