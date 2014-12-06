package frontend.websocket;

import com.google.gson.JsonParser;
import mechanics.GameMechanics;
import mechanics.UserGameState;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

/**
 * Created by titaevskiy.s on 23.10.14
 */
@WebSocket
public class PlayerWebSocket {
    private final String myLogin;
    private final GameMechanics gameMechanics;
    private final WebSocketService webSocketService;
    private Session session;

    public PlayerWebSocket(String myLogin, GameMechanics gameMechanics, WebSocketService webSocketService) {
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
        final int position = new JsonParser().parse(data).getAsInt();
        gameMechanics.doTurn(myLogin, position);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        gameMechanics.closeGameSession(myLogin); //TODO lose game then out
        webSocketService.removeSocket(this);
    }

    public String getMyLogin() {
        return myLogin;
    }

    public void startGame(UserGameState userGameState) {
        updateGameState(userGameState);
    }

    public void gameOver(UserGameState userGameState) {
        updateGameState(userGameState);
        session.close();
    }

    public void updateGameState(UserGameState userGameState) {
        try {
            session.getRemote().sendString(userGameState.toJsonString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
