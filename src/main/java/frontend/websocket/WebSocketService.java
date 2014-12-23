package frontend.websocket;

import frontend.websocket.game.PlayerWebSocket;
import mechanics.UserGameState;
import messageSystem.Abonent;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public interface WebSocketService extends Abonent, Runnable {
    void notifyStartGame(UserGameState userGameState);

    void notifyGameOver(UserGameState userGameState);

    void notifyUpdateGameState(UserGameState userGameState);

    void addSocket(PlayerWebSocket playerWebSocket);

    void removeSocket(PlayerWebSocket playerWebSocket);

    void startGame(String login);

    void doTurn(String login, int position);

    void closeGameSession(String login);
}
