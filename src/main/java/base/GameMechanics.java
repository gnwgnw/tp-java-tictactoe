package base;

/**
 * Created by titaevskiy.s on 23.10.14
 */
public interface GameMechanics {
    void run();

    void waitForEnemy(String login);

    void doTurn(String login, long position);
}
