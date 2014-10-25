package tests;

import base.AccountService;
import org.junit.Test;
import servlets.AdminPageServlet;
import servlets.SignOutServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AdminPageServletTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    final AccountService accountService = mock(AccountService.class);
    //TODO possible?!
    final AdminPageServlet adminPageServlet = new AdminPageServlet(accountService);

    @Test
    public void testDoGet() throws Exception {
        //TODO test
    }
}