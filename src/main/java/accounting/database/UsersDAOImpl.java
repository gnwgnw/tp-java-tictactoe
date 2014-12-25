package accounting.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by titaevskiy.s on 12/5/14
 */
public class UsersDAOImpl implements UsersDAO {

    private SessionFactory sessionFactory;

    public UsersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveUser(UserDataSet dataSet) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(dataSet);
        try {
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public UserDataSet getUserById(long id) {
        Session session = sessionFactory.openSession();
        UserDataSet user = (UserDataSet) session.load(UserDataSet.class, id);
        session.close();
        return user;
    }

    public UserDataSet getUserByLogin(String userLogin) {
        Session session = sessionFactory.openSession();
        UserDataSet user = (UserDataSet) session.createCriteria(UserDataSet.class)
                .add(Restrictions.eq("login", userLogin))
                .uniqueResult();
        session.close();
        return user;
    }

    public boolean isUserExists(String userLogin) {
        Session session = sessionFactory.openSession();
        UserDataSet user = (UserDataSet) session.createCriteria(UserDataSet.class)
                .add(Restrictions.eq("login", userLogin))
                .uniqueResult();
        session.close();
        return (user != null);
    }

    public long getUsersCount() {
        Session session = sessionFactory.openSession();
        long result = (long) session.createCriteria(UserDataSet.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
        session.close();
        return result;
    }

    @Override
    public void updateUser(UserDataSet dataSet) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(dataSet);
        try {
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List getTopUsers() {
        Session session = sessionFactory.openSession();
        List userDataSets = session.createCriteria(UserDataSet.class)
                .addOrder(Order.desc("gameWin"))
                .setMaxResults(10)
                .list();
        session.close();
        return userDataSets;
    }
}
