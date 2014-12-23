package frontend.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by titaevskiy.s on 23.12.14
 */
@WebSocket
public class MobileWebSocket {

    private final static Set<MobileWebSocket> MOBILE_WEB_SOCKETS = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private Session session;

    @OnWebSocketConnect
    public void onConnetct(Session session) {
        this.session = session;
        MOBILE_WEB_SOCKETS.add(this);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        MOBILE_WEB_SOCKETS.remove(this);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        sendMessage(data);
    }

    private void sendMessage(String data) {
        try {
            session.getRemote().sendString(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
