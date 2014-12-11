package accounting;

import accounting.database.DatabaseService;
import accounting.database.UserDataSet;
import accounting.database.UsersDAO;
import frontend.ResponsesCode;
import messageSystem.Address;
import messageSystem.MessageSystem;
import utils.TimeHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class AccountServiceImpl implements AccountService {

    private static final int STEP_TIME = 50;

    private final Address address = new Address();
    private final MessageSystem messageSystem;

    private final Map<String, UserDataSet> sessions = new ConcurrentHashMap<>();
    private final UsersDAO users = DatabaseService.getUsersDAO();

    public AccountServiceImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        messageSystem.addService(this);
        messageSystem.getAddressService().registerAccountService(this);
    }

    @Override
    public ResponsesCode signup(String login, String email, String password) {
        //TODO validate the inputs param

        if (users.isUserExists(login)) {
            return ResponsesCode.ALREADY_EXISTS;
        }
        else {
            UserDataSet userDataSet = new UserDataSet(login, email, password);
            users.saveUser(userDataSet);
            return ResponsesCode.OK;
        }
    }

    @Override
    public ResponsesCode login(String login, String password, String httpSessionId) {
        //TODO validate the inputs param

        UserDataSet user = users.getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            sessions.put(httpSessionId, user);
            return ResponsesCode.OK;
        }
        else {
            return ResponsesCode.WRONG_LOGIN;
        }
    }

    @Override
    public void logout(String httpSessionId) {
        sessions.remove(httpSessionId);
    }

    @Override
    public int getCountActiveUsers() {
        return sessions.size();
    }

    @Override
    public long getCountSignupUsers() {
        return users.getUsersCount();
    }

    @Override
    public UserDataSet getUserDataSet(String httpSessionId) {
        return sessions.get(httpSessionId);
    }

    @Override
    public void userPlay(String login) {
        UserDataSet userDataSet = sessions.get(login);
        if (userDataSet == null) {
            userDataSet = users.getUserByLogin(login);
        }

        userDataSet.increaseGameCount();
        users.updateUser(userDataSet);
    }

    @Override
    public void userWin(String login) {
        UserDataSet userDataSet = sessions.get(login);
        if (userDataSet == null) {
            userDataSet = users.getUserByLogin(login);
        }

        userDataSet.increaseGameWin();
        users.updateUser(userDataSet);
    }

    @Override
    public void userLose(String login) {
        UserDataSet userDataSet = sessions.get(login);
        if (userDataSet == null) {
            userDataSet = users.getUserByLogin(login);
        }

        userDataSet.increaseGameLose();
        users.updateUser(userDataSet);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            TimeHelper.sleep(STEP_TIME);
        }
    }
}
