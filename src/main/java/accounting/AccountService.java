package accounting;

import accounting.database.UserDataSet;
import frontend.ResponsesCode;

/**
 * Created by titaevskiy.s on 17.10.14
 */
public interface AccountService {
    ResponsesCode signup(String login, String email, String password);

    ResponsesCode login(String login, String password, String httpSessionId);

    void logout(String httpSessionId);

    int getCountActiveUsers();

    long getCountSignupUsers();

    UserDataSet getUserDataSet(String httpSessionId);
}
