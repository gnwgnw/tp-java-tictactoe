package message;

import mechanics.GameMechanics;
import mechanics.messages.MessageCloseGameSession;
import mechanics.messages.MessageDoTurn;
import mechanics.messages.MessageStartGame;
import messageSystem.MessageSystem;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class MessageToGameMechanicsTest {

    private final MessageSystem messageSystem = new MessageSystem();
    private final GameMechanics gameMechanics = mock(GameMechanics.class);

    private final String login = "test";
    private final int position = 5;

    @Test
    public void testMessageCloseGameSession() throws Exception {
        messageSystem.addService(gameMechanics);

        MessageCloseGameSession message = new MessageCloseGameSession(null, gameMechanics.getAddress(), login);
        messageSystem.sendMessage(message);

        messageSystem.execForAbonent(gameMechanics);
        verify(gameMechanics, atLeastOnce()).closeGameSession(login);
    }

    @Test
    public void testMessageDoTurn() throws Exception {
        messageSystem.addService(gameMechanics);

        MessageDoTurn message = new MessageDoTurn(null, gameMechanics.getAddress(), login, position);
        messageSystem.sendMessage(message);

        messageSystem.execForAbonent(gameMechanics);
        verify(gameMechanics, atLeastOnce()).doTurn(login, position);
    }

    @Test
    public void testMessageStartGame() throws Exception {
        messageSystem.addService(gameMechanics);

        MessageStartGame message = new MessageStartGame(null, gameMechanics.getAddress(), login);
        messageSystem.sendMessage(message);

        messageSystem.execForAbonent(gameMechanics);
        verify(gameMechanics, atLeastOnce()).waitForEnemy(login);
    }
}