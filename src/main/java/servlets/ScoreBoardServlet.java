package servlets;

import base.PageUrlServlet;
import utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author s.titaevskiy on 02.10.14.
 */
public class ScoreBoardServlet extends HttpServlet implements PageUrlServlet {
    private static final String pageURL = "/scores";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().println(PageGenerator.getPage("scoreboard.txt", null));
    }

    @Override
    public String getPageUrl() {
        return pageURL;
    }
}
