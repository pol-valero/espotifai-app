package business;


/**
 * Clase para gestionar la informacion entre el controller y las clases de Business
 */
public class BusinessFacadelmpl implements BusinessFacade{

    private final static boolean OK = true;
    private final static boolean ERROR = false;


    @Override
    public boolean checkEmptyString(String generalString) {
        return !generalString.equals("");
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
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

}
