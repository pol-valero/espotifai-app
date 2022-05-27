package business;

import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Interface capable of using methods in order to manage information flow between presentation and business
 * layers.
 */
public interface BusinessFacade {

    /**
     * Method that checks if email fields are correct
     * @param email string with the email to be checked
     * @return bolean for true in case email fields are correct. False for the other case.
     */
    boolean checkEmail(String email);

    /**
     * Method to check if two strings are equal or not.
     * @param generalString1 string to be checked
     * @param compareString string of reference
     * @return boolean true in case of two identical strings. False for the other case.
     */
    boolean sameString(String generalString1, String compareString);

    /**
     * Method that checks if a password meets the requirements. This requirements are the same ones
     * established by MIT.
     * @param password String to check
     * @return boolean true in case the password meets the requirements. False for opposite case.
     */
    boolean checkPassword(String password);

    /**
     * Metofo par iniciar sesion en un suuario ya registrado
     * @param login String con el email o name del usuario
     * @param password contraseña del usuario
     * @return boolean true si se a podido iniciar sesion, false si no a sido posible iniciar sesion
     */
    /**
     * Method to log in an already signed-up user.
     * @param login string of the user's login
     * @param password string password
     * @return
     */
     boolean loginRequest(String login, String password);


    /**
     * Method that checks if given a string, it corresponds to an already
     * signed-up user.
     * @param name string of a supposedly existing user.
     * @return boolean true in case Data-base finds this user.
     */
     boolean findUsernameMach(String name);

    /**
     * Method that checks if given an email address, this is already registered in the
     * data-base.
     * @param email string of the email address to look for.
     * @return boolean true if the data-base finds the email address. False in opposite case.
     */
     boolean findEmailMach(String email);

    /**
     *Method to register an user.
     * @param user Object of the class User
     */
     void singUpRequest(User user);

    /**
     * Metodo para cerrar sesion
     */
     void logoutRequest();

    /**
     * Metodo para eliminar la cuenta del usuario actual
     */
     void deleteAccountRequest();

    User getCurrentUser();

     LinkedList<String> loadPublicPlaylist();

     LinkedList<String> loadUserPlaylist();

    void addSongPlaylist(String playlistName, Song song, int position);

     List<Song> loadAllMusic();

     void deleteSongPlaylist(String playlistName, List<String> songName);

     void deletePlaylist(String playlistName);

     void createPlaylist(String playlistName);

     Playlist findPlaylist(String playlistName); //todo mirar

     List<Song> loadMusicPlaylist(String playlistName);

    List<Song> loadSearchMusic(String filterName);

     void createSong(Song song);

     List<Genre> loadStadistic();

     void deleteUserAddedSong(String songName);

     Song findSong(String songName);

     void playMusic(String playlistName, int position);

    void previusNextSong(int next);

    void pausedSong();

    boolean findPlaylistName(String playlistName);

     void changePlaylistName(String currentName, String newName);

     boolean isPublicPlaylist(String playlistName);

     String getCurrentPlaylist();

     void setCurrentPlaylist(String playlistName);
}
