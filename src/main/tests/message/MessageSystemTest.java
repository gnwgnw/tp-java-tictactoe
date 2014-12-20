package message;

import messageSystem.*;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class MessageSystemTest {

    private final MessageSystem messageSystem = new MessageSystem();
    private final Abonent abonent = mock(Abonent.class);
    private final Message message = mock(Message.class);
    private final Address address = mock(Address.class);

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