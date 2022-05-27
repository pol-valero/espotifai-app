package persistence.DAO;

import business.entities.Genre;
import business.entities.Song;
import persistence.MusicDAO;
import persistence.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MusicDatabaseDAO implements MusicDAO {

    /***
     * Method to load the statistics which will show the different musical genres
     * that exist in the program and the number of songs.
     */
    @Override
    public List<Genre> loadStadistic() {
        List<Genre> genres = new LinkedList<>();
        String query = "SELECT id, genero ,cantidad FROM genero;";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            while (resultSet.next()) {
                int idgenre = resultSet.getInt("id");
                String genre = resultSet.getString("genero");
                int amount = resultSet.getInt("cantidad");

                genres.add(new Genre(idgenre, genre,amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    /***
     * Method for creating a song, i.e. inserting a song
     * @param song we receive the song to insert
     */
    @Override
    public void createSong(Song song) { //cambiar al campo pathFile en char en la base
        String query = "INSERT INTO canciones (titulo, id_genero, id_album, id_usuario," +
                "filePath,timeMinutes, timeSec,lyrics) VALUES ('"
                + song.getName() + "', '"
                + song.getIdGenre() + "', '"
                + song.getIdAlbum() + "', '"
                + song.getIdOwne() + "', '"
                + song.getFilePath() + "', '"
                + song.getMinutes() + "', '"
                + song.getSeconds() + "', '"
                + song.getLyrics() +
                "');";

        SQLConnector.getInstance().insertQuery(query);

    }

    /***
     * Method to delete a song from a list
     * @param song we receive the song to insert
     */
    @Override
    public void deleteSong(Song song) {

        String query = "DELETE FROM lista_cancion WHERE id_cancion = '"
                + song.getIdSong() + "';";
        SQLConnector.getInstance().deleteQuery(query);

        query = "DELETE FROM canciones WHERE titulo = \""
                + song.getName() + "\";";

        SQLConnector.getInstance().deleteQuery(query);
    }

    /***
     * Method for updating statistics to be displayed later on
     */
    @Override
    public void updateStadistic(String genreName, int amount) {

        String query = "UPDATE genero SET cantidad = '" + amount + "' WHERE  genero = \"" + genreName+ "\";";
        SQLConnector.getInstance().updateQuery(query);

    }

    /***
     * Method para crear las estadísticas muscicales
     * @param genreName: the name of the genre
     */
    @Override
    public void createStadistic(String genreName){
        String query = "INSERT INTO genero (genero, cantidad ) VALUES ('"
                + genreName + "', '1'" + ");";

        SQLConnector.getInstance().insertQuery(query);
    }

    /***
     * Method for deleting a class
     * @param genreName: name of the genre we intend to delete
     */
    @Override
    public void deleteGenre(String genreName){
        String query = "DELETE From genero Where genero = \"" + genreName + "\";";
        SQLConnector.getInstance().deleteQuery(query);
    }

    /***
     * Method to create an album, in which songs are to be added
     * @param album: album into which we want to insert the song in question
     * @param idSinger: the id of the singer to know the one we want to refer to
     */
    @Override
    public void createAlbum(String album, int idSinger){
        String query = "INSERT INTO album (titulo, id_cantante) VALUES ('"
                + album + "', '"+ idSinger + "'" + ");";

        SQLConnector.getInstance().insertQuery(query);
    }

    /***
     * Method to load an album and we will do it by means of its title
     * @param albumName: album into which we want to insert the song in question
     */
    @Override
    public int loadIdAlbum(String albumName){

        String query = "SELECT id FROM album WHERE titulo = \"" + albumName + "\";";
        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
            if (resultSet != null) {
                resultSet.next();
                return resultSet.getInt("id");
            }
            return 0;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("ERROR album");
            return 0;
        }

    }

    /***
     * Method to create a singer that we are going to insert from a query
     * to which we will introduce the name of the singer.
     * @param singerName: the name of the singer to whom we are referring to
     */
    @Override
    public void createSinger(String singerName){
        String query = "INSERT INTO cantantes (nombre) VALUES ('"
                + singerName + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    /***
     * Method to load a singer and we will do it from the id of the singers.
     * @param singerName: the name of the singer to whom we are referring to
     */
    @Override
    public int loadIdSinger(String singerName){
        String query = "SELECT id FROM cantantes Where nombre = \"" + singerName + "\";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            if (resultSet != null) {
                resultSet.next();
                //System.out.println("la id vale = "+ resultSet.getInt("id"));
                return resultSet.getInt("id");
            }
           return 0;
        } catch (SQLException e) {
           //e.printStackTrace();
            System.out.println("error singer");
            return 0;
        }
    }
}
