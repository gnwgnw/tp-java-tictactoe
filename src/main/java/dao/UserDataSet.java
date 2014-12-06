package dao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author s.titaevskiy on 13.09.14.
 */
@Entity
@Table(name = "Users")
public class UserDataSet implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gameCount")
    private long gameCount = 0;

    @Column(name = "gameWin")
    private long gameWin = 0;

    @Column(name = "gameLose")
    private long gameLose = 0;

    public UserDataSet(String login, String email, String password) {
        this.id = -1;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public UserDataSet() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getGameCount() {
        return gameCount;
    }

    public void setGameCount(long gameCount) {
        this.gameCount = gameCount;
    }

    public long getGameWin() {
        return gameWin;
    }

    public void setGameWin(long gameWin) {
        this.gameWin = gameWin;
    }

    public long getGameLose() {
        return gameLose;
    }

    public void setGameLose(long gameLose) {
        this.gameLose = gameLose;
    }
}