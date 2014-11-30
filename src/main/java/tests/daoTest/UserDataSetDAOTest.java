package tests.daoTest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by kic on 11/9/14.
 */
public class UserDataSetDAOTest {
    private SessionFactory sessionFactory;


    public UserDataSetDAOTest(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(UserDataSetImplTest dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(dataSet);
        trx.commit();
        session.close();
    }

    public UserDataSetImplTest read(long id) {
        Session session = sessionFactory.openSession();
        UserDataSetImplTest user = (UserDataSetImplTest) session.load(UserDataSetImplTest.class, id);
        session.close();
        return user;

    }

    public UserDataSetImplTest readByLogin(String user_login) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSetImplTest.class);
        UserDataSetImplTest user = (UserDataSetImplTest) criteria.add(Restrictions.eq("login", user_login)).uniqueResult();
        session.close();
        return user;
    }

    public boolean checkByLogin(String user_login) {
        boolean isExist = false;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSetImplTest.class);
        UserDataSetImplTest user = (UserDataSetImplTest) criteria.add(Restrictions.eq("login", user_login)).uniqueResult();
        if (user != null) {
            isExist = true;
        }
        session.close();
        return isExist;
    }

    public int count() {
        Session session = sessionFactory.openSession();
        UserDataSetImplTest result = (UserDataSetImplTest) session.createCriteria(UserDataSetImplTest.class).setProjection(Projections.rowCount()).uniqueResult();
        int count = Integer.parseInt(result.toString());
        session.close();
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<UserDataSetImplTest> readAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSetImplTest.class);
        return (List<UserDataSetImplTest>) criteria.list();
    }
}
