package persistence.DAO;

import business.entities.User;
import persistence.LoginDAO;

import java.util.List;

public class LoginDatabaseDAO implements LoginDAO {

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void singUpRequest(User user) {
        String query = "el codigo de la tabla y insertar ";

    }

    @Override
    public void deleteAccountRequest(String userName) {

    }
}
