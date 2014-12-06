package service;

import base.AccountService;
import base.ResponsesCode;
import base.UsersDAO;
import dao.DatabaseService;
import dao.UserDataSet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class AccountServiceImpl implements AccountService {

    private final Map<String, UserDataSet> sessions = new ConcurrentHashMap<>();
    private UsersDAO users = DatabaseService.getUsersDAO();

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
}
