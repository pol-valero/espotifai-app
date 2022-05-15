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
    public void createSong(Song song) { //la columna cancion hace referencia al path
      /*  String query = "INSERT INTO usuario (id, titulo, id_genero, id_album, id_usuario," +
                "tiempo_segundos, cancion) VALUES ('"
                + song.getIdSong() + "', '" +
                song.getName() + "', '" +
                song.getGenre() + "', '" +
                user.getPassword() + "');";

        SQLConnector.getInstance().insertQuery(query);
       */
    }

    @Override
    public void deleteSong(String songName) {

        int idGenre = idGenre(songName);

        String query = "DELETE FROM lista_canciones WHERE id_cancion = "
                + idSong(songName) + ";";
        SQLConnector.getInstance().deleteQuery(query);

        query = "DELETE FROM canciones WHERE titulo = "
                + songName + ";";
        SQLConnector.getInstance().deleteQuery(query);

        List<Genre> genreList = loadStadistic();
        for (Genre genre: genreList) {
            if (genre.getId() == idGenre ){
                genre.setAmount(genre.getAmount() - 1);
            }
        }
        createStadistic(genreList);
    }

    @Override
    public void createStadistic(List<Genre> stadistic) {
        String query = "TRUNCATE genero";
        SQLConnector.getInstance().updateQuery(query); //todo probar si va bien
        for (Genre genre : stadistic) {
            query = "INSERT INTO genero(id, genero, cantidad) VALUES ('" +
                    genre.getId() + "', '" +
                    genre.getGenre() + "', '" +
                    genre.getAmount() + "');";
            SQLConnector.getInstance().insertQuery(query);
        }
    }

    /**
     * Metodo para obteber la id de una cancion a traves de su nombre
     * @param songName String con el nombre de la cancion
     * @return int con el identificador de la cancion
     */
    private int idSong(String songName){
        String query = "SElECT id FROM canciones WHERE titulo = " + songName + ";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        int id = -1;
        try {
            id = resultSet.getInt("titulo");
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return id;
    }

    /**
     * MEtodo para obtener la id del genero a partir del nombre de la cancion
     * @return int con el identificador de la cancion
     */
    private int idGenre(String songName){
        String query = "SELECT id_genero FROM canciones WHERE titulo = "
                + songName + ";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            return resultSet.getInt("id_genero");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
