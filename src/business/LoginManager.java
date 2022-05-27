package business;

import business.entities.User;
import persistence.DAO.LoginDatabaseDAO;
import persistence.LoginDAO;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LoginManager {

    private LoginDAO loginDAO = new LoginDatabaseDAO();
    private User currentUser;

    private final static boolean OK = true;
    private final static boolean ERROR = false;

    //El que hi ha dins d'aquest constructor s'ha de borrar. Nomes es per fer proves.

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
       /*
        String passwordSHA = pwdHash(password);

        if (loginDAO.checkLogin(login, passwordSHA) > 0 ) return OK;
        else return ERROR;
        */

        List<User> users = loginDAO.getAllUsers();
        String passwordSHA = pwdHash(password);
        if(itsEmail(login)){
            if (findEmailMach(login)){
                for (User user: users) {
                    if (login.equals(user.getEmail())) {
                        if (passwordSHA.equals(user.getPassword())) {
                            setCurrentUser(user);
                            return OK;
                        }
                    }
                }
            }
        } else{
            if (findUsernameMach(login)) {
                for (User user: users) {
                    if (login.equals(user.getName())) {
                        if (passwordSHA.equals(user.getPassword())) {
                            setCurrentUser(user);
                            return OK;
                        }
                    }
                }
            }
        }
        return ERROR;

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
                return OK;
            }
        }
        return ERROR;
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
                return OK;
            }
        }
        return ERROR;
    }

    /**
     * Metodo para registrar a un usuario
     * @param user Obsejeto de la clase User con la informacion del usuario
     */
    public void singUpRequest(User user){

        // miro si existe primero el usuario
      //  if (loginDAO.checkUser(user.getName())) {
            // si existe o existe un error al verificar si existe el usuario obtendremos True
            // NO podemos dar de alta. Falta cambiar la función a boolean

       // } else {
            //si NO EXISTE EL USUARIO, lo damos de alta
            loginDAO.singUpRequest(user);
            setCurrentUser(user);
        //}

    }

    /**
     * Metofo para eliminar la cuenta del usuario actual
     */
    public void deleteAccountRequest(){
        loginDAO.deleteAccountRequest(currentUser.getName());
        logoutRequest();
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

    public boolean checkEmail(String email){ //todo falta comprobar que solo hayan numeros, letras y puntos
                                            // (contando que ya se comprueba que solo haya un@), poner metodo
                                            //que lo compruebe ya que sera para cada parte del String
        if ( !itsEmail(email) || checkNonEmptyString(email)){
            try {
                if (email.length() <= 254 && checkNonEmptyString(email) && email.charAt(email.length() - 1) == '.') {
                    String[]  userUrl = email.split("@");

                    if (checkNonEmptyString(userUrl[0]) && userUrl.length == 2) {
                        String[] url = userUrl[1].split("\\.");

                        if (url.length > 1) {
                            for (int i = 1; i < url.length; i++) {
                                if(url[i].length() < 2){
                                    return ERROR;
                                }
                            }
                            return OK;
                        }
                    }
                }
                return ERROR;
            } catch (ArrayIndexOutOfBoundsException e){
                return ERROR;
            }
        }
        return ERROR;
    }

    public boolean checkPassword(String password) {

        if (password.length() >= 8) {
            char[] letter = password.toCharArray();

            if (checkCapitalLetter(letter) && checkLowercaseLetter(letter) && containsNumber(letter)) {
                System.out.println("CONTRASEMNYA OK");
                return OK;
            }
        }
        System.out.println("CONTRASEMNYA Error");
        return ERROR;
    }
    /**
     * Metodo que comprueba que haya al menos una mayuscula en la contraseña
     * @param password array de char, donde en cada posicion del array esta un caracter de la contraseña
     * @return boolean OK (true) si hay una mayuscula, ERROR (false) si no hya ninguna mayuscula
     */
    private boolean checkCapitalLetter(char[] password){

        for (int i = 0; i < password.length ; i++) {
            char aux = Character.toUpperCase(password[i]);

            if (Character.compare(aux, password[i]) == 0 && !Character.isDigit(password[i])) {
                return OK;
            }
        }
        return ERROR;
    }

    /**
     * Metodo que comprueba que haya al menos una minuscula en la contraseña
     * @param password array de char, donde en cada posicion del array esta un caracter de la contraseña
     * @return boolean OK (true) si hay una minuscula, ERROR (false) si no hya ninguna minuscula
     */
    private boolean checkLowercaseLetter(char[] password){

        for (int i = 0; i < password.length ; i++) {
            char aux = Character.toLowerCase(password[i]);

            if (Character.compare(aux, password[i]) == 0 && !Character.isDigit(password[i])) {
                return OK;
            }
        }
        return ERROR;
    }

    /**
     * Metodo que comprueba que al menos haya un numero en la contraseña
     * @param password array de char con los characteres de la contraseña
     * @return boolean OK (true) si contiene al menos un numero, ERROR (false) si no contiene al menos uno
     */
    private boolean containsNumber(char[] password){

        for (int i = 0; i < password.length ; i++) {
            if (Character.isDigit(password[i])) {
                return OK;
            }
        }
        return ERROR;
    }

    /***
     * Metodo para saber si una String esta vacia
     * @param generalString String a comprobar
     * @return boolean, false si esta vacia, true si contiene caracteres
     */
    private boolean checkNonEmptyString(String generalString) {
        return !generalString.equals("");
    }

    private String pwdHash(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.reset();
        try {
            digest.update(password.getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        password = String.format("%064x", new BigInteger(1, digest.digest()));

        return password;
    }

}
