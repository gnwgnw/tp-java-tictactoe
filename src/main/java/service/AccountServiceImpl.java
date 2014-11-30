package service;

import base.AccountService;
import base.ResponsesCode;
import base.UserDataSet;
import dao.UserDataSetDAO;
import dao.dbSessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class AccountServiceImpl implements AccountService {
    private final Map<String, UserDataSet> sessions = new ConcurrentHashMap<>();
    private UserDataSetDAO users;

    //TODO refactoring
    public AccountServiceImpl() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);

        dbSessionFactory factory = new dbSessionFactory();

        SessionFactory sessionFactory = factory.createSessionFactory(configuration, "update");

        users = new UserDataSetDAO(sessionFactory);
    }

    @Override
    public ResponsesCode signUp(String login, String email, String password) {
//TODO validate the inputs param

        if (users.checkByLogin(login)) {
            return ResponsesCode.ALREADY_EXISTS;
        }
        else {
            UserDataSet UserDataSet = new UserDataSet(login, email, password);
            users.save(UserDataSet);
            return ResponsesCode.OK;
        }
    }

    @Override
    public ResponsesCode signIn(String login, String password, String httpSessionId) {
//TODO validate the inputs param

        if (users.checkByLogin(login) && users.readByLogin(login).getPassword().equals(password)) {
            sessions.put(httpSessionId, users.readByLogin(login));
            return ResponsesCode.OK;
        }
        else {
            return ResponsesCode.WRONG_LOGIN;
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
        return users.count();
    }

    @Override
    public UserDataSet getUserDataSet(String httpSessionId) {
        return sessions.get(httpSessionId);
    }
}
