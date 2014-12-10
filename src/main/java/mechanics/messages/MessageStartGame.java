package mechanics.messages;

import mechanics.GameMechanics;
import messageSystem.Address;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public class MessageStartGame extends MessageToGameMechanics {

    private final String login;

    public MessageStartGame(Address from, Address to, String login) {
        super(from, to);
        this.login = login;
    }

    @Override
    public void exec(GameMechanics gameMechanics) {
        gameMechanics.waitForEnemy(login);
    }
}
