package frontend.servlets;

import accounting.AccountService;
import frontend.websocket.game.CustomPlayerWebSocketCreator;
import frontend.websocket.WebSocketService;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

/**
 * Created by titaevskiy.s on 23.10.14
 */
@WebServlet
public class WebSocketGameServlet extends WebSocketServlet implements PageUrlServlet {

    private static final String pageUrl = "/gameplay";
    private static final int IDLE_TIME = 60 * 60 * 1000;    //TODO: debug

    private final AccountService accountService;
    private final WebSocketService webSocketService;

    public WebSocketGameServlet(AccountService accountService, WebSocketService webSocketService) {
        this.accountService = accountService;
        this.webSocketService = webSocketService;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(IDLE_TIME);
        factory.setCreator(new CustomPlayerWebSocketCreator(accountService, webSocketService));
    }

    @Override
    public String getPageUrl() {
        return pageUrl;
    }
}
