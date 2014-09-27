package frontend;

import main.AccountService;
import templater.PageGenerator;

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
public class AdminPageServlet extends HttpServlet {
    private final AccountService accountService;

    public AdminPageServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String timeString = request.getParameter("shutdown");
        if (timeString != null) {
            int timeMS = Integer.valueOf(timeString);
            System.out.print("Server will be down after: " + timeMS + " ms");

            try {
                Thread.sleep(timeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("\nShutdown");
            System.exit(0);
        }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("status", "run");
        pageVariables.put("signup", accountService.countSignUp());
        pageVariables.put("signin", accountService.countSignIn());

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.getPage("admin.tml", pageVariables));
    }
}
