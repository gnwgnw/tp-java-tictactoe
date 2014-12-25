package message;

import mechanics.GameMechanics;
import mechanics.GameMechanicsImpl;
import mechanics.messages.MessageCloseGameSession;
import mechanics.messages.MessageDoTurn;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.Message;
import messageSystem.MessageSystem;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class MessageSystemTest {

    private final Abonent abonent = mock(Abonent.class);
    private final Message message = mock(Message.class);
    private final Address address = mock(Address.class);

    private final MessageSystem messageSystem = new MessageSystem();

    private final GameMechanics gameMechanics1 = mock(GameMechanicsImpl.class);
    private final GameMechanics gameMechanics2 = mock(GameMechanicsImpl.class);

    private final String login_first = "login_one";
    private final String login_second = "login_two";
    private final int position = 1;

    @Test
    public void testQueues() throws Exception {

        when(gameMechanics1.getAddress()).thenReturn(new Address());
        when(gameMechanics2.getAddress()).thenReturn(new Address());

        messageSystem.addService(gameMechanics1);
        messageSystem.addService(gameMechanics2);

        MessageDoTurn messageDoTurn = new MessageDoTurn(null, gameMechanics1.getAddress(), login_first, position);
        MessageCloseGameSession messageCloseGameSession = new MessageCloseGameSession(null, gameMechanics2.getAddress(), login_first);

        messageSystem.sendMessage(messageDoTurn);
        messageSystem.sendMessage(messageCloseGameSession);

        messageSystem.execForAbonent(gameMechanics1);
        messageSystem.execForAbonent(gameMechanics2);

        verify(gameMechanics1, atLeastOnce()).doTurn(login_first, position);
        verify(gameMechanics1, never()).waitForEnemy(login_second);

        verify(gameMechanics2, atLeastOnce()).closeGameSession(login_first);
        verify(gameMechanics2, never()).doTurn(login_first, position);
    }


    @Test
    public void testAddService() throws Exception {
        when(abonent.getAddress()).thenReturn(address);
        messageSystem.addService(abonent);
        verify(abonent, atLeastOnce()).getAddress();
    }

    @Test
    public void testSendMessage() throws Exception {
        when(abonent.getAddress()).thenReturn(address);
        when(message.getTo()).thenReturn(address);
        messageSystem.addService(abonent);
        messageSystem.sendMessage(message);
        verify(abonent, atLeastOnce()).getAddress();
        verify(message, atLeastOnce()).getTo();
    }

    @Test
    public void testexecForAbonent() throws Exception {
        when(abonent.getAddress()).thenReturn(address);
        when(message.getTo()).thenReturn(address);
        messageSystem.addService(abonent);
        messageSystem.sendMessage(message);
        messageSystem.execForAbonent(abonent);
        verify(abonent, atLeastOnce()).getAddress();
        verify(message, atLeastOnce()).getTo();
        verify(message, atLeastOnce()).exec(abonent);
    }
}