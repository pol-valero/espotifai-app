package persistence;

import business.entities.User;

import java.util.List;

public interface LoginDAO {

    //public int checkLogin(String login, String pwdhash);

   // public boolean checkUser(String username);

    /***
     * Metodo para obtener todos los usuarios almacenados en la base de datos
     * @return List de user con la informacion de los usuarios
     */
    public List<User> getAllUsers();

    /***
     * Metodo para registrar a un usuario
     * @param user objeto de la clase User con los datos del usuario
     */
    public void singUpRequest(User user);

    /**
     * Metodo para eliminar a un usuario de la base de datos
     * @param userName String con el nombre del usuario a elimianar
     */
    public void deleteAccountRequest(String userName);

   // User getUser(String name, String pwd);
}
