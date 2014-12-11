package accounting;

import accounting.database.UserDataSet;
import frontend.ResponsesCode;
import messageSystem.Abonent;

/**
 * Created by titaevskiy.s on 17.10.14
 */
public interface AccountService extends Abonent, Runnable {

    ResponsesCode signup(String login, String email, String password);

    ResponsesCode login(String login, String password, String httpSessionId);

    void logout(String httpSessionId);

    int getCountActiveUsers();

    long getCountSignupUsers();

    UserDataSet getUserDataSet(String httpSessionId);

    void userPlay(String login);

    void userWin(String login);

    void userLose(String login);
}
