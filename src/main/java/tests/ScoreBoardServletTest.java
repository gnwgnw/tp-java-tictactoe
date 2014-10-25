package tests;

import org.junit.Test;
import servlets.ScoreBoardServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ScoreBoardServletTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    final ScoreBoardServlet scoreBoardServlet = new ScoreBoardServlet();

    @Test
    public void testDoGet() throws Exception {
        //TODO test
    }
}