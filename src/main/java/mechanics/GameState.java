package mechanics;

/**
 * Created by marina on 14.11.14.
 */
public class GameState {

    private final static int SIZE = 3;              //TODO hardcode
    private final static int COUNT = SIZE * SIZE;   //TODO hardcode
    private int winner = 0;

    public void setWinner(int win) {
        winner = win;
    }

    public int getWinner() {
        return winner;
    }

    public boolean isChosenWinner(int sum) {
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

    public boolean checkLines(int[] field) {
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

    public boolean checkRows(int[] field) {
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

    public boolean checkPriDiagonals(int[] field) {
        int sum = 0;
        for (int i = 0; i < COUNT; i += SIZE + 1) {
            sum += field[i];
        }
        return isChosenWinner(sum);
    }

    public boolean checkAddDiagonals(int[] field) {
        int sum = 0;
        for (int i = SIZE - 1; i < COUNT - 1; i += SIZE - 1) {
            sum += field[i];
        }
        return isChosenWinner(sum);
    }

    public boolean isFull(int[] field) {
        for (int i = 0; i < COUNT; ++i) {
            if (field[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
