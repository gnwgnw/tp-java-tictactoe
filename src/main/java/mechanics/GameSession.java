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

    private int winner;

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

        return new UserGameState(myGameUser, enemyGameUser, whoseTurn, field, winner);
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
        int sum;
        for (int i = 0; i < 9; i += 3) {
            sum = 0;
            for (int j = 0; j < 3; ++j) {
                sum += field[i + j];
            }
            if (chooseWinner(sum) > 0) {
                return;
            }
        }

        for (int i = 0; i < 3; ++i) {
            sum = 0;
            for (int j = 0; j < 9; j += 3) {
                sum += field[i + j];
            }
            if (chooseWinner(sum) > 0) {
                return;
            }
        }

        sum = 0;
        for (int i = 0; i < 9; i += 4) {
            sum += field[i];
        }
        if (chooseWinner(sum) > 0) {
            return;
        }

        sum = 0;
        for (int i = 2; i < 8; i += 2) {
            sum += field[i];
        }
        chooseWinner(sum);
    }

    private int chooseWinner(int sum) {
        switch (sum) {
            case GameUser.O * 3:
                winner = GameUser.O;
                return GameUser.O;

            case GameUser.X * 3:
                winner = GameUser.X;
                return GameUser.X;

            default:
                return 0;
        }
    }

    public boolean isFinished() {
        return winner > 0;
    }

    public String getFirstLogin() {
        return first.getLogin();
    }

    public String getSecondLogin() {
        return second.getLogin();
    }
}
