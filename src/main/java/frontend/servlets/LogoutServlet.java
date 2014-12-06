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
public class LogoutServlet extends HttpServlet implements PageUrlServlet {

    private static final String pageURL = "/logout";
    private final AccountService accountService;

    public LogoutServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountService.logout(request.getSession().getId());

        ResponseHelper responseHelper = new ResponseHelper();

        responseHelper.setStatus(ResponsesCode.OK);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().println(responseHelper.toJsonString());
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }
}
