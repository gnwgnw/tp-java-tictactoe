package accounting.messages;

import accounting.AccountService;
import messageSystem.Address;

/**
 * Created by titaevskiy.s on 11.12.14
 */
public class MessageUserWin extends MessageToAccountService {

    private final String login;

    public MessageUserWin(Address from, Address to, String login) {
        super(from, to);
        this.login = login;
    }

    @Override
    public void exec(AccountService accountService) {
        accountService.userWin(login);
    }
}
