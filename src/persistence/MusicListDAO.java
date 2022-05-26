package persistence;

import business.entities.Playlist;
import business.entities.Song;

import java.util.List;

public interface MusicListDAO  {

     List<Song> loadAllMusic();

     // recupera todas las playlist distintas del usuario
     List<Playlist> loadPublicPlaylist(int id_user);

     List<Song> loadUserAddedSongs(int id_user);

    List<Song> loadMusicPlaylist(Playlist playlist);

    // añade una canción a la playlist
    void addSongPlaylist(int idPlaylist, int idSong, int position);

    public boolean deleteSongPlaylist(Playlist playlist, Song song);

    public boolean moveSongUp(Playlist playlist, Song song);

    public boolean moveSongDown(Playlist playlist, Song song);

    /**
     * Metodo para crear una nueva playlist
     * @param playlistName Nombre de la playlist
     */
     void createPlaylist(String playlistName, int id_user);

    /**
     * Metodo para eliminar una cancion de una playlist ya existente
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
     //void updatePlaylist(Playlist playlist);


    /**
     * Metodo para eliminar una playlist de la base de datos
     * @param playlistName Objeto Playlist con la informcion de la playlist
     */
     void deletePlaylist(String playlistName);

    // void canciones_Playlist (int playlist);

    List<Playlist> loadUserPlaylist (int user_id);

     void changePlaylistName(String currentName, String newName);

    int idSongInPlaylist(int idPlaylist);

    List<Song>  loadMusicOnePlaylist(int idPlaylist);

    void moveSongsInPlaylist(int idPlaylist, int idSong1, int idSong2, int idOrder1, int idOrder2);

    void deleteSongAllPlaylist(int idSong);
}
