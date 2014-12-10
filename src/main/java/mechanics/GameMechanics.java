package mechanics;

import messageSystem.Abonent;

/**
 * Created by titaevskiy.s on 23.10.14
 */
public interface GameMechanics extends Abonent, Runnable {
    void run();

    void waitForEnemy(String login);

    void doTurn(String login, int position);

    void closeGameSession(String login);
}
