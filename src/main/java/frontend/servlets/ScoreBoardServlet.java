package frontend.servlets;

import accounting.AccountService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author s.titaevskiy on 02.10.14.
 */
public class ScoreBoardServlet extends HttpServlet implements PageUrlServlet {

    private static final String pageURL = "/scores";
    private final AccountService accountService;

    public ScoreBoardServlet(AccountService accountService) {this.accountService = accountService;}

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List topUsers = accountService.getTopUsers();

        Gson gson = new Gson();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(topUsers));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }
}
