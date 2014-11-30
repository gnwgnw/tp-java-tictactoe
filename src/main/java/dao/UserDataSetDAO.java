package dao;

import base.UserDataSet;
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
public class UserDataSetDAO {
    private SessionFactory sessionFactory;

    public UserDataSetDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(UserDataSetImpl dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(dataSet);
        trx.commit();
        session.close();
    }

    public UserDataSetImpl read(long id) {
        Session session = sessionFactory.openSession();
        UserDataSetImpl user = (UserDataSetImpl) session.load(UserDataSetImpl.class, id);
        session.close();
        return user;

    }

    public UserDataSetImpl readByLogin(String user_login) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSetImpl.class);
        UserDataSetImpl user = (UserDataSetImpl) criteria.add(Restrictions.eq("login", user_login)).uniqueResult();
        session.close();
        return user;
    }

    public boolean checkByLogin(String user_login) {
        boolean isExist = false;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSetImpl.class);
        UserDataSetImpl user = (UserDataSetImpl) criteria.add(Restrictions.eq("login", user_login)).uniqueResult();
        if (user != null) {
            isExist = true;
        }
        session.close();
        return isExist;
    }

    public int count() {
        Session session = sessionFactory.openSession();
        UserDataSetImpl result = (UserDataSetImpl) session.createCriteria(UserDataSetImpl.class).setProjection(Projections.rowCount()).uniqueResult();
        int count = Integer.parseInt(result.toString());
        session.close();
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<UserDataSet> readAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSetImpl.class);
        List users = (List<UserDataSet>) criteria.list();
        session.close();
        return users;
    }
}
