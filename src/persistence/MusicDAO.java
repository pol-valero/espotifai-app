package persistence;

import business.entities.Genre;
import business.entities.Song;

import java.util.List;

public interface MusicDAO {

    /**
     * Metodo para obtener las estadisticas de genero de la base de datos
     * @return List de tipo Genre con la informacion
     */
     List<Genre> loadStadistic();

     void createSong(Song song);

     void deleteSong(Song song);

     void updateStadistic(String genreName, int amount);

     void createStadistic(String genreName);

     void deleteGenre(String genreName);

     void createAlbum(String album, int idSinger);

     int loadIdAlbum(String albumName);

     void createSinger(String singerName);

     int loadIdSinger(String singerName);

}
