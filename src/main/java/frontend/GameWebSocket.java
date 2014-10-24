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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        setSession(session);
        webSocketService.addSocket(this);
        gameMechanics.waitForEnemy(myLogin);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(data);
            gameMechanics.doTurn(myLogin, (Long) jsonObject.get("position"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        webSocketService.removeSocket(this);
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getMyLogin() {
        return myLogin;
    }

    public void startGame(UserGameState userGameState) {
//TODO
    }

    public void gameOver(UserGameState userGameState) {
//TODO
    }

    public void updateGameState(UserGameState userGameState) {
//TODO
    }
}
