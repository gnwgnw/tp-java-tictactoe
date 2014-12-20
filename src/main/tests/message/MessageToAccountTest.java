package message;

import accounting.AccountService;
import accounting.AccountServiceImpl;
import accounting.messages.MessageUserLose;
import accounting.messages.MessageUserPlay;
import accounting.messages.MessageUserWin;
import messageSystem.MessageSystem;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class MessageToAccountTest {

    private final MessageSystem messageSystem = new MessageSystem();
    private final AccountService accountService = mock(AccountServiceImpl.class);

    private final String login = "test";

    @Test
    public void testMessageUserLose() throws Exception {
        messageSystem.addService(accountService);

        MessageUserLose messageUserLose = new MessageUserLose(null, accountService.getAddress(), login);
        messageSystem.sendMessage(messageUserLose);

        messageSystem.execForAbonent(accountService);
        verify(accountService, atLeastOnce()).userLose(login);
    }

    @Test
    public void testMessageUserPlay() throws Exception {
        messageSystem.addService(accountService);

        MessageUserPlay messageUserPlay = new MessageUserPlay(null, accountService.getAddress(), login);
        messageSystem.sendMessage(messageUserPlay);

        messageSystem.execForAbonent(accountService);
        verify(accountService, atLeastOnce()).userPlay(login);
    }

    @Test
    public void testMessageUserWin() throws Exception {
        messageSystem.addService(accountService);

        MessageUserWin messageUserWin = new MessageUserWin(null, accountService.getAddress(), login);
        messageSystem.sendMessage(messageUserWin);

        messageSystem.execForAbonent(accountService);
        verify(accountService, atLeastOnce()).userWin(login);
    }
}