package service;

import base.AccountService;
import base.ResponsesCode;
import base.UserProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class AccountServiceImpl implements AccountService {
    private Map<String, UserProfile> users = new HashMap<>();
    private Map<String, UserProfile> sessions = new HashMap<>();

    @Override
    public ResponsesCode signUp(String login, String email, String password) {
//TODO validate the inputs param

        if (users.containsKey(login)) {
            return ResponsesCode.ALREADY_EXISTS;
        }
        else {
            UserProfile userProfile = new UserProfile(login, email, password);
            users.put(login, userProfile);
            return ResponsesCode.OK;
        }
    }

    @Override
    public ResponsesCode signIn(String login, String password, String httpSessionId) {
//TODO validate the inputs param

        if (users.containsKey(login) && users.get(login).getPassword().equals(password)) {
            sessions.put(httpSessionId, users.get(login));
            return ResponsesCode.OK;
        }
        else {
            return ResponsesCode.WRONG_SIGNIN;
        }
    }

    @Override
    public void signOut(String httpSessionId) {
        sessions.remove(httpSessionId);
    }

    @Override
    public int countSignIn() {
        return sessions.size();
    }

    @Override
    public int countSignUp() {
        return users.size();
    }

    @Override
    public UserProfile getUserProfile(String httpSessionId) {
        return sessions.get(httpSessionId);
    }
}
