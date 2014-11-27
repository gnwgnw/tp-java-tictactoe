package base;

/**
 * Created by kic on 11/21/14.
 */
public interface UserDataSet {

    public long getId(Long id);

    public String getLogin();

    public void setLogin(String login);

    public String getEmail();

    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String pw);

    public void setId(long id);
}
