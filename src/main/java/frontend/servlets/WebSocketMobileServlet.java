package frontend.servlets;

import frontend.websocket.mobile.CustomMobileWebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created by titaevskiy.s on 23.12.14
 */
public class WebSocketMobileServlet extends WebSocketServlet implements PageUrlServlet {

    private static final String pageUrl = "/mobile";
    private static final int IDLE_TIME = 60 * 60 * 1000;

    @Override
    public String getPageUrl() {
        return pageUrl;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(IDLE_TIME);
        factory.setCreator(new CustomMobileWebSocketCreator());
    }
}
