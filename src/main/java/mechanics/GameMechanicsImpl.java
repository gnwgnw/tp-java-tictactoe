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
    public void doTurn(String login, int position) {
        GameSession gameSession = loginToGameSession.get(login);
        gameSession.doTurn(login, position);

        UserGameState myGameState = gameSession.getUserGameState(login);
        UserGameState enemyGameState = gameSession.getUserGameState(myGameState.getEnemyGameUser().getLogin());
//TODO: check session state
        webSocketService.notifyUpdateGameState(myGameState);
        webSocketService.notifyUpdateGameState(enemyGameState);
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
