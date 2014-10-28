package tests;

import base.AccountService;
import base.UserProfile;
import org.junit.Test;
import servlets.UserPageServlet;
import utils.PageGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserPageServletTest {
    final AccountService accountService = mock(AccountService.class);
    final UserPageServlet userPageServlet = new UserPageServlet(accountService);
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession httpSession = mock(HttpSession.class);
    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);

    String loginString = "defaultUser1";
    String emailString = "defaultUser1@mail.ru";
    String passwordString = "123";
    String sessionString = "session";

    final UserProfile userProfile = new UserProfile(loginString, emailString, passwordString);

    @Test
    public void testDoGet() throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("login", loginString);
        pageVariables.put("email", emailString);

        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionString);
        when(accountService.getUserProfile(anyString())).thenReturn(userProfile);
        when(response.getWriter()).thenReturn(printWriter);

        userPageServlet.doGet(request, response);

        assertTrue(stringWriter.toString().contains(PageGenerator.getPage("userpage.tml", pageVariables)));
    }
}