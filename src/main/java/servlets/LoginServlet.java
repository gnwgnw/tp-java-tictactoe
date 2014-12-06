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
public class LoginServlet extends HttpServlet implements PageUrlServlet {
    private static final String pageURL = "/login";
    private final AccountService accountService;

    public LoginServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String responseAnswer;
        switch (accountService.login(login, password, request.getSession().getId())) {
            case OK:
                responseAnswer = "The user " + login + " signin";
                break;
            case WRONG_LOGIN:
                responseAnswer = "Enter correct login and password";
                break;
            default:
                responseAnswer = "Unknown error";
                break;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(responseAnswer);
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }
}
