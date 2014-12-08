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
 * @author s.titaevskiy on 14.09.14.
 */
public class LoginServlet extends HttpServlet implements PageUrlServlet {

    private static final String pageURL = "/login";
    private final AccountService accountService;

    public LoginServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ResponsesCode status = accountService.login(login, password, request.getSession().getId());

        ResponseHelper responseHelper = new ResponseHelper();
        responseHelper.setStatus(status);

        switch (status) {//TODO
            case OK:
                responseHelper.addToResponse("user", accountService.getUserDataSet(request.getSession().getId()));
                break;

            case WRONG_LOGIN:
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
