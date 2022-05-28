package persistence;

import business.entities.User;

import java.util.List;

public interface LoginDAO {

    //public int checkLogin(String login, String pwdhash);

   // public boolean checkUser(String username);

    /***
     * Method to get all users stored in the database
     * @return List of user with user information
     */
    public List<User> getAllUsers();

    /***
     * Method to register a user
     * @param user object of the User class with the user data
     */
    public void singUpRequest(User user);

    /**
     * Method to remove a user from the database
     * @param userName String with the name of the user to be deleted
     */
    public void deleteAccountRequest(String userName);

   // User getUser(String name, String pwd);
}
