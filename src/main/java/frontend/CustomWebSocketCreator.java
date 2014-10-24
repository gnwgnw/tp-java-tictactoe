package frontend;

import base.AccountService;
import base.GameMechanics;
import base.UserProfile;
import base.WebSocketService;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by titaevskiy.s on 23.10.14
 */
public class CustomWebSocketCreator implements WebSocketCreator {
    private GameMechanics gameMechanics;
    private AccountService accountService;
    private WebSocketService webSocketService;

    public CustomWebSocketCreator(GameMechanics gameMechanics, AccountService accountService, WebSocketService webSocketService) {
        this.gameMechanics = gameMechanics;
        this.accountService = accountService;
        this.webSocketService = webSocketService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        String sessionId = req.getHttpServletRequest().getSession().getId();
        UserProfile userProfile = accountService.getUserProfile(sessionId);
        return new GameWebSocket(userProfile.getLogin(), gameMechanics, webSocketService);
    }
}
