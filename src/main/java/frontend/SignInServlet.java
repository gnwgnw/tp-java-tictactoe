package frontend;

import main.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author s.titaevskiy on 14.09.14.
 */
public class SignInServlet extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession httpSession = request.getSession();
        String responseAnswer;
        switch (accountService.signIn(login, password, httpSession)) {
            case OK:
                responseAnswer = "The user " + login + " signin";
                break;
            case WRONG_SIGNIN:
                responseAnswer = "Enter correct login and password";
                break;
            default:
                responseAnswer = "Unknown error";
                break;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(responseAnswer);
    }
}
