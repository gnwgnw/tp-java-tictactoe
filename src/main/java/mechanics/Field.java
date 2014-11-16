package mechanics;

/**
 * Created by titaevskiy.s on 16.11.14
 */
public class Field {
    private final static int SIZE = 3;//TODO
    private final static int COUNT = SIZE * SIZE;
    private final int[] field = new int[COUNT];
    private int winner;

    public TurnStatus doTurn(int position, int sign) {
        if (position >= 0 && position < COUNT && field[position] == 0) {
            field[position] = sign;
            return TurnStatus.OK;
        }
        return TurnStatus.ERROR;
    }

    public boolean isFinished() {
        return onLines() && onColumns() && onPriDiagonals() && onAddDiagonals() && isFull();
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

    private boolean onLines() {
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

    private boolean onColumns() {
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

    private boolean onPriDiagonals() {
        int sum = 0;
        for (int i = 0; i < COUNT; i += SIZE + 1) {
            sum += field[i];
        }
        return isChosenWinner(sum);
    }

    private boolean onAddDiagonals() {
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

    public int[] getField() {
        return field;
    }

    public int getWinner() {
        return winner;
    }

    public static enum TurnStatus {
        OK,
        ERROR
    }
}
