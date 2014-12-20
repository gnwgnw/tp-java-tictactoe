package message;

import frontend.websocket.WebSocketService;
import frontend.websocket.messages.MessageGameOver;
import frontend.websocket.messages.MessageGameStarted;
import frontend.websocket.messages.MessageGameUpdated;
import mechanics.UserGameState;
import messageSystem.MessageSystem;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class MessageToWebSocketServiceTest {

    private final MessageSystem messageSystem = new MessageSystem();
    private final WebSocketService webSocketService = mock(WebSocketService.class);

    private final UserGameState first = mock(UserGameState.class);
    private final UserGameState second = mock(UserGameState.class);

    @Test
    public void testMessageGameOver() throws Exception {
        messageSystem.addService(webSocketService);

        MessageGameOver message = new MessageGameOver(null, webSocketService.getAddress(), first, second);
        messageSystem.sendMessage(message);

        messageSystem.execForAbonent(webSocketService);
        verify(webSocketService, atLeastOnce()).notifyGameOver(first);
        verify(webSocketService, atLeastOnce()).notifyGameOver(second);
    }

    @Test
    public void testMessageGameStarted() throws Exception {
        messageSystem.addService(webSocketService);

        MessageGameStarted message = new MessageGameStarted(null, webSocketService.getAddress(), first, second);
        messageSystem.sendMessage(message);

        messageSystem.execForAbonent(webSocketService);
        verify(webSocketService, atLeastOnce()).notifyStartGame(first);
        verify(webSocketService, atLeastOnce()).notifyStartGame(second);
    }

    @Test
    public void testMessageGameUpdated() throws Exception {
        messageSystem.addService(webSocketService);

        MessageGameUpdated message = new MessageGameUpdated(null, webSocketService.getAddress(), first, second);
        messageSystem.sendMessage(message);

        messageSystem.execForAbonent(webSocketService);
        verify(webSocketService, atLeastOnce()).notifyUpdateGameState(first);
        verify(webSocketService, atLeastOnce()).notifyUpdateGameState(second);
    }
}