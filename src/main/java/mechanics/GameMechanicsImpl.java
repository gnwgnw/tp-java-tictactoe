package mechanics;

import frontend.websocket.WebSocketService;
import utils.TimeHelper;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class GameMechanicsImpl implements GameMechanics {
    private static final int STEP_TIME = 1000;

    private final WebSocketService webSocketService;

    private final Set<GameSession> allGameSessions = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<String, GameSession> loginToGameSession = new ConcurrentHashMap<>();
    private String waiter;

    public GameMechanicsImpl(WebSocketService webSocketService) { this.webSocketService = webSocketService;}

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
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
        loginToGameSession.get(login).closeGameSession();
    }

    private void startGame(String waiter, String login) {
        GameSession gameSession = new GameSession(waiter, login);

        allGameSessions.add(gameSession);
        loginToGameSession.put(waiter, gameSession);
        loginToGameSession.put(login, gameSession);

        webSocketService.notifyStartGame(gameSession.getUserGameState(waiter));
        webSocketService.notifyStartGame(gameSession.getUserGameState(login));
    }

    private void gameOver(UserGameState first, UserGameState second) {
        webSocketService.notifyGameOver(first);
        webSocketService.notifyGameOver(second);
    }

    private void gameUpdate(UserGameState first, UserGameState second) {
        webSocketService.notifyUpdateGameState(first);
        webSocketService.notifyUpdateGameState(second);
    }

    private void    clearGameSessions() {
        allGameSessions.stream().filter(GameSession::isFinished).forEach(gameSession -> {
            loginToGameSession.remove(gameSession.getFirstLogin());
            loginToGameSession.remove(gameSession.getSecondLogin());
            allGameSessions.remove(gameSession);
        });
    }
}
