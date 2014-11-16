package dao;

import base.UserDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by kic on 11/9/14.
 */
public class CheckDAO {
    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);

        dbSessionFactory usersFactory = new dbSessionFactory();

        SessionFactory sessionFactory = usersFactory.createSessionFactory(configuration, "update");

        UserDataSetDAO dao = new UserDataSetDAO(sessionFactory);
        dao.save(new UserDataSet("user88", "lal@trall.ru", "123"));

    }

}
