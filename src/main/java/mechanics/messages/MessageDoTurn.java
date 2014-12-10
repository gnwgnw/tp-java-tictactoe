package mechanics.messages;

import mechanics.GameMechanics;
import messageSystem.Address;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public class MessageDoTurn extends MessageToGameMechanics {

    private final String login;
    private final int position;

    public MessageDoTurn(Address from, Address to, String login, int position) {
        super(from, to);
        this.login = login;
        this.position = position;
    }

    @Override
    public void exec(GameMechanics gameMechanics) {
        gameMechanics.doTurn(login, position);
    }
}
