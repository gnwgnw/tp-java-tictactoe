package servlets;

import base.AccountService;
import base.PageUrlServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author s.titaevskiy on 14.09.14.
 */
public class SignOutServlet extends HttpServlet implements PageUrlServlet {
    private static final String pageURL = "/signout";
    private final AccountService accountService;

    public SignOutServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountService.signOut(request.getSession().toString());

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Done");
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }
}
