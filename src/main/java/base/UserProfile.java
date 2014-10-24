package base;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class UserProfile {
    private final String login;
    private final String email;
    private final String password;
    private int Score = 0;

    public UserProfile(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
