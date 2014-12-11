package messageSystem;

import accounting.AccountService;
import frontend.websocket.WebSocketService;
import mechanics.GameMechanics;

/**
 * Created by titaevskiy.s on 10.12.14
 */
public class AddressService {

    private Address gameMechanicsAddress;
    private Address webSocketServiceAddress;
    private Address accountServiceAddress;

    public void registerGameMechanics(GameMechanics gameMechanics) {
        this.gameMechanicsAddress = gameMechanics.getAddress();
    }

    public void registerWebSocketService(WebSocketService webSocketService) {
        this.webSocketServiceAddress = webSocketService.getAddress();
    }

    public void registerAccountService(AccountService accountService) {
        this.accountServiceAddress = accountService.getAddress();
    }

    public Address getGameMechanicsAddress() {
        return gameMechanicsAddress;
    }

    public Address getWebSocketServiceAddress() {
        return webSocketServiceAddress;
    }

    public Address getAccountServiceAddress() {
        return accountServiceAddress;
    }
}
