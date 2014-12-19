package tests.message;

import mechanics.messages.*;
import messageSystem.Abonent;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class MessageToGameMechanicsTest {

    private final Abonent abonent = mock(Abonent.class);

    @Test
    public void testMessageCloseGameSession() throws Exception {
        MessageCloseGameSession messageCloseGameSession = mock(MessageCloseGameSession.class);
        messageCloseGameSession.exec(abonent);
        verify(messageCloseGameSession, atLeastOnce()).exec(abonent);
    }

    @Test
    public void testMessageDoTurn() throws Exception {
        MessageDoTurn messageDoTurn = mock(MessageDoTurn.class);
        messageDoTurn.exec(abonent);
        verify(messageDoTurn, atLeastOnce()).exec(abonent);
    }

    @Test
    public void testMessageStartGame() throws Exception {
        MessageStartGame messageStartGame = mock(MessageStartGame.class);
        messageStartGame.exec(abonent);
        verify(messageStartGame, atLeastOnce()).exec(abonent);
    }
}