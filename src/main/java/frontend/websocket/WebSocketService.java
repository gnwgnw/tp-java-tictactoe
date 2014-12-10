package frontend.websocket;

import mechanics.UserGameState;
import messageSystem.Abonent;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public interface WebSocketService extends Abonent {
    void notifyStartGame(UserGameState userGameState);

    void notifyGameOver(UserGameState userGameState);

    void notifyUpdateGameState(UserGameState userGameState);

    void addSocket(PlayerWebSocket playerWebSocket);

    void removeSocket(PlayerWebSocket playerWebSocket);
}
