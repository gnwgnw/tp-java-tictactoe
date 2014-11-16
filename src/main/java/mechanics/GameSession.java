package mechanics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class GameSession {
    private final static int SIZE = 3;
    private final static int COUNT = SIZE * SIZE;

    private final int[] field = new int[SIZE * SIZE];
    private final GameUser first;
    private final GameUser second;
    private final Map<String, GameUser> loginToGameUser = new HashMap<>();

    private int whoseTurn;
    private int winner;
    private boolean isFinished = false;

    private final GameState gameState = new GameState();

    public GameSession(String loginFirst, String loginSecond) {
        Random random = new Random();
        int signFirst = (random.nextInt(100) > 50) ? GameUser.X : GameUser.O;
        int signSecond = changeSign(signFirst);

        first = new GameUser(loginFirst, signFirst);
        second = new GameUser(loginSecond, signSecond);

        loginToGameUser.put(loginFirst, first);
        loginToGameUser.put(loginSecond, second);

        whoseTurn = GameUser.X;
    }

    public String getFirstLogin() {
        return first.getLogin();
    }

    public String getSecondLogin() {
        return second.getLogin();
    }

    public UserGameState getUserGameState(String login) {
        GameUser myGameUser = loginToGameUser.get(login);
        GameUser enemyGameUser = (myGameUser == first) ? second : first;

        return new UserGameState(myGameUser, enemyGameUser, whoseTurn, field, winner, isFinished);
    }

    private int changeSign(int sign) {
        return (GameUser.X + GameUser.O) - sign;
    }

    public void doTurn(String login, int position) {
        GameUser gameUser = loginToGameUser.get(login);
        int sign = gameUser.getSign();

        if (sign == whoseTurn && field[position] == 0) {
            field[position] = sign;
            whoseTurn = changeSign(sign);

            checkGameState();//TODO
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void closeGameSession() {
        isFinished = true;
    }

    private void checkGameState() {
        gameState.setWinner(winner);                    //TODO may be by reference?
        if (gameState.checkLines(field) ||
            gameState.checkRows(field) ||
            gameState.checkPriDiagonals(field) ||
            gameState.checkAddDiagonals(field) ||
            gameState.isFull(field)) {
            isFinished = true;
        }
    }
}
