package accounting.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
}
