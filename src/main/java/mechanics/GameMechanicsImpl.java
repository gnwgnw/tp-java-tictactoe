package mechanics;

import base.GameMechanics;
import base.WebSocketService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class GameMechanicsImpl implements GameMechanics {
    private final WebSocketService webSocketService;

    private Set<GameSession> allGameSessions = new HashSet<>();
    private Map<String, GameSession> loginToGameSession = new HashMap<>();

    private String waiter;

    public GameMechanicsImpl(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void run() {
//TODO
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
    public void doTurn(String login, long position) {
//TODO
    }

    private void startGame(String waiter, String login) {
        GameSession gameSession = new GameSession(waiter, login);

        allGameSessions.add(gameSession);
        loginToGameSession.put(waiter, gameSession);
        loginToGameSession.put(login, gameSession);

        webSocketService.notifyStartGame(gameSession.getUserGameState(waiter));
        webSocketService.notifyStartGame(gameSession.getUserGameState(login));
    }
}
