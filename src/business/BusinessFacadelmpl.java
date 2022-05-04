package business;

import business.entities.User;

/**
 * Clase para gestionar la informacion entre el controller y las clases de Business
 */
public class BusinessFacadelmpl implements BusinessFacade{

    private LoginManager loginManager;

    private final static boolean OK = true;
    private final static boolean ERROR = false;


    @Override
    public boolean checkNonEmptyString(String generalString) {
        return !generalString.equals("");
    }

    @Override
    public boolean checkEmail(String email){ //todo falta comprobar que solo hayan numeros, letras y puntos
                                            // (contando que ya se comprueba que solo haya un@), poner metodo
                                            //que lo compruebe ya que sera para cada parte del String
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

    @Override
    public boolean sameString(String generalString1, String compareString) {
        return generalString1.equals(compareString);
    }

    @Override
    public boolean checkPassword(String password) {

        if (password.length() >= 8) {
            char[] letter = password.toCharArray();

            if (checkCapitalLetter(letter) && checkLowercaseLetter(letter) && containsNumber(letter)) {
                return OK;
            }
        }
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

    public boolean loginRequest(String login, String password){
        return loginManager.loginRequest(login, password);
    }

    public boolean findUsernameMach(String name){
        return loginManager.findUsernameMach(name);
    }

    public boolean findEmailMach(String email){
        return loginManager.findEmailMach(email);
    }

    public void singUpRequest(User user){
        loginManager.singUpRequest(user);
    }

    public void logoutRequest(){
        loginManager.logoutRequest();
    }

    public void deleteAccountRequest(){
        loginManager.deleteAccountRequest();
    }

    public User getCurrentUser(){
        return loginManager.getCurrentUSer();
    }

}
