package servlets;

import base.AccountService;
import base.PageUrlServlet;
import base.UserProfile;
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
public class UserPageServlet extends HttpServlet implements PageUrlServlet {
    public static final String pageURL = "/userpage";
    private static final String redirectUrl = "/#login";

    private final AccountService accountService;

    public UserPageServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfile userProfile = accountService.getUserProfile(request.getSession().getId());
        Map<String, Object> pageVariables = new HashMap<>();

        if (userProfile == null) {
            response.sendRedirect(redirectUrl);
            return;
        }

        pageVariables.put("login", userProfile.getLogin());
        pageVariables.put("email", userProfile.getEmail());

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.getPage("userpage.tml", pageVariables));
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }
}
