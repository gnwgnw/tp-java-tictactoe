package mechanics.messages;

import mechanics.GameMechanics;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.Message;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public abstract class MessageToGameMechanics extends Message {
    public MessageToGameMechanics(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) {
        if (abonent instanceof GameMechanics) {
            exec((GameMechanics) abonent);
        }
    }

    public abstract void exec(GameMechanics gameMechanics);
}
