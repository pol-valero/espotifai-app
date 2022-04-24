package persistence.DAO;

import business.entities.Playlist;
import business.entities.Song;
import persistence.MusicListDAO;
import persistence.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MusicListDatabaseDAO implements MusicListDAO {


    @Override
    public List<Playlist> loadAllPlaylist() {
        List<Playlist> playlist = new LinkedList<>();
        String query = "SELECT id, name, id_usuario FROM listas_reproduccion;";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long id_user = resultSet.getLong("id_usuario");

                playlist.add(new Playlist(id, name, id_user));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return playlist;
    }

    @Override
    public void createPlaylist(Playlist playlist, long id_user) {
        String query = "INSERT INTO listas_reproduccion(id, nombre, id_usuario) VALUES ('" +
                playlist.getId() + "," +
                playlist.getName() + "," +
                id_user + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        String query = "DELETE FROM listas_reproduccion WHERE nombre = '" + playlist.getName() + "';";
        SQLConnector.getInstance().deleteQuery(query);

        query = "DELETE FROM lista_cancion WHERE id_lista = '" + playlist.getId() + "';";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
     //todo hacer aqui de borrar todas con la id de playlist y ponerlas de nuevo
        String query = "DELETE FROM listas_reproduccion WHERE id_lista = '" + playlist.getId() + "';";
        SQLConnector.getInstance().deleteQuery(query);

        for (int i = 0; i < playlist.getSongList().size() ; i++) {
            updatePlaylistAddSong(playlist, i);
        }
    }

    /**
     * Metodo para añadir una cancion de una playlist ya existente
     * @param playlist Objeto Playlist con la informcion de la playlist
     * @param position posicion de la nueva cancion en el orden de reproduccion
     */
    private void updatePlaylistAddSong(Playlist playlist, int position) {

        String query = "INSERT INTO lista_cancion(id_lista, id_cancion, orden) VALUES ('" +
                playlist.getId() + "," +
                playlist.getSongList().get(position).getIdSong() + "," +
                position + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public List<Song> loadAllMusic() {
        List<Song> song = new LinkedList<>();
        String query = "SELECT id, titulo, id_genero, " +
                "id_album, id_usuario FROM lista_cancion;";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            while (resultSet.next()) {
                long idSong = resultSet.getLong("id");
                String name = resultSet.getString("titulo");
                long idGenre = resultSet.getLong("id_genero");
                long idAlbum = resultSet.getLong("id_album");
                long idUser = resultSet.getLong("id_usuario");

                String singer = singerName(idAlbum);
                String album = albumName(idAlbum);
                String owne = owneName(idUser);
                String genre = genre(idGenre);
                song.add(new Song(name, idSong, genre,
                        album, singer, owne, idUser));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return song;
    }

    /**
     * Metodo para obtener el nombre del cantante a traves de la identificador de la cancion
     * @param idAlbum long indentificador del album de la cancion
     * @return String con el nombre del cantante
     */
    private String singerName(long idAlbum) {

        String query = "SELECT id_cantante FROM album WHERE id = "
                + idAlbum + ";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        String singer = null;
        try {
            long idSinger = resultSet.getLong("id_cantante");
            singer = singerNameInCantantes(idSinger);
        } catch (SQLException exception) {
            exception.getErrorCode();
        }

        return singer;
    }

    /**
     * Metodo para obtener el nombre del cantante de una cancion a traves del
     *  identificador de la cancion
     * @param idSinger long con el identificador de la cancion
     * @return String con el nombre del cantante
     */
    private String singerNameInCantantes(long idSinger){
        String query = "SELECT nombre FROM cantantes WHERE id = " +
                idSinger + ";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        String singer = null;

        try {
            singer = resultSet.getString("nombre");
        } catch (SQLException exception) {
            exception.getErrorCode();
        }
        return singer;
    }

    /**
     * Metodo para obtener el nombre del album a traves de su identificador
     * @param idAlbum long con el identificador del album
     * @return String con el nombre del album
     */
    private String albumName(long idAlbum) {
        String query = "SELECT titulo FROM album WHERE id = "
                + idAlbum + ";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        String album = null;
        try {
            album = resultSet.getString("id");
        } catch (SQLException exception) {
            exception.getErrorCode();
        }

        return album;
    }

    /**
     * Metodo para obtener el nombre del usuario a traves de su id
     * @param idUser long con el identificador del usuario
     * @return String con el nombre del usuario
     */
    private String owneName(long idUser) {
        String query = "SELECT nombre FROM ususario WHERE id = "
                + idUser + ";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        String owne = null;
        try {
            owne = resultSet.getString("id");
        } catch (SQLException exception) {
            exception.getErrorCode();
        }

        return owne;
    }

    /**
     * Metodo para obtener el genero de una cancion a traves del identificador de genero
     * @param idGenre long identificador de genero
     * @return String con el genero
     */
    private String genre(long idGenre) {
        String query = "SELECT genero FROM genero WHERE id = "
                + idGenre + ";";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        String genre = null;
        try {
            genre = resultSet.getString("id");
        } catch (SQLException exception) {
            exception.getErrorCode();
        }

        return genre;
    }

}
