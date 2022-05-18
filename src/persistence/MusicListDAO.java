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

    public boolean addSongPlaylist(Playlist playlist, Song song, int position);

    public boolean deleteSongPlaylist(Playlist playlist, Song song);

    public boolean updateSongPlaylist(Playlist playlist, Song song, int position);
    /**
     * Metodo para crear una nueva playlist
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
     void createPlaylist(Playlist playlist, long id_user);

    /**
     * Metodo para eliminar una cancion de una playlist ya existente
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
     //void updatePlaylist(Playlist playlist);


    /**
     * Metodo para eliminar una playlist de la base de datos
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
     boolean deletePlaylist(Playlist playlist);

     void canciones_Playlist (int playlist);

    List<Playlist> loadUserPlaylist (int user_id);
}
