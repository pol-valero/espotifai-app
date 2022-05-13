package persistence;

import business.entities.Playlist;
import business.entities.Song;

import java.util.List;

public interface MusicListDAO {

     List<Song> loadAllMusic();

     List<Playlist> loadAllPlaylist();

    /**
     * Metodo para crear una nueva playlist
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
     void createPlaylist(Playlist playlist, long id_user);

    /**
     * Metodo para eliminar una cancion de una playlist ya existente
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
     void updatePlaylist(Playlist playlist);


    /**
     * Metodo para eliminar una playlist de la base de datos
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
     void deletePlaylist(Playlist playlist);

     void canciones_Playlist (int playlist);

     void playlistUser (int user_id);
}
