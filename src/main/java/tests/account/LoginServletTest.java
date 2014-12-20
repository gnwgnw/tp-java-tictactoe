package tests.account;

import accounting.AccountService;
import frontend.ResponsesCode;
import frontend.servlets.LoginServlet;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LoginServletTest {
    final AccountService accountService = mock(AccountService.class);
    final HttpSession httpSession       = mock(HttpSession.class);
    final HttpServletRequest request    = mock(HttpServletRequest.class);
    final HttpServletResponse response  = mock(HttpServletResponse.class);

    final LoginServlet loginServlet     = new LoginServlet(accountService);
    final StringWriter stringWriter     = new StringWriter();
    final PrintWriter printWriter       = new PrintWriter(stringWriter);

    String loginString      = "defaultUser1";
    String passwordString   = "123";
    String sessionString    = "session";
    String jsonOK           = "{\"status\":\"OK\",\"response\":{}}\n";
    String jsonWRONG_LOGIN    = "{\"status\":\"WRONG_LOGIN\"}\n";
    String jsonALREADY_EXISTS = "{\"status\":\"ALREADY_EXISTS\"}\n";


    @Test
    public void testDoPostResponseOk() throws Exception {
        when(request.getParameter("login")).thenReturn(loginString);
        when(request.getParameter("password")).thenReturn(passwordString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        when((accountService).login(loginString, passwordString, sessionString)).thenReturn(ResponsesCode.OK);

        loginServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).login(loginString, passwordString, sessionString);
        assertTrue(stringWriter.toString().contains(jsonOK));
    }

    @Test
    public void testDoPostResponseWrongSignIn() throws Exception {
        when(request.getParameter("login")).thenReturn(loginString);
        when(request.getParameter("password")).thenReturn(passwordString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.login(loginString, passwordString, sessionString)).thenReturn(ResponsesCode.WRONG_LOGIN);

        loginServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).login(loginString, passwordString, sessionString);
        assertTrue(stringWriter.toString().contains(jsonWRONG_LOGIN));
    }

    @Test
    public void testDoPostResponseUnknownError() throws Exception {
        when(request.getParameter("login")).thenReturn(loginString);
        when(request.getParameter("password")).thenReturn(passwordString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.login(loginString, passwordString, sessionString)).thenReturn(ResponsesCode.ALREADY_EXISTS);

        loginServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).login(loginString, passwordString, sessionString);
        assertTrue(stringWriter.toString().contains(jsonALREADY_EXISTS));
    }
}