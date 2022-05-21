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

    @Override
    public void createSong(Song song) { //cambiar al campo pathFile en char en la base
        String query = "INSERT INTO v_canciones (titulo, id_genero, id_album, id_usuario," +
                "pathFile) VALUES ('"
                + song.getName() + ","
                + song.getIdGenre() + ","
                + song.getAlbum() +
                + song.getIdOwne() +
                //+ song.getFilePath() +
                "');";

        SQLConnector.getInstance().insertQuery(query);

    }

    @Override
    public void deleteSong(Song song) {

        String query = "DELETE FROM lista_canciones WHERE id_cancion = "
                + song.getIdSong() + ";";
        SQLConnector.getInstance().deleteQuery(query);

        query = "DELETE FROM canciones WHERE titulo = "
                + song.getName() + ";";

        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public void updateStadistic(String genreName, int amount) {
        String query = "UPDATE genero SET cantidad = " + amount + "Where genero = "+ genreName + ";";
        SQLConnector.getInstance().updateQuery(query); //todo probar si va bien

    }

    @Override
    public void createStadistic(String genreName){
        String query = "INSERT INTO genero (genero, cantidad ) VALUES ('"
                + genreName + 1 + "');"; //todo quizas el 1 va entre comillas

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void deleteGenre(String genreName){
        String query = "DELETE From genero Where genero = " + genreName + ";";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public void createAlbum(String album, int idSinger){
        String query = "INSERT INTO genero (titulo, id_cantante) VALUES ('"
                + album + idSinger + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public int loadIdAlbum(String albumName){
        String query = "SELECT id FROM album Where titulo =" + albumName + ";" ;
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            return  resultSet.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void createSinger(String singerName){
        String query = "INSERT INTO cantantes (nombre) VALUES ('"
                + singerName + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public int loadIdSinger(String singerName){
        String query = "SELECT id FROM cantantes Where nombre =" + singerName + ";" ;
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            return resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
