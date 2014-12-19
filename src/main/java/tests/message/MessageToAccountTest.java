package tests.message;

import accounting.messages.*;
import messageSystem.Abonent;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class MessageToAccountTest {

    private final Abonent abonent = mock(Abonent.class);

    @Test
    public void testMessageUserLose() throws Exception {
        MessageUserLose messageUserLose = mock(MessageUserLose.class);
        messageUserLose.exec(abonent);
        verify(messageUserLose, atLeastOnce()).exec(abonent);
    }

    @Test
    public void testMessageUserPlay() throws Exception {
        MessageUserPlay messageUserPlay = mock(MessageUserPlay.class);
        messageUserPlay.exec(abonent);
        verify(messageUserPlay, atLeastOnce()).exec(abonent);
    }

    @Test
    public void testMessageUserWin() throws Exception {
        MessageUserWin messageUserWin = mock(MessageUserWin.class);
        messageUserWin.exec(abonent);
        verify(messageUserWin, atLeastOnce()).exec(abonent);
    }
}