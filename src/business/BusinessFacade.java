package business;

/**
 * Interficie con los metodos para gestionar la informacion entre presentation y business
 */
public interface BusinessFacade {

    /***
     * Metodo para saber si una String esta vacia
     * @param generalString String a comprobar
     * @return boolean, false si esta vacia, true si contiene caracteres
     */
    boolean checkNonEmptyString(String generalString);

    /**
     * Metodo para comprobar que los campos del correo electronico son correctos
     * @return boolean true si el correo es correcto, false si es incorrecto
     */
    boolean checkEmail(String email);

    /**
     * Metodo para saber si dos Strings son iguales
     * @param generalString1 String principal
     * @param compareString String con la que queremos comparar
     * @return boolean true si son iguales, false si son diferentes
     */
    boolean sameString(String generalString1, String compareString); //para la base de datos como para las opciones de registrarse

    /**
     * Metodo para comprobar que la contraseña cumpla los requisitos minimos
     * @param password String a analizar
     * @return boolean true si cumple los requisitos, false si no es correcta
     */
    boolean checkPassword(String password);

}
