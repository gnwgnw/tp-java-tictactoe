package frontend.websocket.game;

import com.google.gson.JsonParser;
import frontend.websocket.WebSocketService;
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
    private final WebSocketService webSocketService;
    private Session session;

    public PlayerWebSocket(String myLogin, WebSocketService webSocketService) {
        this.myLogin = myLogin;
        this.webSocketService = webSocketService;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
        webSocketService.addSocket(this);
        webSocketService.startGame(myLogin);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        final int position = new JsonParser().parse(data).getAsInt();
        webSocketService.doTurn(myLogin, position);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        webSocketService.closeGameSession(myLogin);
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
