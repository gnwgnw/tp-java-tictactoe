package tests.account;

import accounting.AccountService;
import frontend.servlets.AdminPageServlet;
import org.junit.Test;
import utils.PageGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AdminPageServletTest {
    final AccountService accountService = mock(AccountService.class);
    final AdminPageServlet adminPageServlet = new AdminPageServlet(accountService);
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);

    int signUpCount = 2;
    int signInCount = 1;

    @Test
    public void testDoGetPage() throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("status", "run");
        pageVariables.put("signup", signUpCount);
        pageVariables.put("signin", signInCount);

        when(request.getParameter("Shutdown")).thenReturn(null);
        when(accountService.getCountActiveUsers()).thenReturn(signInCount);
        when(accountService.getCountSignupUsers()).thenReturn((long) signUpCount);
        when(response.getWriter()).thenReturn(printWriter);

        adminPageServlet.doGet(request, response);

        assertTrue(stringWriter.toString().contains(PageGenerator.getPage("admin.tml", pageVariables)));
    }
}