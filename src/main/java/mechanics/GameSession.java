package mechanics;

import base.UserGameState;

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
        int signSecond = (GameUser.X + GameUser.O) - signFirst;

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
}
