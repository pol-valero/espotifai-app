package business;

import business.entities.User;
import persistence.LoginDAO;

import java.util.List;

public class LoginManager {

    private LoginDAO loginDAO;
    private User currentUser;

    /**
     * Metodo para obtener la informacio del usuario actual
     * @return objseto de la Clase User con el usuario actual
     */
    public User getCurrentUSer(){  //todo guardarlo al iniciar sesion/crear cuenta
        return currentUser;
    }

    /**
     * Metofo par iniciar sesion en un suuario ya registrado
     * @param login String con el email o name del usuario
     * @param password contraseña del usuario
     * @return boolean true si se a podido iniciar sesion, false si no a sido posible iniciar sesion
     */
    public boolean loginRequest(String login, String password){
        List<User> users = loginDAO.getAllUsers();
        if(itsEmail(login)){
            if (findEmailMach(login)){
                for (User user: users) {
                    if (login.equals(user.getEmail())) {
                        if (password.equals(user.getPassword())) {
                            setCurrentUser(user);
                            return true;
                        }
                    }
                }
            }
        } else{
            if (findUsernameMach(login)) {
                for (User user: users) {
                    if (login.equals(user.getName())) {
                        if (password.equals(user.getPassword())) {
                            setCurrentUser(user);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Metodo para buscar el nombre de un usuario en la base de datos
     * @param name String con el nombre del usuario a buscar
     * @return boolean true si el usuario se a encontrado, false si no a sido encontrado
     */
    public boolean findUsernameMach(String name){
        List<User> users = loginDAO.getAllUsers();

        for(User user: users){
            if (name.equals(user.getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para buscar el email de un usuario en la base de datos
     * @param email String con el email del usuario a buscar
     * @return boolean true si el email del usuario se a encontrado, false si no a sido encontrado
     */
    public boolean findEmailMach(String email){
        List<User> users = loginDAO.getAllUsers();

        for(User user: users){
            if (email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para registrar a un usuario
     * @param user Obsejeto de la clase User con la informacion del usuario
     */
    public void singUpRequest(User user){
        loginDAO.singUpRequest(user);
    }

    /**
     * Metofo para eliminar la cuenta del usuario actual
     */
    public void deleteAccountRequest(){
        loginDAO.deleteAccountRequest(currentUser.getName());
    }

    /**
     * Metodo para cerrar sesion es decir el atributo cuurentUser es null
     */
    public void logoutRequest(){
        this.currentUser = null;
    }

    /**
     * Metodo para asignar el atributo currentUser
     * @param currentUser objeto con el usuario a guardar
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Metodo para saber si el String es un email o un nombre
     * @param email String com el email a comprobar
     * @return
     */
    private boolean itsEmail(String email){ //todo el nombre provisional
        return email.contains("@");
    }

}
