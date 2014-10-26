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

            checkGameState();
        }
    }

    private void checkGameState() {
        if (checkLines() || checkRows() || checkPriDiagonals() || checkAddDiagonals() || isFull()) {
            isFinished = true;
        }
    }

    private boolean isChosenWinner(int sum) {
        switch (sum) {
            case GameUser.O * SIZE:
                winner = GameUser.O;
                break;

            case GameUser.X * SIZE:
                winner = GameUser.X;
                break;

            default:
                return false;
        }
        return true;
    }

    public boolean isFinished() {
        return isFinished;
    }

    private boolean checkLines() {
        for (int i = 0; i < COUNT; i += SIZE) {
            int sum = 0;
            for (int j = 0; j < SIZE; ++j) {
                sum += field[i + j];
            }
            if (isChosenWinner(sum)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < SIZE; ++i) {
            int sum = 0;
            for (int j = 0; j < COUNT; j += SIZE) {
                sum += field[i + j];
            }
            if (isChosenWinner(sum)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPriDiagonals() {
        int sum = 0;
        for (int i = 0; i < COUNT; i += SIZE + 1) {
            sum += field[i];
        }
        return isChosenWinner(sum);
    }

    private boolean checkAddDiagonals() {
        int sum = 0;
        for (int i = SIZE - 1; i < COUNT - 1; i += SIZE - 1) {
            sum += field[i];
        }
        return isChosenWinner(sum);
    }

    private boolean isFull() {
        for (int i = 0; i < COUNT; ++i) {
            if (field[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
