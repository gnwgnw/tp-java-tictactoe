package mechanics;

import frontend.websocket.messages.MessageGameOver;
import frontend.websocket.messages.MessageGameStarted;
import frontend.websocket.messages.MessageGameUpdated;
import messageSystem.Address;
import messageSystem.Message;
import messageSystem.MessageSystem;
import utils.TimeHelper;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class GameMechanicsImpl implements GameMechanics {

    private static final int STEP_TIME = 50;

    private final Address address = new Address();
    private final MessageSystem messageSystem;

    private final Set<GameSession> allGameSessions = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<String, GameSession> loginToGameSession = new ConcurrentHashMap<>();
    private String waiter;

    public GameMechanicsImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        messageSystem.addService(this);
        messageSystem.getAddressService().registerGameMechanics(this);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            clearGameSessions();
            TimeHelper.sleep(STEP_TIME);
        }
    }

    @Override
    public void waitForEnemy(String login) {
        if (waiter == null) {
            waiter = login;
        }
        else {
            startGame(waiter, login);
            waiter = null;
        }
    }

    @Override
    public void doTurn(String login, int position) {
        GameSession gameSession = loginToGameSession.get(login);
        gameSession.doTurn(login, position);

        UserGameState myGameState = gameSession.getUserGameState(login);
        UserGameState enemyGameState = gameSession.getUserGameState(myGameState.getEnemyGameUser().getLogin());

        if (gameSession.isFinished()) {
            gameOver(myGameState, enemyGameState);
        }
        else {
            gameUpdate(myGameState, enemyGameState);
        }
    }

    @Override
    public void closeGameSession(String login) {
        final GameSession gameSession = loginToGameSession.get(login);
        if (gameSession != null) {
            gameSession.closeGameSession();
        }
    }

    private void startGame(String waiter, String login) {
        GameSession gameSession = new GameSession(waiter, login);

        allGameSessions.add(gameSession);
        loginToGameSession.put(waiter, gameSession);
        loginToGameSession.put(login, gameSession);

        final Message message = new MessageGameStarted(address, messageSystem.getAddressService()
                .getWebSocketServiceAddress(), gameSession.getUserGameState(waiter), gameSession.getUserGameState(login));

        messageSystem.sendMessage(message);
    }

    private void gameOver(UserGameState first, UserGameState second) {
        final Message message = new MessageGameOver(address, messageSystem.getAddressService()
                .getWebSocketServiceAddress(), first, second);

        messageSystem.sendMessage(message);
    }

    private void gameUpdate(UserGameState first, UserGameState second) {
        final Message message = new MessageGameUpdated(address, messageSystem.getAddressService()
                .getWebSocketServiceAddress(), first, second);

        messageSystem.sendMessage(message);
    }

    private void clearGameSessions() {
        allGameSessions.stream().filter(GameSession::isFinished).forEach(gameSession -> {
            loginToGameSession.remove(gameSession.getFirstLogin());
            loginToGameSession.remove(gameSession.getSecondLogin());
            allGameSessions.remove(gameSession);
        });
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
