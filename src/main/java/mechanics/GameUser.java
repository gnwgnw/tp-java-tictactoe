package mechanics;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class GameUser {
    public static final int X = 1;
    public static final int O = 4;

    private final String login;
    private final int sign;

    public GameUser(String login, int sign) {
        this.login = login;
        this.sign = sign;
    }

    public String getLogin() {
        return login;
    }

    public int getSign() {
        return sign;
    }
}
