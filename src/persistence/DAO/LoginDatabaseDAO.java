package persistence.DAO;

import business.entities.User;
import persistence.LoginDAO;
import persistence.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LoginDatabaseDAO implements LoginDAO {

    @Override
    public List<User> getAllUsers() {
        List<User> user = new LinkedList<>();
        String query = "SELECT id, nombre, correo, password;";
        ResultSet information = SQLConnector.getInstance().selectQuery(query);

        try {
            while (information.next()) {
                long id =  information.getLong("id");
                String name = information.getString("nombre");
                String email = information.getString("correo");
                String password = information.getString("password");

                user.add(new User(id, name, email, password));
            }
        } catch (SQLException exception){
            System.err.println("Problem when " + exception.getErrorCode());
        }

        return user;
    }

    @Override
    public void singUpRequest(User user) {
        String query = "INSERT INTO usuario (Id_user, Name, Password, email) VALUES ('" +
                user.getId() + "," +
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
