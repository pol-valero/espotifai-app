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
    public LoginManager () {
        User userProva = new User("x", "x", "x");
        userProva.setId(15);
        setCurrentUser(userProva);
    }

    /**
     * Method to obtain information from current user
     * @return Object of the User class with the current user     *
     */
    public User getCurrentUSer(){  //todo guardarlo al iniciar sesion/crear cuenta
        return currentUser;
    }

    /**
     * Method capable to log in with the already registered user
     * @param login String corresponding the email or name of the user
     * @param password String corresponding the users password
     * @return boolean true in case the log in was successfully done
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
     * Method to look for the name of the user in the data-base
     * @param name Strin with the name of the searched user
     * @return boolean true in case the user has been found, false of the opposite case
     *
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
     * Method to look for the email of the user in the data-base
     * @param email String corresponding to the email address to look for
     * @return boolean true in case the email has been found, false of the opposite case
     *
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
     * Method to sign the user up
     * @param user User object with the information of the user who has sign up
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
     * Method to delete the accoun of the current user
     */
    public void deleteAccountRequest(){
        loginDAO.deleteAccountRequest(currentUser.getName());
        logoutRequest();
    }

    /**
     * Method to set the current user attribute to null, meaning that there is no current user
     */
    public void logoutRequest(){
        this.currentUser = null;
    }

    /**
     * Method to assign the current user to an specific one
     * @param currentUser User object corresponding to the user to be saved
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Method to know if a String corresponds to an email
     * @param email String corresponding to the email to check
     * @return boolean true in case the string is an actual email, false for opposite case
     */
    private boolean itsEmail(String email){ //todo el nombre provisional
        return email.contains("@");
    }

    /**
     * Method that checks that the email meets the requirements
     * @param email String of the email to check
     * @return boolean true in case the email meets the requirements, false in opposite case.
     */
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

    /**
     * Method which checks a given string.
     * @param password String corresponding to the password to check.
     * @return boolean true in case the given string meets the requirements established by MIT.
     */
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
     * Method that checks that there is at least one capital letter in a given String
     * @param password Char[] where there are the letters of the password to check
     * @return boolean OK (true) if the there is at least one capital letter, ERROR (false) in the opposite case
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
     * Method that checks that there is at least one letter case in a given String
     * @param password Char[] where there are the letters of the password to check
     * @return boolean OK (true) if the there is at least one letter case, ERROR (false) in the opposite case
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
     * Method that checks that there's at least one number in a char array
     * @param password Char array with the characters to be checked
     * @return boolean true in case the requirements al meet, false for the opposite case
     */
    private boolean containsNumber(char[] password){

        for (int i = 0; i < password.length ; i++) {
            if (Character.isDigit(password[i])) {
                return OK;
            }
        }
        return ERROR;
    }


    /**
     * method to know if a String is empty
     * @param generalString String to check
     * @return Boolean for true in case is empty, false for opposite case
     */
    private boolean checkNonEmptyString(String generalString) {
        return !generalString.equals("");
    }

    /**
     * Method that codifies a string (password) in hash. This will be used for security purposes.
     * @param password String to codify
     * @return String of the codified password
     */
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
