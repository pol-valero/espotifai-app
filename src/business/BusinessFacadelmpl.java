package business;

import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Class capable of managing information between controller and business classes
 */
public class BusinessFacadelmpl implements BusinessFacade{

    private LoginManager loginManager = new LoginManager();
    private MusicListManager musicListManager = new MusicListManager();
    private MusicManager musicManager = new MusicManager();
    private final String NOTPLAYLIST = "AllSongs";

    /**
     * Method that checks if email fields are correct
     * @param email string with the email to be checked
     * @return bolean for true in case email fields are correct. False for the other case.
     */
    @Override
    public boolean checkEmail(String email){
         return loginManager.checkEmail(email);
    }


    /**
     * Method to check if two strings are equal or not.
     * @param generalString1 string to be checked
     * @param compareString string of reference
     * @return boolean true in case of two identical strings. False for the other case.
     */
    @Override
    public boolean sameString(String generalString1, String compareString) {
        return generalString1.equals(compareString);
    }

    /**
     * Method that checks if a password meets the requirements. This requirements are the same ones
     * established by MIT.
     * @param password String to check
     * @return boolean true in case the password meets the requirements. False for opposite case.
     */
    @Override
    public boolean checkPassword(String password) {
        return loginManager.checkPassword(password);
    }

    /**
     * Method to log in an already signed-up user.
     * @param login string of the user's login
     * @param password string password of the user
     * @return boolean true in case the login has been done succesfully, false ofr opposite case.
     */
    @Override
    public boolean loginRequest(String login, String password){
        return loginManager.loginRequest(login, password);
    }

    /**
     * Method that checks if given a string, it corresponds to an already
     * signed-up user.
     * @param name string of a supposedly existing user.
     * @return boolean true in case Data-base finds this user.
     */
    @Override
    public boolean findUsernameMach(String name){
        return loginManager.findUsernameMach(name);
    }

    /**
     * Method that checks if given an email address, this is already registered in the
     * data-base.
     * @param email string of the email address to look for.
     * @return boolean true if the data-base finds the email address. False in opposite case.
     */
    @Override
    public boolean findEmailMach(String email){
        return loginManager.findEmailMach(email);
    }

    /**
     *Method to register an user.
     * @param user Object of the class User
     */
    @Override
    public void singUpRequest(User user){
        loginManager.singUpRequest(user);
    }

    /**
     * Method to logout the current user
     */
    @Override
    public void logoutRequest(){
        loginManager.logoutRequest();
    }

    /**
     * Method to delete de account of the current user
     */
    @Override
    public void deleteAccountRequest(){

        for (Song song: loadAllMusic()){
            if ( song.getIdOwne() == loginManager.getCurrentUSer().getId()){
                deleteUserAddedSong(song.getName());
            }
        }
        loginManager.deleteAccountRequest();
    }

    /**
     * Method that gives the user that has log in or sign up
     * @return returns an User object corresponding to the one that has recently log in o sign up
     */
    @Override
    public User getCurrentUser(){
        return loginManager.getCurrentUSer();
    }

    /**
     *Method that loads the name of the playlist of all users excepts the one that has log in or sign up
     * @return Linkedlist of String with the public playlist's names
     */
    @Override
    public LinkedList<String> loadPublicPlaylist(){
       return musicListManager.loadPublicPlaylist(loginManager.getCurrentUSer().getId());
    }

    /**
     * Method that loads the name of the playlist of which the owner corresponds to the current user.
     * @return LinkedList of Strings with the user's playlist's names
     */
    @Override
    public LinkedList<String> loadUserPlaylist(){
        return musicListManager.loadUserPlaylist(loginManager.getCurrentUSer().getId());
    }

    /**
     * Method that adds a song into an existing playlist in a certain position
     * @param playlistName Name of the playlist in which the song is added
     * @param songName  Object song which is added to the playlist
     */
    @Override
    public  void addSongPlaylist(String playlistName, String  songName){
        musicListManager.addSongPlaylist(playlistName, songName, loginManager.getCurrentUSer().getId());
    }

    /**
     * Loads all songs in the Data-base
     * @return List of Songs
     */
    @Override
    public List<Song> loadAllMusic(){
        return musicListManager.loadAllMusic();
    }

    /**
     * Method that deletes the songs of a playlist
     * @param playlistName Name of the playlist were songs are going to be deleted
     * @param songName List of String with the name of the songs to be deleted from the playlist
     */
    @Override
    public void deleteSongPlaylist(String playlistName, List<String> songName){
        musicListManager.deleteSongPlaylist(playlistName, songName,  loginManager.getCurrentUSer().getId());
    }

    /**
     * Deletes an object Playlist
     * @param playlistName name of the playlist to delete
     */
    @Override
    public void deletePlaylist(String playlistName){
        musicListManager.deletePlaylist(playlistName);
    }

    /**
     * Creates a playlist
     * @param playlistName name of the playlist to be created
     */
    @Override
    public void createPlaylist(String playlistName){
        musicListManager.createPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    /**
     * Method that given a string, returns the playlist object which the string corresponds to the name of it
     * @param playlistName String corresponding to the name of the playlist to find
     * @return return the object Playlist corresponding of the name of the String. If the playlist was not found,
     * returns null.
     */
    @Override
    public Playlist findPlaylist(String playlistName){
        return musicListManager.findPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    /**
     * Method that by given de string corresponding to the name of an existing playlist, returns the list of
     * songs which belong to that playlist.
     * @param playlistName String corresponding to the playlist were the songs belong to
     * @return List of objects Song
     */
    @Override
    public List<Song> loadMusicPlaylist(String playlistName){
        return musicListManager.loadMusicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    /**
     * Method that by given a string name corresponding to the search of a song returns a list of possible matches
     * from the search
     * @param filterName String corresponding to the search the user has done
     * @return List of songs corresponding to the possible matches of the search
     */
    @Override
    public List<Song> loadSearchMusic(String filterName){
        return musicListManager.loadSearchMusic(filterName);
    }

    /**
     * Method to create a song and saved it in the data-base
     * @param name String  to song name
     */
    @Override
    public void createSong(String name, String artist, String album, String genre, String filePath){ //todo añadir los lyrics

        int time[] = musicManager.songTime(filePath);
        Song song = new Song(name, artist, album, genre, filePath, ".", time[0], time[1]);
        song.setIdOwne(loginManager.getCurrentUSer().getId());
        song.setOwne(loginManager.getCurrentUSer().getName());
        musicManager.createSong(song);
    }

    /**
     * Method that returns all genres objects in memory
     * @return List of objects Genres
     */
    @Override
    public List<Genre> loadStadistic(){
        return musicManager.loadStadistc();
    }

    /**
     * Method that earses the song corresponding to the String given
     * @param songName string corresponding to the song name to be deleted
     */
    @Override
    public void deleteUserAddedSong(String songName){ //TODO llamar a las funciones de eliminar cancion de playlist quizas n hace falta
        List<Song> songs = musicListManager.loadAllMusic();
        for (Song song: songs){
            if (songName.equals(song.getName())) {
                musicManager.deleteUserAddedSong(song);
                break;
            }
        }
    }

    /**
     * Method that looks for the song for which its name corresponds to the string given
     * @param songName String corresponding to the name of the song to look for
     * @return Object song that has been find. Null in case it was not found.
     */
    @Override
    public Song findSong(String songName){
        return musicManager.loadSongInformation(songName);
    }
    /**
     * Method that reproduces a song given a playlist name and the position.
     * @param playlistName String name of the playlist were the song to be reproduced belongs.
     * @param position Integer corresponding to the position inside the List of songs of the corresponding
     *      playlist.
     */
    public void playMusic(String playlistName, int position){
        List<Song> songs = new LinkedList<>();

        if (playlistName.equals(NOTPLAYLIST)){
            List<Song> oneSong= musicListManager.loadAllMusic();
            songs.add(oneSong.get(position));
            musicManager.playSong(false, songs, 0);

        } else {
            songs = musicListManager.loadMusicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
            musicManager.playSong(true, songs, position);
        }
    }

    /**
     * Method that reproduces the previous or next song of the current song been reproduced.
     * @param next integer responsible for setting the new song to be reproduced in n position above or under the
     * the current song being played. Next should be 1 or -1 in most cases. For Next equals to 0, the current
     * song is going to be played again. For different values, it will play a song n positions above ir under the current
     * song. For Next values that might be out of the Playlist range, it is not going to cause problems.
     */
    public void previusNextSong(int next){
        musicManager.previusNextSong(next);
    }
    /**
     * Method to pause the current song which is being played.
     */
    public void pausedSong(){
        musicManager.pausedSong();
    }

    /**
     * Method that looks a supposedly existing playlist
     * @param playlistName Playlist name to be find
     * @return boolean true in case the playlist exists. False for opposite case.
     */
    public boolean findPlaylistName(String playlistName){
        return musicListManager.findPlaylistName(playlistName, loginManager.getCurrentUSer().getId());
    }

    /**
     * Method that changes a playlist name
     * @param currentName String of the name of the playlist name that has to be found
     * @param newName String of the new name the playlist is going to have
     */
    public void changePlaylistName(String currentName, String newName){
        musicListManager.changePlaylistName(currentName, newName);
    }
    /**
     * Method that checks if a playlist is public or not by giving its corresponding name
     * @param playlistName String of the name of the playlist to check
     * @return boolean true in case the playlist is actually public. False in opposite case.
     */
    public boolean isPublicPlaylist(String playlistName) {
        return musicListManager.isPublicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    /**
     * Method that gives the name of the playlist were the user is currently using
     * @return String name of the current playlist
     */
    public String getCurrentPlaylist () {
        return musicListManager.getCurrentPlaylist();
    }
    /**
     * Method to set the current playlist to another one, corresponding to the String given
     * @param playlistName String of the name of the object playlist to set as current.
     */
    public void setCurrentPlaylist (String playlistName) {
        musicListManager.setCurrentPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    public List<Song> loadMusicOnePlaylist(String playlistName){
        return musicListManager.loadMusicOnePlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    public void moveSongsInPlaylist(String playlistName, int position, int upDown){
        musicListManager.moveSongInPlaylist(playlistName, position ,upDown, loginManager.getCurrentUSer().getId());
    }

    public void deleteSongAllPlaylist(String songName){
        musicListManager.deleteSongAllPlaylist(songName);
    }
}
