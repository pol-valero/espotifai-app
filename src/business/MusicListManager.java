package business;

import business.entities.Playlist;
import business.entities.Song;
import persistence.DAO.MusicListDatabaseDAO;
import persistence.MusicListDAO;

import java.util.LinkedList;
import java.util.List;

/**
 * Class capable of managing everything related to the functionalities of a playlist. Main class when it comes to communication
 * between upper layers and persistence layer
 */
public class MusicListManager {
    /**
     * Object will be bringing method to apply functionalities to the persistence layer
     */
    private MusicListDAO  musicListDAO = new MusicListDatabaseDAO();


    /**
     * Name of the current playlist
     */
    private String currentPlaylistName;

    /**
     *Method used for getting all playlist which the current user is not the owner.
     * @param idUser Id of the current user
     * @return Linkedlist of String corresponding to the name of all public playlist
     */
    public LinkedList<String> loadPublicPlaylist(int idUser){
        LinkedList<String> publicPlaylistNames = new LinkedList<>();
        for (Playlist playlist: musicListDAO.loadPublicPlaylist(idUser)) {
            publicPlaylistNames.add(playlist.getName());
        }
        return  publicPlaylistNames;
    }

    /**
     *Method used for getting all playlist which the current user is the owner.
     * @param idUser Id of the current user
     * @return Linkedlist of String corresponding to the name of all playlist of the user
     */
    public LinkedList<String> loadUserPlaylist(int idUser){
        LinkedList<String> userPlaylistNames = new LinkedList<>();
        for (Playlist playlist: musicListDAO.loadUserPlaylist(idUser)) {
            userPlaylistNames.add(playlist.getName());
        }
        return userPlaylistNames;
    }

    /**
     * Method to load all songs of the data base
     * @return List of songs corresponding to all the songs of the system
     */
    public List<Song> loadAllMusic(){
        return musicListDAO.loadAllMusic();
    }

    /**
     * Method to add a list of songs into a playlist and save it in persistence layer
     * @param playlistName Name of the playlist where the songs will be saved
     * @param songNameList List of objects songs which will be saved
     * @param idUser id of the current user. This parameter is relevant as only the current user is able to add songs to
     *               their own playlists.
     */
    public void addSongPlaylist(String playlistName, List<String> songNameList, int idUser){
        Playlist playlist = findUserPlaylist(playlistName, idUser);
        for (int i = 0; i < songNameList.size(); i++) {
            String songName = songNameList.get(i);
            int positon = musicListDAO.idSongInPlaylist(playlist.getId());
            positon++;

            List<Song> songs = loadAllMusic();
            for(Song newSong: songs){
                if (songName.equals(newSong.getName())) {
                    musicListDAO.addSongPlaylist(playlist.getId(), newSong.getIdSong(), positon);
                }
            }
        }

    }

    /**
     * Method to delete a list of songs into a playlist and delete it in persistence layer
     * @param playlistName Name of the playlist where the songs will be deleted
     * @param songName List of objects songs which will be deleted
     * @param idUser id of the current user. This parameter is relevant as only the current user is able to delete songs of
     *               their own playlists.
     */
    public void deleteSongPlaylist(String playlistName, List<String> songName, int idUser){
        Playlist playlist = findUserPlaylist(playlistName, idUser);
        List<Song> songs = findSong(songName);

        if (songs.size() > 0){
            for (Song song: songs){
                musicListDAO.deleteSongPlaylist(playlist, song);
            }
        }
    }

    /**
     * Method that finds the object playlist by giving a name to search and the user id (owner). It
     * will search first in the users playlist and then in the public ones.
     * @param playlistName String name of the playlist to find
     * @param idUser Integer Id of the user
     * @return Playlist that has been found. Null in case it has not.
     */
    public Playlist findPlaylist(String playlistName, int idUser){
        Playlist playlist = findUserPlaylist(playlistName, idUser);

        if (playlist != null) {
            return playlist;
        } else {
            playlist = findOnePublicPlaylist(playlistName, idUser);
            if(playlist != null){
                return playlist;
            }
        }
        return null;
    }

    /**
     * Method to delete a playlist in persistence
     * @param playlistName String name of the playlist to delete
     */
    public void deletePlaylist(String playlistName){
            musicListDAO.deletePlaylist(playlistName);
    }

    /**
     * Method to create a playlist in persistence
     * @param playlistName Playlist name
     * @param idUser Id of the user who has created the playlist (owner)
     */
    public void createPlaylist(String playlistName, int idUser){
            musicListDAO.createPlaylist(playlistName, idUser);
    }

    /**
     * Method to get a list of objects song corresponding to a playlist
     * @param playlistName Name of the playlist
     * @param ideUser Integer corresponding the Id of the user
     * @return List of objects songs
     */
    public List<Song> loadMusicPlaylist(String playlistName, int ideUser){
        Playlist playlist = findPlaylist(playlistName, ideUser);
        if(playlist != null){
            return musicListDAO.loadMusicPlaylist(playlist);
        } else {
            switch (playlistName) {
                case "MySongs":
                    return loadAllUserSongs(ideUser);
                case "AllSongs":
                    return loadAllMusic();
            }
        }
        return null;
    }

    /**
     * Method that loads a list og songs which name matches a given string
     * @param filterName String name of reference to do the research
     * @return List of Songs whose name matches
     */
    public List<Song> loadSearchMusic(String filterName){
        List<Song> songs = musicListDAO.loadAllMusic();
        List<Song> searchSong = new LinkedList<>();

        if (songs.size() != 0 ){
            for(Song song: songs){
                if(filterName.equalsIgnoreCase(song.getName()) || filterName.equalsIgnoreCase(song.getGenre())
                        || filterName.equalsIgnoreCase(song.getAlbum()) || filterName.equalsIgnoreCase(song.getSinger())
                        || filterName.equalsIgnoreCase(song.getOwne())) {
                    searchSong.add(song);
                }
            }
            return searchSong;
        }
        return null;
    }

    /**
     * Method that finds a user's playlist in persistence
     * @param playlistName Playlist name to find
     * @param idUser Id of the user
     * @return Playlist which its name and the given string matches
     */
    private Playlist findUserPlaylist(String playlistName, int idUser){
        List<Playlist> playlists = new LinkedList<>();
         playlists =  musicListDAO.loadUserPlaylist(idUser);

            for (Playlist playlist: playlists) {
                if (playlistName.equals(playlist.getName())) {
                    return playlist;
                }
            }
        return null;
    }

    /**
     * Method to find a songs
     * @param songName List of name of songs to find
     * @return List of objects songs which name mathces the given list of strings
     */
    private List<Song> findSong(List<String> songName){
        List<Song> songs = musicListDAO.loadAllMusic();
        List<Song> newSong = new LinkedList<>();

        if (songs.size() != 0 && songName.size() != 0) {
            for (String name: songName) {
                for (Song song : songs) {
                    if (name.equals(song.getName())) {
                        newSong.add(song);
                    }
                }
            }
        } else {
            return null;
        }

        return newSong;
    }

    /**
     * Method to check is a Playlist object exists with the name given to the method
     * @param playlistName String name of the playlist to find
     * @param idUser Integer Id user of the current user
     * @return boolean true when there has been a match, false in opposite case
     */
    public boolean findPlaylistName(String playlistName, int idUser){

        if(findPlaylist(playlistName, idUser) != null){
            return true;
        }
        return false;
    }

    /**
     * Method to find one public playlist
     * @param playlistName String name of the playlist to find
     * @param idUser Integer id user
     * @return Object Playlist corresponding to the playlist who's name macthes the given string. In
     * case there is no match, returns null.
     */
    private Playlist findOnePublicPlaylist(String playlistName , int idUser){
        List<Playlist> playlistList = musicListDAO.loadPublicPlaylist(idUser);

        if (playlistList.size() != 0) {
            for (Playlist playlist: playlistList) {
                if (playlistName.equals(playlist.getName())) {
                    return playlist;
                }
            }
        }
        return null;
    }

    /**
     * Method to change a playlist name
     * @param currentName String current playlist name to change
     * @param newName String of the new name of the playlist
     */
    public void changePlaylistName(String currentName, String newName){
        musicListDAO.changePlaylistName(currentName, newName);
    }

    /**
     * Method that checks if a plyalist of public (the current user is not the owner) or not
     * @param playlistName String name of playlist to find and check
     * @param idUser Id of the current user
     * @return boolean true in case the playlist with matching names is public, false in the opposite case
     */
    public boolean isPublicPlaylist(String playlistName, int idUser) {
       Playlist playlist = findPlaylist(playlistName, idUser);
       if (playlist.getUserId() == idUser) {
           return false;
       } else {
           return true;
       }
    }

    /**
     * Method to load all songs of the current user
     * @param idUser Id of the user
     * @return List of objects songs which the current user is the owner
     */
    private List<Song> loadAllUserSongs (int idUser) {
        List<Song> userSongs = new LinkedList<>();
        List<Song> allSongs = loadAllMusic();
            for(Song song: allSongs) {
                if(song.getIdOwne() == idUser) {
                    userSongs.add(song);
                }
            }
        return userSongs;
    }

    /**
     * Method to know the current playlist the user is using
     * @return String name of the current playlist
     */
    public String getCurrentPlaylist () {
        return currentPlaylistName;
    }

    /**
     * Method to set the current playlist the user is using to a certain name
     * @param playlistName String name of the new current playlist
     */
    public void setCurrentPlaylist (String playlistName) {
        currentPlaylistName = playlistName;
    }

    /**
     * Method to load all songs of an specific playlist
     * @param playlistName String name of the playlist
     * @param ideUser Integer Id of the current user
     * @return List of objects songs
     */
    public List<Song> loadMusicOnePlaylist(String playlistName, int ideUser){

        Playlist playlist = findUserPlaylist(playlistName, ideUser);
        System.out.println(playlist.getId());

        return musicListDAO.loadMusicOnePlaylist(playlist.getId());
    }

    /**
     * Method to change the position of a song in a playlist and save it in persistence
     * @param playlistName Name of the playlist
     * @param position Integer corresponding to the previous position of the song
     * @param upDown Integer of who many positions above or under the song must be set
     * @param ideUser Integer id of the current user
     */
    public void moveSongInPlaylist(String playlistName, int position, int upDown, int ideUser){
        Playlist playlist = findUserPlaylist(playlistName, ideUser);
        List<Song> songs = musicListDAO.loadMusicOnePlaylist(playlist.getId());
        int idSong1 = songs.get(position).getIdSong();
        int moveSong1 = songs.get(position).getOrden();
        int moveSong2 = songs.get(position - upDown).getOrden();
        int idSong2 = songs.get(position - upDown).getIdSong();

        musicListDAO.moveSongsInPlaylist(playlist.getId(), idSong1, idSong2, moveSong1,moveSong2);

    }

    /**
     * Method to delete a song from all existing playlist where it belonged to
     * @param songName String name of the song
     */
    public void deleteSongAllPlaylist(String songName){
        List<Song> songs = loadAllMusic();
        for (Song song: songs){
            if (songName.equals(song.getName())){
                musicListDAO.deleteSongAllPlaylist(song.getIdSong());
            }
        }
    }

    /**
     * Method to load all songs which have not been added yet to a certain playlist
     * @param playlistName Name of the playlist
     * @param idUser Integer Id of the user
     * @return List of songs corresponding to the songs that have not been added yet
     */
    public List<Song> loadAllNotAlreadyAddedSong(String playlistName, int idUser){
        List<Song> songPlaylist = loadMusicPlaylist(playlistName, idUser);

        if (songPlaylist.size() != 0){
            List<Song> songList = new LinkedList<>();

            for (Song allSong: loadAllMusic()) {
                boolean found = false;

                for (Song songP: songPlaylist) {
                    if (allSong.getIdSong() == songP.getIdSong()) {
                        found = true;
                    }
                }
                if (!found) {
                    songList.add(allSong);
                }
            }
            return songList;
        }
        return loadAllMusic();
    }

    public boolean songExistsInPlaylist(String playlistName, String songName, int userId) {
        LinkedList<Song> playlistSongs = (LinkedList<Song>) loadMusicPlaylist(playlistName, userId);
        for (Song song: playlistSongs) {
            if(song.getName().equals(songName)) {
                return true;
            }
        }
        return false;
    }
}
