package accounting.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by titaevskiy.s on 05.12.14
 */
public class DatabaseBuilder {

    private SessionFactory sessionFactory;
    private UsersDAO usersDAO;

    public DatabaseBuilder(String configString) {
        Configuration configuration = new Configuration();
        configuration.configure(configString);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public UsersDAO getUsersDAO() {
        if (usersDAO == null) {
            usersDAO = new UsersDAOImpl(sessionFactory);
        }
        return usersDAO;
    }
}
