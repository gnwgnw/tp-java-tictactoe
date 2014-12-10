package frontend.websocket;

import mechanics.UserGameState;
import messageSystem.Address;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class WebSocketServiceImpl implements WebSocketService {

    private final Address address = new Address();
    private final Map<String, PlayerWebSocket> loginToSocket = new ConcurrentHashMap<>();

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
    public void addSocket(PlayerWebSocket playerWebSocket) {
        loginToSocket.put(playerWebSocket.getMyLogin(), playerWebSocket);
    }

    @Override
    public void removeSocket(PlayerWebSocket playerWebSocket) {
        loginToSocket.remove(playerWebSocket.getMyLogin());
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
