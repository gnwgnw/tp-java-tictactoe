package tests;

import org.junit.Test;
import servlets.ScoreBoardServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

public class ScoreBoardServletTest {
    final ScoreBoardServlet scoreBoardServlet = new ScoreBoardServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void testDoGet() throws Exception {
        //TODO test
    }
}