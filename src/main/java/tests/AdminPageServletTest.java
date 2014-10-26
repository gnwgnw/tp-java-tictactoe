package tests;

import base.AccountService;
import org.junit.Test;
import servlets.AdminPageServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

public class AdminPageServletTest {
    final AccountService accountService = mock(AccountService.class);
    //TODO possible?!
    final AdminPageServlet adminPageServlet = new AdminPageServlet(accountService);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void testDoGet() throws Exception {
        //TODO test
    }
}