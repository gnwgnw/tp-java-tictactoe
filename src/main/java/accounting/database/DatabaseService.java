package accounting.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by titaevskiy.s on 05.12.14
 */
public class DatabaseService {

    private static SessionFactory sessionFactory;
    private static UsersDAO usersDAO;

    static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate_test.cfg.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static UsersDAO getUsersDAO() {
        if (usersDAO == null) {
            usersDAO = new UsersDAOImpl(sessionFactory);
        }
        return usersDAO;
    }
}
