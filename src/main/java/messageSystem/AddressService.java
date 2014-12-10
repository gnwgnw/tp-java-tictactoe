package messageSystem;

import frontend.websocket.WebSocketService;
import mechanics.GameMechanics;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public class AddressService {

    private Address gameMechanicsAddress;
    private Address webSocketServiceAddress;

    public void registerGameMechanics(GameMechanics gameMechanics) {
        this.gameMechanicsAddress = gameMechanics.getAddress();
    }

    public void registerWebSocketService(WebSocketService webSocketService) {
        this.webSocketServiceAddress = webSocketService.getAddress();
    }

    public Address getGameMechanicsAddress() {
        return gameMechanicsAddress;
    }

    public Address getWebSocketServiceAddress() {
        return webSocketServiceAddress;
    }
}
