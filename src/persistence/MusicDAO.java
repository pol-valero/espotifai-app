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

    /***
     * Method for creating a song, i.e. inserting a song
     * @param song we receive the song to insert
     */
     void createSong(Song song);

    /***
     * Method to delete a song from a list
     * @param song we receive the song to insert
     */
     void deleteSong(Song song);

    /***
     * Method for updating statistics to be displayed later on
     */
     void updateStadistic(String genreName, int amount);

    /***
     * Method para crear las estadísticas muscicales
     * @param genreName: the name of the genre
     */
     void createStadistic(String genreName);

    /***
     * Method for deleting a class
     * @param genreName: name of the genre we intend to delete
     */
     void deleteGenre(String genreName);

    /***
     * Method to create an album, in which songs are to be added
     * @param album: album into which we want to insert the song in question
     * @param idSinger: the id of the singer to know the one we want to refer to
     */
     void createAlbum(String album, int idSinger);

    /***
     * Method to load an album and we will do it by means of its title
     * @param albumName: album into which we want to insert the song in question
     */
     int loadIdAlbum(String albumName);

    /***
     * Method to create a singer that we are going to insert from a query
     * to which we will introduce the name of the singer.
     * @param singerName: the name of the singer to whom we are referring to
     */
     void createSinger(String singerName);

    /***
     * Method to load a singer and we will do it from the id of the singers.
     * @param singerName: the name of the singer to whom we are referring to
     */
     int loadIdSinger(String singerName);

}
