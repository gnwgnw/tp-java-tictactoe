package base;

import frontend.PlayerWebSocket;
import mechanics.UserGameState;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public interface WebSocketService {
    void notifyStartGame(UserGameState userGameState);

    void notifyGameOver(UserGameState userGameState);

    void notifyUpdateGameState(UserGameState userGameState);

    void addSocket(PlayerWebSocket playerWebSocket);

    void removeSocket(PlayerWebSocket playerWebSocket);
}
