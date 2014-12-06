package frontend.servlets;

import accounting.AccountService;
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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timeString = request.getParameter("shutdown");
        if (timeString != null) {
            try {
                int timeMS = Integer.valueOf(timeString);
                stopServer(timeMS);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("status", "run");
        pageVariables.put("signup", accountService.getCountSignupUsers());
        pageVariables.put("signin", accountService.getCountActiveUsers());

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.getPage("admin.tml", pageVariables));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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
