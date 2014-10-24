package service;

import base.UserGameState;
import base.WebSocketService;
import frontend.GameWebSocket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class WebSocketServiceImpl implements WebSocketService {
    //TODO: multi threads
    private Map<String, GameWebSocket> loginToSocket = new HashMap<>();

    @Override
    public void notifyStartGame(UserGameState userGameState) {
        loginToSocket.get(userGameState.getMyGameUser().getLogin()).startGame(userGameState);
    }

    @Override
    public void notifyGameOver(UserGameState userGameState) {
        loginToSocket.get(userGameState.getMyGameUser().getLogin()).gameOver(userGameState);
    }

    @Override
    public void notifyUpdateGameState(UserGameState userGameState) {
        loginToSocket.get(userGameState.getMyGameUser().getLogin()).updateGameState(userGameState);
    }

    @Override
    public void addSocket(GameWebSocket gameWebSocket) {
        loginToSocket.put(gameWebSocket.getMyLogin(), gameWebSocket);
    }

    @Override
    public void removeSocket(GameWebSocket gameWebSocket) {
        loginToSocket.remove(gameWebSocket.getMyLogin());
    }
}
