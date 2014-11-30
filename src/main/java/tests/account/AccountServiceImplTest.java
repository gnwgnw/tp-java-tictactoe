package tests.account;

import base.AccountService;
import base.ResponsesCode;
import base.UserDataSet;
import dao.dbSessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import tests.daoTest.UserDataSetDAOTest;
import tests.daoTest.UserDataSetImplTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author s.titaevskiy on 13.09.14.
 */
public class AccountServiceImplTest implements AccountService {
    private final Map<String, UserDataSet> sessions = new ConcurrentHashMap<>();
    private UserDataSetDAOTest users;

    //TODO refactoring
    public AccountServiceImplTest() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSetImplTest.class);

        dbSessionFactory factory = new dbSessionFactory();

        SessionFactory sessionFactory = factory.createSessionFactory(configuration, "update");

        users = new UserDataSetDAOTest(sessionFactory);
    }

    @Override
    public ResponsesCode signUp(String login, String email, String password) {
//TODO validate the inputs param

        if (users.checkByLogin(login)) {
            return ResponsesCode.ALREADY_EXISTS;
        }
        else {
            UserDataSetImplTest userDataSet = new UserDataSetImplTest(login, email, password);
            users.save(userDataSet);
            return ResponsesCode.OK;
        }
    }

    @Override
    public ResponsesCode signIn(String login, String password, String httpSessionId) {
//TODO validate the inputs param

        UserDataSetImplTest user = users.readByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            sessions.put(httpSessionId, user);
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
        return users.count();
    }

    @Override
    public UserDataSet getUserDataSet(String httpSessionId) {
        return sessions.get(httpSessionId);
    }
}
