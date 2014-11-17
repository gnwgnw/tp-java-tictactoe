package frontend;

import base.GameMechanics;
import base.WebSocketService;
import mechanics.UserGameState;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Created by titaevskiy.s on 23.10.14
 */
@WebSocket
public class GameWebSocket {
    private final String myLogin;
    private final GameMechanics gameMechanics;
    private final WebSocketService webSocketService;
    private Session session;

    public GameWebSocket(String myLogin, GameMechanics gameMechanics, WebSocketService webSocketService) {
        this.myLogin = myLogin;
        this.gameMechanics = gameMechanics;
        this.webSocketService = webSocketService;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
        webSocketService.addSocket(this);
        gameMechanics.waitForEnemy(myLogin);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(data);
//TODO: move frontend constant to file
        final int position = (int) (long) jsonObject.get("position");
        gameMechanics.doTurn(myLogin, position);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        gameMechanics.closeGameSession(myLogin); //TODO ring
        webSocketService.removeSocket(this);
    }

    public String getMyLogin() {
        return myLogin;
    }

    public void startGame(UserGameState userGameState) {
//TODO
    }

    public void gameOver(UserGameState userGameState) {
        updateGameState(userGameState);
        session.close();
    }

    public void updateGameState(UserGameState userGameState) {
//TODO
    }
}
