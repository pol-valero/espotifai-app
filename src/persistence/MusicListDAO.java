package persistence;

import business.entities.Playlist;
import business.entities.Song;

import java.util.List;

public interface MusicListDAO {

    public List<Song> loadAllMusic();

    public List<Playlist> loadAllPlaylist();

    /**
     * Metodo para crear una nueva playlist
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
    public void createPlaylist(Playlist playlist, long id_user);

    /**
     * Metodo para eliminar una cancion de una playlist ya existente
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
    public void updatePlaylist(Playlist playlist);


    /**
     * Metodo para eliminar una playlist de la base de datos
     * @param playlist Objeto Playlist con la informcion de la playlist
     */
    public void deletePlaylist(Playlist playlist);
}
