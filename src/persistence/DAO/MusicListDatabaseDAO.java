package persistence.DAO;

import business.entities.Playlist;
import business.entities.Song;
import persistence.MusicListDAO;
import persistence.SQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MusicListDatabaseDAO implements MusicListDAO {

    // recupera todas las playlist distintas del usuario
    // ******
    @Override
    public List<Playlist> loadPublicPlaylist(int user_id) {
        List<Playlist> playlist = new LinkedList<>();
        // recupero todas las listas por orden de alta (id)
        String query = "select * from v_playlist where id_usuario <> " + user_id+ "' order by playlist_name asc";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong("id_playlist");
                String name = resultSet.getString("playlist_name");
                long id_user = resultSet.getLong("id_usuario");
                String owner = resultSet.getString("owner");

                playlist.add(new Playlist(id, name, id_user,owner));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return playlist;

    /*
        List<Playlist> playlist = new LinkedList<>();
        String query = "SELECT id, name, id_usuario FROM listas_reproduccion";
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

     */
    }


    //recupera las playlist del usuario
    @Override
    public List<Playlist> loadUserPlaylist (int user_id) {
        List<Playlist> playlist = new LinkedList<>();
        String query = "Select * from v_playlista where id_usuario = '"+user_id+"' order by playlist_name asc";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong("id_playlist");
                String name = resultSet.getString("playlist_name");
                long id_user = resultSet.getLong("id_usuario");
                String owner = resultSet.getString("owner");

                playlist.add(new Playlist(id, name, id_user,owner));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return playlist;
    }

    // crea playlist de un usuario
    @Override
    public void createPlaylist(Playlist playlist, long id_user) {
        // no hay control de si ya existe el "playlist", permitir mismo nombre con distinto usuario ? o no

        String query = "INSERT INTO listas_reproduccion(nombre, id_usuario) VALUES ('" +
                //playlist.getId() + "," +
                //el getId no es necesario porque lo crea la propia bbdd, en realidad vendría con 0
                playlist.getName() + "," +
                id_user + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    //borra playlist del usuario y las canciones relacionadas en el playlist
    // debería solo borrarse el playlist si no hay canciones?? para decidir
    @Override
    public boolean deletePlaylist(Playlist playlist) {
        String query1 = "DELETE FROM listas_reproduccion WHERE id = " + playlist.getId();
        String query2 = "DELETE FROM lista_cancion WHERE id_lista = " + playlist.getId();

        SQLConnector.getInstance().deleteQuery(query1);
        SQLConnector.getInstance().deleteQuery(query2);

        return true;

        /*
        String query = "DELETE FROM listas_reproduccion WHERE nombre = '" + playlist.getName() + "';";
        SQLConnector.getInstance().deleteQuery(query);

        query = "DELETE FROM lista_cancion WHERE id_lista = '" + playlist.getId() + "';";
        SQLConnector.getInstance().deleteQuery(query);

         */
    }


    // HOME / PLAYLIST
    // es la lista de todas las canciones distintas que tiene en las playlist el usuario
    @Override
    public List<Song> loadMusicUser(int id_user) {
        List<Song> song = new LinkedList<>();
        String query = "select distinct name, listas_reproduccion.id_usuario as idOwnerLista, v_songs.* from lista_cancion\n" +
                "            inner join v_songs on lista_cancion.id_cancion = v_songs.idSong\n" +
                "            inner join listas_reproduccion on lista_cancion.id_lista = listas_reproduccion.id\n" +
                " where listas_reproduccion.id_usuario = "+ id_user +
                " order by name asc";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

            while (resultSet.next()) {
                int idSong = resultSet.getInt("idSong");
                String name = resultSet.getString("name");
                int idGenere = resultSet.getInt("id_genere");
                String genere = resultSet.getString("genere");
                int idAlbum = resultSet.getInt("id_album");
                String album = resultSet.getString("album");
                int idSinger = resultSet.getInt("idSinger");
                String singer = resultSet.getString("singer");
                int idOwner = resultSet.getInt("idOwner");
                String owner = resultSet.getString("Owner");
                String filePath = resultSet.getString("filepath");

                song.add(new Song(idSong, name, idGenere, genere, idAlbum, album, idSinger, singer, idOwner, owner, filePath));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return song;
    }

    // HOME / EXPLORER
    // lista de canciones de una playlist
    @Override
    public List<Song> loadMusicPlaylist(Playlist playlist) {
        List<Song> song = new LinkedList<>();
        String query = "select name, listas_reproduccion.id_usuario as idOwnerLista, v_songs.* from lista_cancion\n" +
                "            inner join v_songs on lista_cancion.id_cancion = v_songs.idSong\n" +
                "            inner join listas_reproduccion on lista_cancion.id_lista = listas_reproduccion.id\n" +
                " where lista_cancion.id_lista = "+ playlist.getId() + " order by orden";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

            while (resultSet.next()) {
                int idSong = resultSet.getInt("idSong");
                String name = resultSet.getString("name");
                int idGenere = resultSet.getInt("id_genere");
                String genere = resultSet.getString("genere");
                int idAlbum = resultSet.getInt("id_album");
                String album = resultSet.getString("album");
                int idSinger = resultSet.getInt("idSinger");
                String singer = resultSet.getString("singer");
                int idOwner = resultSet.getInt("idOwner");
                String owner = resultSet.getString("Owner");
                String filePath = resultSet.getString("filepath");

                song.add(new Song(idSong, name, idGenere, genere, idAlbum, album, idSinger, singer, idOwner, owner, filePath));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return song;
    }

    // todavía no existe la pantalla donde se usa
    // se usará para buscar una canción para añadir a la playlist
    // devuelven todas las canciones que existen para poder añadir
    @Override
    public List<Song> loadAllMusic() {
        List<Song> song = new LinkedList<>();
        String query = "SELECT distinct name from v_song";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

            while (resultSet.next()) {
                int idSong = resultSet.getInt("idSong");
                String name = resultSet.getString("name");
                int idGenere = resultSet.getInt("id_genere");
                String genere = resultSet.getString("genere");
                int idAlbum = resultSet.getInt("id_album");
                String album = resultSet.getString("album");
                int idSinger = resultSet.getInt("idSinger");
                String singer = resultSet.getString("singer");
                int idOwner = resultSet.getInt("idOwner");
                String owner = resultSet.getString("Owner");
                String filePath = resultSet.getString("filepath");

                song.add(new Song(idSong, name, idGenere, genere, idAlbum, album, idSinger, singer, idOwner, owner, filePath));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return song;
    }

    // añade una canción a la playlist
    // IGUAL interesa que siempre se añada a la posición y la rutina determine la posición ultima y después update posición
    @Override
    public boolean addSongPlaylist(Playlist playlist, Song song, int position) {
        String query = "INSERT INTO lista_cancion(id_lista, id_cancion, orden) VALUES ('" +
                playlist.getId() + "," +
                song.getIdSong() + "," +
                position + "')";

        SQLConnector.getInstance().insertQuery(query);

        return true;
    }

    // borra una canción de la playlist
    @Override
    public boolean deleteSongPlaylist(Playlist playlist, Song song) {
        String query = "DELETE FROM lista_cancion WHERE id_lista = " + playlist.getId() + " and id_cancion = " + song.getIdSong();

        SQLConnector.getInstance().deleteQuery(query);

        return true;

    }

    // actualiza una canción de la playlist... principalmente es actualizar el orden/posiición
    @Override
    public boolean updateSongPlaylist(Playlist playlist, Song song, int position) {
        String query = "UPDATE lista_cancion SET orden = " + position + " WHERE id_lista = " + playlist.getId() + " and id_cancion = " + song.getIdSong();

        SQLConnector.getInstance().updateQuery(query);

        return true;
    }

    //------------------ PENDIENTE REVISAR ---------------//

   /* @Override
    public void updatePlaylist(Playlist playlist) {
     //todo hacer aqui de borrar todas con la id de playlist y ponerlas de nuevo
        String query = "DELETE FROM listas_reproduccion WHERE id_lista = '" + playlist.getId() + "';";
        SQLConnector.getInstance().deleteQuery(query);

        for (int i = 0; i < playlist.getSongList().size() ; i++) {
            updatePlaylistAddSong(playlist, i);
        }
    }
*/
    /**
     * Metodo para añadir una cancion de una playlist ya existente
     //* @param playlist Objeto Playlist con la informcion de la playlist
     //* @param position posicion de la nueva cancion en el orden de reproduccion
     */
    /*private void updatePlaylistAddSong(Playlist playlist, int position) {

        String query = "INSERT INTO lista_cancion(id_lista, id_cancion, orden) VALUES ('" +
                playlist.getId() + "," +
                playlist.getSongList().get(position).getIdSong() + "," +
                position + "');";

        SQLConnector.getInstance().insertQuery(query);
    }
*/



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

    public void canciones_Playlist (int playlist) {

        //PreparedStatement ps = null;
        //String query = "select * from v_canciones where id_playlist = ?";
        String query = "select * from v_canciones";
        ResultSet rs = SQLConnector.getInstance().selectQuery(query);

        try {

            // recorremos el arrya y printamos, aunque lo que tendría que hacer es devolver el array

            while (rs.next()) {
                System.out.println(rs.getInt("id_cancion") + " " + rs.getString("titulo") + rs.getString("cantante"));
            }

        } catch (
                SQLException e) {
            System.err.println(e);
        }
    }


}
