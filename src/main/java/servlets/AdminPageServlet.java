package servlets;

import base.AccountService;
import base.PageUrlServlet;
import utils.PageGenerator;
import utils.TimeHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author s.titaevskiy on 26.09.14.
 */
public class AdminPageServlet extends HttpServlet implements PageUrlServlet {
    private static final String pageURL = "/admin";
    private final AccountService accountService;

    public AdminPageServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timeString = request.getParameter("shutdown");
        if (timeString != null) {
            int timeMS = Integer.valueOf(timeString);
            stopServer(timeMS);
        }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("status", "run");
        pageVariables.put("signup", accountService.countSignUp());
        pageVariables.put("signin", accountService.countSignIn());

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.getPage("admin.tml", pageVariables));
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }

    private void stopServer(int timeMS) {
        System.out.print("Server will be down after: " + timeMS + " ms");
        TimeHelper.sleep(timeMS);
        System.out.print("\nShutdown");
        System.exit(0);
    }
}
