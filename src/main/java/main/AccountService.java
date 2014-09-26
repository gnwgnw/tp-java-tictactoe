package main;

import java.util.HashMap;
import java.util.Map;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class AccountService {
    private Map<String, UserProfile> users = new HashMap<>();
    private Map<String, UserProfile> sessions = new HashMap<>();

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

    public ResponsesCode signIn(String login, String password, String httpSessionString) {
//TODO validate the inputs param

        if (users.containsKey(login) && users.get(login).getPassword().equals(password)) {
            sessions.put(httpSessionString, users.get(login));
            return ResponsesCode.OK;
        }
        else return ResponsesCode.WRONG_SIGNIN;
    }

    public void signOut(String httpSessionString) {
        sessions.remove(httpSessionString);
    }

    public int countSignIn() {
        return sessions.size();
    }

    public int countSignUp() {
        return users.size();
    }

    public UserProfile getCurrentUserProfile(String httpSessionString) {
        return sessions.get(httpSessionString);
    }
}
