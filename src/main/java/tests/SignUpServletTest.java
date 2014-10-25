package tests;

import base.AccountService;
import base.ResponsesCode;
import org.junit.Test;
import servlets.SignUpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SignUpServletTest {
    final AccountService accountService = mock(AccountService.class);
    //TODO possible?!
    final SignUpServlet signUpServlet = new SignUpServlet(accountService);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void testDoPostOK() throws Exception {
        when(request.getParameter("login")).thenReturn("testUser");
        when(request.getParameter("email")).thenReturn("testUser@mail.ru");
        when(request.getParameter("password")).thenReturn("123");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signUp("testUser", "testUser@mail.ru", "123")).thenReturn(ResponsesCode.OK);

        signUpServlet.doPost(request, response);
//        assertEquals(stringWriter.toString(), "The user testUser created");
        assertTrue(stringWriter.toString().contains("The user testUser created"));
    }

    @Test
    public void testDoPostAlreadyExist() throws Exception {
        when(request.getParameter("login")).thenReturn("defaultUser1");
        when(request.getParameter("email")).thenReturn("defaultUser1@mail.ru");
        when(request.getParameter("password")).thenReturn("123");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signUp("defaultUser1", "defaultUser1@mail.ru", "123")).thenReturn(ResponsesCode.ALREADY_EXISTS);

        signUpServlet.doPost(request, response);
//        assertEquals(stringWriter.toString(), "The user testUser created");
        assertTrue(stringWriter.toString().contains("The user defaultUser1 already exists"));
    }
}