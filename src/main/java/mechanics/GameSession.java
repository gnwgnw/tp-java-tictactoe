package mechanics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class GameSession {

    private final GameUser first;
    private final GameUser second;
    private final Map<String, GameUser> loginToGameUser = new HashMap<>();

    private final Field field = new Field();
    private int whoseTurn;
    private int winner;
    private boolean isFinished = false;

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

        return new UserGameState(myGameUser, enemyGameUser, whoseTurn, field.getField(), winner, isFinished);
    }

    private int changeSign(int sign) {
        return (GameUser.X + GameUser.O) - sign;
    }

    public void doTurn(String login, int position) {
        GameUser gameUser = loginToGameUser.get(login);
        int sign = gameUser.getSign();

        if (sign == whoseTurn && field.doTurn(position, sign) == Field.TurnStatus.OK) {
            whoseTurn = changeSign(sign);
            checkGameState();
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void closeGameSession() {
        isFinished = true;
    }

    private void checkGameState() {
        if (field.isFinished()) {
            isFinished = true;
            winner = field.getWinner();
        }
    }
}
