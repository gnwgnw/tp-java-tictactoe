package frontend.servlets;

import accounting.AccountService;
import frontend.ResponseHelper;
import frontend.ResponsesCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class SignupServlet extends HttpServlet implements PageUrlServlet {

    private static final String pageURL = "/signup";
    private final AccountService accountService;

    public SignupServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ResponsesCode status = accountService.signup(login, email, password);

        ResponseHelper responseHelper = new ResponseHelper();
        responseHelper.setStatus(status);

        switch (status) {//TODO
            case OK:
                break;

            case ALREADY_EXISTS:
                break;

            case BAD_INPUT:
                break;

            default:
                break;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().println(responseHelper.toJsonString());
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }
}
