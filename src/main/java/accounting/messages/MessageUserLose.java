package accounting.messages;

import accounting.AccountService;
import messageSystem.Address;

/**
 * Created by titaevskiy.s on 11.12.14
 */
public class MessageUserLose extends MessageToAccountService {

    private final String login;

    public MessageUserLose(Address from, Address to, String login) {
        super(from, to);
        this.login = login;
    }

    @Override
    public void exec(AccountService accountService) {
        accountService.userLose(login);
    }
}
