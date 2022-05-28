package persistence.DAO;

import business.entities.User;
import persistence.LoginDAO;
import persistence.SQLConnector;

import javax.xml.transform.OutputKeys;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LoginDatabaseDAO implements LoginDAO {

    /***
     * Method to get all users stored in the database
     * @return List of user with user information
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        String query = "SELECT id, nombre, correo, pwd FROM usuario;";
        ResultSet information = SQLConnector.getInstance().selectQuery(query);

        try {
            while (information.next()) {
                int id =  information.getInt("id");
                String name = information.getString("nombre");
                String email = information.getString("correo");
                String password = information.getString("pwd");

                User user = new User(id, name, email, password);
                users.add(user);

            }
        } catch (SQLException exception){
            System.err.println("Problem when " + exception.getErrorCode());
        }

        return users;
    }

    /***
     * Method to register a user
     * @param user object of the User class with the user data
     */
    @Override
    public void singUpRequest(User user) {
        String query = "INSERT INTO usuario (nombre, correo, pwd) VALUES ('"
                + user.getName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() + "');";

        SQLConnector.getInstance().insertQuery(query);

    }

    /**
     * Method to remove a user from the database
     * @param userName String with the name of the user to be deleted
     */
    @Override
    public void deleteAccountRequest(String userName) {
        String query = "DELETE FROM usuario WHERE nombre = \"" + userName + "\";";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
