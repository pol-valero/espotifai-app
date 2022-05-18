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

    /**
     *
     * @param song
     */
     void createSong(Song song);

    /**
     * Metodo para eliminar una cancion de la base de datos
     * @param songName
     */
     void deleteSong(String songName);

    /**
     * Metodo para subir estadisticas a la base de datos
     * @param stadistic List con la informacion de las estadisticas
     */
    public void updateStadistic(List<Genre> stadistic);

}
