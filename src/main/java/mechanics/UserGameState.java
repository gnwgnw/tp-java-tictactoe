package mechanics;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class UserGameState {
    private final GameUser myGameUser;
    private final GameUser enemyGameUser;
    private final int whoseTurn;
    private final int[] field;

    public UserGameState(GameUser myGameUser, GameUser enemyGameUser, int whoseTurn, int[] field) {
        this.myGameUser = myGameUser;
        this.enemyGameUser = enemyGameUser;
        this.whoseTurn = whoseTurn;
        this.field = field;
    }

    public GameUser getMyGameUser() {
        return myGameUser;
    }

    public GameUser getEnemyGameUser() {
        return enemyGameUser;
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public int[] getField() {
        return field;
    }
}
