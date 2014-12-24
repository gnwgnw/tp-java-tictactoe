package frontend.websocket;

import frontend.websocket.game.PlayerWebSocket;
import mechanics.UserGameState;
import mechanics.messages.MessageCloseGameSession;
import mechanics.messages.MessageDoTurn;
import mechanics.messages.MessageStartGame;
import messageSystem.Address;
import messageSystem.Message;
import messageSystem.MessageSystem;
import utils.TimeHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class WebSocketServiceImpl implements WebSocketService {

    private static final int STEP_TIME = 50;

    private final Address address = new Address();
    private final MessageSystem messageSystem;

    private final Map<String, PlayerWebSocket> loginToSocket = new ConcurrentHashMap<>();

    public WebSocketServiceImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        messageSystem.addService(this);
        messageSystem.getAddressService().registerWebSocketService(this);
    }

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
    public void startGame(String login) {
        final Message message = new MessageStartGame(address, messageSystem.getAddressService()
                .getGameMechanicsAddress(), login);
        messageSystem.sendMessage(message);
    }

    @Override
    public void doTurn(String login, int position) {
        final Message message = new MessageDoTurn(address, messageSystem.getAddressService()
                .getGameMechanicsAddress(), login, position);
        messageSystem.sendMessage(message);
    }

    @Override
    public void closeGameSession(String login) {
        final Message message = new MessageCloseGameSession(address, messageSystem.getAddressService()
                .getGameMechanicsAddress(), login);
        messageSystem.sendMessage(message);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            TimeHelper.sleep(STEP_TIME);
        }
    }
}
