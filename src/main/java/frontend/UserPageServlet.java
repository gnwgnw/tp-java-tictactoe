package frontend;

import main.AccountService;
import main.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author s.titaevskiy on 26.09.14.
 */
public class UserPageServlet extends HttpServlet {
    private final AccountService accountService;

    public UserPageServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        UserProfile userProfile = accountService.getCurrentUserProfile(httpSession.toString());

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("login", userProfile.getLogin());
        pageVariables.put("email", userProfile.getEmail());

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.getPage("userpage.tml", pageVariables));
    }
}
