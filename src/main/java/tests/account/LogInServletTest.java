package tests.account;

import base.AccountService;
import base.ResponsesCode;
import org.junit.Test;
import servlets.LogInServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LogInServletTest {
    final AccountService accountService = mock(AccountService.class);
    final LogInServlet logInServlet = new LogInServlet(accountService);
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession httpSession = mock(HttpSession.class);
    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);

    String loginString = "defaultUser1";
    String passwordString = "123";
    String sessionString = "session";

    @Test
    public void testDoPostResponseOk() throws Exception {
        when(request.getParameter("login")).thenReturn(loginString);
        when(request.getParameter("password")).thenReturn(passwordString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        when((accountService).signIn(loginString, passwordString, sessionString)).thenReturn(ResponsesCode.OK);

        logInServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).signIn(loginString, passwordString, sessionString);
        assertTrue(stringWriter.toString().contains("The user defaultUser1 signin"));
    }

    @Test
    public void testDoPostResponseWrongSignIn() throws Exception {
        when(request.getParameter("login")).thenReturn(loginString);
        when(request.getParameter("password")).thenReturn(passwordString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signIn(loginString, passwordString, sessionString)).thenReturn(ResponsesCode.WRONG_LOGIN);

        logInServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).signIn(loginString, passwordString, sessionString);
        assertTrue(stringWriter.toString().contains("Enter correct login and password"));
    }

    @Test
    public void testDoPostResponseUnknownError() throws Exception {
        when(request.getParameter("login")).thenReturn(loginString);
        when(request.getParameter("password")).thenReturn(passwordString);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionString);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signIn(loginString, passwordString, sessionString)).thenReturn(ResponsesCode.ALREADY_EXISTS);

        logInServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).signIn(loginString, passwordString, sessionString);
        assertTrue(stringWriter.toString().contains("Unknown error"));
    }
}