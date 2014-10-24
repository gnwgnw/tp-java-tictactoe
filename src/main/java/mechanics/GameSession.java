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

    private int whoseTurn;
    private int[] field = new int[9];

    private Map<String, GameUser> loginToGameUser = new HashMap<>();

    public GameSession(String loginFirst, String loginSecond) {
        Random random = new Random();
        int signFirst = (random.nextInt(1) == 0) ? GameUser.X : GameUser.O;
        int signSecond = changeSign(signFirst);

        first = new GameUser(loginFirst, signFirst);
        second = new GameUser(loginSecond, signSecond);

        loginToGameUser.put(loginFirst, first);
        loginToGameUser.put(loginSecond, second);

        whoseTurn = GameUser.X;
    }

    public UserGameState getUserGameState(String login) {
        GameUser myGameUser = loginToGameUser.get(login);
        GameUser enemyGameUser = (myGameUser == first) ? second : first;

        return new UserGameState(myGameUser, enemyGameUser, whoseTurn, field);
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
            checkGameState();
        }
    }

    private void checkGameState() {
//TODO
    }
}
