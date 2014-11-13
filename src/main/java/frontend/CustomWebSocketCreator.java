package frontend;

import base.AccountService;
import base.GameMechanics;
import base.UserDataSet;
import base.UserDataSet;
import base.WebSocketService;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by titaevskiy.s on 23.10.14
 */
public class CustomWebSocketCreator implements WebSocketCreator {
    private final GameMechanics gameMechanics;
    private final AccountService accountService;
    private final WebSocketService webSocketService;

    public CustomWebSocketCreator(GameMechanics gameMechanics, AccountService accountService, WebSocketService webSocketService) {
        this.gameMechanics = gameMechanics;
        this.accountService = accountService;
        this.webSocketService = webSocketService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        String sessionId = req.getHttpServletRequest().getSession().getId();
        UserDataSet userDataSet = accountService.getUserDataSet(sessionId);
        return new GameWebSocket(userDataSet.getLogin(), gameMechanics, webSocketService);
    }
}
