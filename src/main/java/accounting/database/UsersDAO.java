package accounting.database;

import java.util.List;

/**
 * Created by titaevskiy.s on 05.12.14
 */
public interface UsersDAO {

    void saveUser(UserDataSet dataSet);

    UserDataSet getUserById(long id);

    UserDataSet getUserByLogin(String userLogin);

    boolean isUserExists(String userLogin);

    long getUsersCount();

    void updateUser(UserDataSet dataSet);

    List getTopUsers();
}
