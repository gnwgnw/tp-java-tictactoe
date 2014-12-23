package frontend.websocket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by titaevskiy.s on 23.12.14
 */
public class CustomMobileWebSocketCreator implements WebSocketCreator {

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        return new MobileWebSocket();
    }
}
