package tests.account;

import frontend.servlets.ScoreBoardServlet;
import org.junit.Test;
import utils.PageGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ScoreBoardServletTest {
    final ScoreBoardServlet scoreBoardServlet = new ScoreBoardServlet();
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);

    @Test
    public void testDoGet() throws Exception {
        when(response.getWriter()).thenReturn(printWriter);

        scoreBoardServlet.doGet(request, response);

        assertTrue(stringWriter.toString().contains(PageGenerator.getPage("scoreboard.txt", null)));
    }
}