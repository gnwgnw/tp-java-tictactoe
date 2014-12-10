package mechanics.messages;

import mechanics.GameMechanics;
import messageSystem.Address;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public class MessageCloseGameSession extends MessageToGameMechanics {

    private final String login;

    public MessageCloseGameSession(Address from, Address to, String login) {
        super(from, to);
        this.login = login;
    }

    @Override
    public void exec(GameMechanics gameMechanics) {
        gameMechanics.closeGameSession(login);
    }
}
