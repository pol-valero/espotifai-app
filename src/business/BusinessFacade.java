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
     *
     * @param email string with the email to be checked
     * @return bolean for true in case email fields are correct. False for the other case.
     */
    boolean checkEmail(String email);

    /**
     * Method to check if two strings are equal or not.
     *
     * @param generalString1 string to be checked
     * @param compareString  string of reference
     * @return boolean true in case of two identical strings. False for the other case.
     */
    boolean sameString(String generalString1, String compareString);

    /**
     * Method that checks if a password meets the requirements. This requirements are the same ones
     * established by MIT.
     *
     * @param password String to check
     * @return boolean true in case the password meets the requirements. False for opposite case.
     */
    boolean checkPassword(String password);


    /**
     * Method to log in an already signed-up user.
     *
     * @param login    string of the user's login
     * @param password string password of the user
     * @return boolean true in case the login has been done succesfully, false ofr opposite case.
     */
    boolean loginRequest(String login, String password);


    /**
     * Method that checks if given a string, it corresponds to an already
     * signed-up user.
     *
     * @param name string of a supposedly existing user.
     * @return boolean true in case Data-base finds this user.
     */
    boolean findUsernameMach(String name);

    /**
     * Method that checks if given an email address, this is already registered in the
     * data-base.
     *
     * @param email string of the email address to look for.
     * @return boolean true if the data-base finds the email address. False in opposite case.
     */
    boolean findEmailMach(String email);

    /**
     * Method to register an user.
     *
     * @param user Object of the class User
     */
    void singUpRequest(User user);

    /**
     * Method to logout the current user
     */
    void logoutRequest();

    /**
     * Method to delete de account of the current user
     */
    void deleteAccountRequest();

    /**
     * Method that gives the user that has log in or sign up
     *
     * @return returns an User object corresponding to the one that has recently log in o sign up
     */
    User getCurrentUser();

    /**
     * Method that loads the name of the playlist of all users excepts the one that has log in or sign up
     *
     * @return Linkedlist of String with the public playlist's names
     */
    LinkedList<String> loadPublicPlaylist();

    /**
     * Method that loads the name of the playlist of which the owner corresponds to the current user.
     *
     * @return LinkedList of Strings with the user's playlist's names
     */
    LinkedList<String> loadUserPlaylist();

    /**
     * Method that adds a song into an existing playlist in a certain position
     *
     * @param playlistName Name of the playlist in which the song is added
     * @param songNameList List song which is added to the playlist
     */
    void addSongPlaylist(String playlistName, List<String> songNameList);

    /**
     * Loads all songs in the Data-base
     *
     * @return List of Songs
     */
    List<Song> loadAllMusic();

    /**
     * Method that deletes the songs of a playlist
     *
     * @param playlistName Name of the playlist were songs are going to be deleted
     * @param songName     List of String with the name of the songs to be deleted from the playlist
     */
    void deleteSongPlaylist(String playlistName, List<String> songName);

    /**
     * Deletes an object Playlist
     *
     * @param playlistName name of the playlist to delete
     */
    void deletePlaylist(String playlistName);

    /**
     * Creates a playlist
     *
     * @param playlistName name of the playlist to be created
     */
    void createPlaylist(String playlistName);

    /**
     * Method that given a string, returns the playlist object which the string corresponds to the name of it
     *
     * @param playlistName String corresponding to the name of the playlist to find
     * @return return the object Playlist corresponding of the name of the String. If the playlist was not found,
     * returns null.
     */
    Playlist findPlaylist(String playlistName); //todo mirar

    /**
     * Method that by given de string corresponding to the name of an existing playlist, returns the list of
     * songs which belong to that playlist.
     *
     * @param playlistName String corresponding to the playlist were the songs belong to
     * @return List of objects Song
     */
    List<Song> loadMusicPlaylist(String playlistName);

    /**
     * Method that by given a string name corresponding to the search of a song returns a list of possible matches
     * from the search
     *
     * @param filterName String corresponding to the search the user has done
     * @return List of songs corresponding to the possible matches of the search
     */
    List<Song> loadSearchMusic(String filterName);

    /**
     * Method to create a song and saved it in the data-base
     *
     */
    void createSong(String name, String artist, String album, String genre, String filePath);

    /**
     * Method that returns all genres objects in memory
     *
     * @return List of objects Genres
     */
    List<Genre> loadStadistic();

    /**
     * Method that earses the song corresponding to the String given
     *
     * @param songName string corresponding to the song name to be deleted
     */
    void deleteUserAddedSong(String songName);

    /**
     * Method that looks for the song for which its name corresponds to the string given
     *
     * @param songName String corresponding to the name of the song to look for
     * @return Object song that has been find. Null in case it was not found.
     */
    Song findSong(String songName);

    /**
     * Method that reproduces a song given a playlist name and the position.
     *
     * @param playlistName String name of the playlist were the song to be reproduced belongs.
     * @param position     Integer corresponding to the position inside the List of songs of the corresponding
     *                     playlist.
     */
    void playMusic(String playlistName, int position);

    /**
     * Method that reproduces the previous or next song of the current song been reproduced.
     *
     * @param next integer responsible for setting the new song to be reproduced in n position above or under the
     *             the current song being played. Next should be 1 or -1 in most cases. For Next equals to 0, the current
     *             song is going to be played again. For different values, it will play a song n positions above ir under the current
     *             song. For Next values that might be out of the Playlist range, it is not going to cause problems.
     */
    void previusNextSong(int next);

    /**
     * Method to pause the current song which is being played.
     */
    void pausedSong();

    /**
     * Method that looks a supposedly existing playlist
     *
     * @param playlistName Playlist name to be find
     * @return boolean true in case the playlist exists. False for opposite case.
     */
    boolean findPlaylistName(String playlistName);

    boolean findSongName(String songName);

    boolean findPath(String path);

    /**
     * Method that changes a playlist name
     *
     * @param currentName String of the name of the playlist name that has to be found
     * @param newName     String of the new name the playlist is going to have
     */
    void changePlaylistName(String currentName, String newName);

    /**
     * Method that checks if a playlist is public or not by giving its corresponding name
     *
     * @param playlistName String of the name of the playlist to check
     * @return boolean true in case the playlist is actually public. False in opposite case.
     */
    boolean isPublicPlaylist(String playlistName);

    /**
     * Method that gives the name of the playlist were the user is currently using
     *
     * @return String name of the current playlist
     */
    String getCurrentPlaylist();

    /**
     * Method to set the current playlist to another one, corresponding to the String given
     *
     * @param playlistName String of the name of the object playlist to set as current.
     */
    void setCurrentPlaylist(String playlistName);

     List<Song> loadMusicOnePlaylist(String playlistName);

    public void moveSongsInPlaylist(String playlistName, int position, int upDown);

    public void deleteSongAllPlaylist(String songName);

    void deletePersonalSong(List<String> songNameList);

    void loop();

    List<Song> loadAllNotAlreadyAddedSong(String playlistName);
}