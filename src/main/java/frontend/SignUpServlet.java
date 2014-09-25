package frontend;

import main.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String responseAnswer;
        switch (accountService.signUp(login, email, password)) {
            case OK:
                responseAnswer = "The user " + login + " created";
                break;
            case ALREADY_EXISTS:
                responseAnswer = "The user " + login + " already exists";
                break;
            default:
                responseAnswer = "Unknown error";
                break;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(responseAnswer);
    }
}
