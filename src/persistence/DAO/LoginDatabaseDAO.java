package persistence.DAO;

import business.entities.User;
import persistence.LoginDAO;
import persistence.SQLConnector;

import java.util.List;

public class LoginDatabaseDAO implements LoginDAO {

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void singUpRequest(User user) {
        String query = "INSERT INTO usuario (Id_user, Name, Password, email) VALUES ('" +
                "2" + "," + //el dos hay que cambiarlo por un valor qe se le asigne al usuario la id
                user.getName() + "," +
                user.getPassword() + "," +
                user.getEmail() + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void deleteAccountRequest(String userName) {
        String query = "DELETE FROM usuario WHERE nombre = " + userName + ";";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
