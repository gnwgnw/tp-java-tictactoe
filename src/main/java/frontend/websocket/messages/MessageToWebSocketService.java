package frontend.websocket.messages;

import frontend.websocket.WebSocketService;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.Message;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public abstract class MessageToWebSocketService extends Message {
    public MessageToWebSocketService(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) {
        if (abonent instanceof WebSocketService) {
            exec((WebSocketService) abonent);
        }
    }

    public abstract void exec(WebSocketService webSocketService);
}
