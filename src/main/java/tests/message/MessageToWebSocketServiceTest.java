package tests.message;

import frontend.websocket.messages.*;
import messageSystem.Abonent;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class MessageToWebSocketServiceTest {

    private final Abonent abonent = mock(Abonent.class);

    @Test
    public void testMessageGameOver() throws Exception {
        MessageGameOver messageGameOver = mock(MessageGameOver.class);
        messageGameOver.exec(abonent);
        verify(messageGameOver, atLeastOnce()).exec(abonent);
    }

    @Test
    public void testMessageGameStarted() throws Exception {
        MessageGameStarted messageGameStarted = mock(MessageGameStarted.class);
        messageGameStarted.exec(abonent);
        verify(messageGameStarted, atLeastOnce()).exec(abonent);
    }

    @Test
    public void testMessageGameUpdated() throws Exception {
        MessageGameUpdated messageGameUpdated = mock(MessageGameUpdated.class);
        messageGameUpdated.exec(abonent);
        verify(messageGameUpdated, atLeastOnce()).exec(abonent);
    }
}