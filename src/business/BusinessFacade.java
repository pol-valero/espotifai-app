package business;

import business.entities.User;

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
    boolean sameString(String generalString1, String compareString);

    /**
     * Metodo para comprobar que la contraseña cumpla los requisitos minimos
     * @param password String a analizar
     * @return boolean true si cumple los requisitos, false si no es correcta
     */
    boolean checkPassword(String password);

    /**
     * Metofo par iniciar sesion en un suuario ya registrado
     * @param login String con el email o name del usuario
     * @param password contraseña del usuario
     * @return boolean true si se a podido iniciar sesion, false si no a sido posible iniciar sesion
     */
    public boolean loginRequest(String login, String password);

    /**
     * Metodo para buscar el nombre de un usuario en la base de datos
     * @param name String con el nombre del usuario a buscar
     * @return boolean true si el usuario se a encontrado, false si no a sido encontrado
     */
    public boolean findUsernameMach(String name);

    /**
     * Metodo para buscar el email de un usuario en la base de datos
     * @param email String con el email del usuario a buscar
     * @return boolean true si el email del usuario se a encontrado, false si no a sido encontrado
     */
    public boolean findEmailMach(String email);

    /**
     * Metodo para registrar a un usuario
     * @param user Obsejeto de la clase User con la informacion del usuario
     */
    public void singUpRequest(User user);

    /**
     * Metodo para cerrar sesion
     */
    public void logoutRequest();

    /**
     * Metofo para eliminar la cuenta del usuario actual
     */
    public void deleteAccountRequest();
}
