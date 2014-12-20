package tests.account;

import accounting.AccountService;
import frontend.ResponsesCode;
import frontend.servlets.SignupServlet;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SignupServletTest {
    final AccountService accountService = mock(AccountService.class);
    final SignupServlet signupServlet = new SignupServlet(accountService);
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);

    String newLoginString = "testUser";
    String newEmailString = "testUser@mail.ru";
    String newPasswordString = "123";

    String oldLoginString = "defaultUser1";
    String oldEmailString = "defaultUser1@mail.ru";
    String oldPasswordString = "123";

    String jsonOK           = "{\"status\":\"OK\"}\n";
    String jsonWRONG_LOGIN   = "{\"status\":\"WRONG_LOGIN\"}\n";
    String jsonALREADY_EXISTS   = "{\"status\":\"ALREADY_EXISTS\"}\n";

    @Test
    public void testDoPostOK() throws Exception {
        when(request.getParameter("login")).thenReturn(newLoginString);
        when(request.getParameter("email")).thenReturn(newEmailString);
        when(request.getParameter("password")).thenReturn(newPasswordString);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signup(newLoginString, newEmailString, newPasswordString)).thenReturn(ResponsesCode.OK);

        signupServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).signup(newLoginString, newEmailString, newPasswordString);
        assertTrue(stringWriter.toString().contains(jsonOK));
    }

    @Test
    public void testDoPostAlreadyExist() throws Exception {
        when(request.getParameter("login")).thenReturn(oldLoginString);
        when(request.getParameter("email")).thenReturn(oldEmailString);
        when(request.getParameter("password")).thenReturn(oldPasswordString);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signup(oldLoginString, oldEmailString, oldPasswordString)).thenReturn(ResponsesCode.ALREADY_EXISTS);

        signupServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).signup(oldLoginString, oldEmailString, oldPasswordString);
        assertTrue(stringWriter.toString().contains(jsonALREADY_EXISTS));
    }

    @Test
    public void testDoPostWrongSighUn() throws Exception {
        when(request.getParameter("login")).thenReturn(oldLoginString);
        when(request.getParameter("email")).thenReturn(oldEmailString);
        when(request.getParameter("password")).thenReturn(oldPasswordString);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signup(anyString(), anyString(), anyString())).thenReturn(ResponsesCode.WRONG_LOGIN);

        signupServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).signup(anyString(), anyString(), anyString());
        assertTrue(stringWriter.toString().contains(jsonWRONG_LOGIN));
    }
}