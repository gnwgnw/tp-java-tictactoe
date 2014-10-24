package servlets;

import base.AccountService;
import base.GameMechanics;
import base.PageUrlServlet;
import base.WebSocketService;
import frontend.CustomWebSocketCreator;
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

    private GameMechanics gameMechanics;
    private AccountService accountService;
    private WebSocketService webSocketService;

    public WebSocketGameServlet(GameMechanics gameMechanics, AccountService accountService, WebSocketService webSocketService) {
        this.gameMechanics = gameMechanics;
        this.accountService = accountService;
        this.webSocketService = webSocketService;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(IDLE_TIME);
        factory.setCreator(new CustomWebSocketCreator(gameMechanics, accountService, webSocketService));
    }

    @Override
    public String getPageUrl() {
        return pageUrl;
    }
}
