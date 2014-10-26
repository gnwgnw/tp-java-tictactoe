package tests;

import base.AccountService;
import base.ResponsesCode;
import org.junit.Test;
import servlets.SignInServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SignInServletTest {
    final AccountService accountService = mock(AccountService.class);
    //TODO possible?!
    final SignInServlet signInServlet = new SignInServlet(accountService);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("login")).thenReturn("defaultUser1");
        when(request.getParameter("password")).thenReturn("123");
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("session");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(accountService.signIn("defaultUser1", "123", "session")).thenReturn(ResponsesCode.OK);

        signInServlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("The user defaultUser1 signin"));
    }
}