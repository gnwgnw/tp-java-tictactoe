package dao;

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
public class UsersDAOImpl implements UsersDAOImpl {
    private SessionFactory sessionFactory;

    public UsersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(UserDataSet dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(dataSet);
        trx.commit();
        session.close();
    }

    public UserDataSet read(long id) {
        Session session = sessionFactory.openSession();
        UserDataSet user = (UserDataSet) session.load(UserDataSet.class, id);
        session.close();
        return user;

    }

    public UserDataSet readByLogin(String user_login) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSet.class);
        UserDataSet user = (UserDataSet) criteria.add(Restrictions.eq("login", user_login)).uniqueResult();
        session.close();
        return user;
    }

    public boolean checkByLogin(String user_login) {
        boolean isExist = false;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSet.class);
        UserDataSet user = (UserDataSet) criteria.add(Restrictions.eq("login", user_login)).uniqueResult();
        if (user != null) {
            isExist = true;
        }
        session.close();
        return isExist;
    }

    public int count() {
        Session session = sessionFactory.openSession();
        UserDataSet result = (UserDataSet) session.createCriteria(UserDataSet.class).setProjection(Projections.rowCount()).uniqueResult();
        int count = Integer.parseInt(result.toString());
        session.close();
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<UserDataSet> readAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSet.class);
        List users = (List<UserDataSet>) criteria.list();
        session.close();
        return users;
    }
}
