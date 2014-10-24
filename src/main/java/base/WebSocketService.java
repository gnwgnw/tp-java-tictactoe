package base;

import frontend.GameWebSocket;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public interface WebSocketService {
    void notifyStartGame(UserGameState userGameState);

    void notifyGameOver(UserGameState userGameState);

    void notifyUpdateGameState(UserGameState userGameState);

    void addSocket(GameWebSocket gameWebSocket);

    void removeSocket(GameWebSocket gameWebSocket);
}
