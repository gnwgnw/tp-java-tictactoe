package frontend.websocket.messages;

import frontend.websocket.WebSocketService;
import mechanics.UserGameState;
import messageSystem.Address;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public class MessageGameOver extends MessageToWebSocketService {

    private final UserGameState firstUser;
    private final UserGameState secondUser;

    public MessageGameOver(Address from, Address to, UserGameState firstUser, UserGameState secondUser) {
        super(from, to);
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    @Override
    public void exec(WebSocketService webSocketService) {
        webSocketService.notifyGameOver(firstUser);
        webSocketService.notifyGameOver(secondUser);
    }
}
