package main;

import javax.servlet.http.HttpSession;
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
        } else {
            UserProfile userProfile = new UserProfile(login, email, password);
            users.put(login, userProfile);
            return ResponsesCode.OK;
        }
    }

    public ResponsesCode signIn(String login, String password, HttpSession httpSession) {
//TODO validate the inputs param

        if (users.containsKey(login)) {
            if (users.get(login).getPassword().equals(password)) {
                sessions.put(httpSession.toString(), users.get(login));
                return ResponsesCode.OK;
            } else return ResponsesCode.WRONG_SIGNIN;
        } else return ResponsesCode.WRONG_SIGNIN;
    }

    public void signOut(HttpSession httpSession) {
        sessions.remove(httpSession.toString());
    }
}
