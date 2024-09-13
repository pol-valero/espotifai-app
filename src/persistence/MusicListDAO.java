package persistence;

import business.entities.Playlist;
import business.entities.Song;

import java.util.List;

public interface MusicListDAO  {

    /***
     * method that will allow to load all the songs by selecting them from the songs view.
     */
     List<Song> loadAllMusic();

    /***
     * Method to load other users playlists from a query that is going to select us from a view
     * @param id_user we receive the user id
     */
    List<Playlist> loadPublicPlaylist(int id_user);



    /***
     * Method to list the songs in a playlist and this will be displayed in the Home view in the EXPLORE section.
     * @param playlist we receive the playlist
     */
    List<Song> loadMusicPlaylist(Playlist playlist);

    /***
     * Method to add songs to a playlist
     * @param idPlaylist we receive the id of the playlist we want to work with
     * @param idSong we receive the id of the song
     * @param position song position
     */
    void addSongPlaylist(int idPlaylist, int idSong, int position);

    /***
     * Method to delete songs from a playlist
     * @param playlist we receive the id of the playlist we want to work with
     * @param song we received the song
     */
    public boolean deleteSongPlaylist(Playlist playlist, Song song);

    /**
     * Metodo para crear una nueva playlist
     * @param playlistName Nombre de la playlist
     */
     void createPlaylist(String playlistName, int id_user);

    /**
     * Metodo para eliminar una playlist de la base de datos
     * @param playlistName Objeto Playlist con la informcion de la playlist
     */
     void deletePlaylist(String playlistName);

    /***
     * Method for loading user-created Playlists from a query that is going to select us from a view
     * @param user_id we receive the user id
     */
    List<Playlist> loadUserPlaylist (int user_id);

    /***
     * Method to change the name of the active user
     * @param currentName: current user name
     * @param newName: new name the user will receive
     */
    void changePlaylistName(String currentName, String newName);

    /***
     * Method to get the id of a song belonging to a Playlist
     * @param idPlaylist we receive the id of the playlist we want to work with
     */
    int idSongInPlaylist(int idPlaylist);

    /***
     * Method to load music from a specific playlist
     * @param idPlaylist we receive the id of the playlist we want to work with
     */
    List<Song>  loadMusicOnePlaylist(int idPlaylist);

    /***
     * Method to move the songs to a different position
     * @param idPlaylist we receive the id of the playlist we want to work with
     * @param idSong1 id of the first song to move
     * @param idSong2 id of the second song to move
     * @param idOrder1 order of the first song
     * @param idOrder2 order of the second song
     */
    void moveSongsInPlaylist(int idPlaylist, int idSong1, int idSong2, int idOrder1, int idOrder2);



}
