package mechanics;

import base.GameMechanics;
import base.WebSocketService;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class GameMechanicsImpl implements GameMechanics {
    WebSocketService webSocketService;

    public GameMechanicsImpl(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void run() {

    }

    @Override
    public void waitForEnemy(String login) {

    }

    @Override
    public void doTurn(String login, long position) {

    }
}
