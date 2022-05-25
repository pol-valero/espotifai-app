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

    // recupera todas las playlist distintas del usuario
    // ******
    @Override
    public List<Playlist> loadPublicPlaylist(int user_id) {
        List<Playlist> playlist = new LinkedList<>();
        // recupero todas las listas por orden de alta (id)
        String query = "select * from v_playlist where id_usuario <> '" + user_id + "' order by playlist_name asc";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id_playlist");
                    String name = resultSet.getString("playlist_name");
                    long id_user = resultSet.getLong("id_usuario");
                    String owner = resultSet.getString("owner");

                    playlist.add(new Playlist(id, name, id_user, owner));
                }
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
        String query = "select * from v_playlista where id_usuario = \"" + user_id + "\" order by playlist_name asc";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id_playlist");
                    String name = resultSet.getString("playlist_name");
                    long id_user = resultSet.getLong("id_usuario");
                    String owner = resultSet.getString("owner");

                    playlist.add(new Playlist(id, name, id_user, owner));
                }
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return playlist;
    }

    // crea playlist de un usuario
    @Override
    public void createPlaylist(String playlistName, int id_user) {
        // no hay control de si ya existe el "playlist", permitir mismo nombre con distinto usuario ? o no

        String query = "INSERT INTO listas_reproduccion(nombre, id_usuario) VALUES ('" +
                //playlist.getId() + "," +
                //el getId no es necesario porque lo crea la propia bbdd, en realidad vendría con 0
                playlistName + "', '" +
                id_user + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    //borra playlist del usuario y las canciones relacionadas en el playlist
    // debería solo borrarse el playlist si no hay canciones?? para decidir
    @Override
    public void deletePlaylist(Playlist playlist) {
        String query1 = "DELETE FROM listas_reproduccion WHERE id = " + playlist.getId();
        String query2 = "DELETE FROM lista_cancion WHERE id_lista = " + playlist.getId();

        SQLConnector.getInstance().deleteQuery(query1);
        SQLConnector.getInstance().deleteQuery(query2);

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
    public List<Song> loadUserAddedSongs(int id_user) {
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
                int orden = resultSet.getInt("orden");
                int minutes = resultSet.getInt("timeMinutes");
                int seconds = resultSet.getInt("timeSec");
                String lyrics = resultSet.getString("lyrics");


                //aquí el campo orden no es significativo pero se lo pasamos
                song.add(new Song(idSong, name, idGenere, genere, idAlbum, album, idSinger, singer, idOwner, owner, filePath, orden, minutes, seconds, lyrics));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return song;
    }

    // HOME / EXPLORER
    // lista de canciones de una playlist. usamos el campo orden y es importante al estar las canciones dentro de la playlist
    @Override
    public List<Song> loadMusicPlaylist(Playlist playlist) {
        List<Song> song = new LinkedList<>();
        String query = "select lista_cancion.id as idSongPlaylist, name, listas_reproduccion.id_usuario as idOwnerLista, orden, v_songs.* from lista_cancion\n" +
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
                int orden = resultSet.getInt("orden");
                int minutes = resultSet.getInt("timeMinutes");
                int seconds = resultSet.getInt("timeSec");
                String lyrics = resultSet.getString("lyrics");

                song.add(new Song(idSong, name, idGenere, genere, idAlbum, album, idSinger, singer, idOwner, owner, filePath, orden, minutes, seconds, lyrics));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
            return null;
        }
        return song;
    }

    // todavía no existe la pantalla donde se usa
    // se usará para buscar una canción para añadir a la playlist
    // devuelven todas las canciones que existen para poder añadir
    @Override
    public List<Song> loadAllMusic() {
        List<Song> song = new LinkedList<>();
        String query = "SELECT distinct name from v_song order by name";

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
                int minutes = resultSet.getInt("timeMinutes");
                int seconds = resultSet.getInt("timeSec");
                String lyrics = resultSet.getString("lyrics");
                int orden = 0; //ponemos 0 porque el orden nos da igual, las canciones se ordenan por nombre y no se requiere

                song.add(new Song(idSong, name, idGenere, genere, idAlbum, album, idSinger, singer, idOwner, owner, filePath,orden, minutes, seconds, lyrics));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return song;
    }

    // añade una canción a la playlist
    // se añade al final de la lista. Pondremos por defecto el orden el id_ del registro.
    // como el id es un autonumerico, sera el ultimo id_ de registro y no sirve para indicar el ultimo valor de orden
    @Override // todo hace falta la posicion si la queremos añadir al final??
    public void addSongPlaylist(Playlist playlist, Song song) {

            String query = "INSERT INTO lista_cancion(id_lista, id_cancion) VALUES ('" +
                    playlist.getId() + "," +
                    song.getIdSong() + "')";

            SQLConnector.getInstance().insertQuery(query);

            //despúes de añadir la cación busco su id y updato al campo orden
            // esto funciona si no se añade dos veces la misma canción
            // quien lo controla ? si la capa bussines no nos envía una canción existente funciona
            // sino habría que controlarlo aquí y cambiar "vodi" por "boolean" para indicar a la capa bussines que todo
            // ha ido bien

            //buscamos el id_ recien creado



        try {
            String query1 = "SELECT * from lista_cancion WHERE id_lista = "+
                    playlist.getId() + " and id_cancion = " +
                    song.getIdSong() + ")";

            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query1);
            int orden = resultSet.getInt("id");

            //updatamos el id_ en el campo orden
            String query2 = "UPDATE lista_cancion SET orden = " + orden + " WHERE id_lista = " + playlist.getId() + " and id_cancion = " + song.getIdSong();

            SQLConnector.getInstance().updateQuery(query2);
        } catch (SQLException e1) {
            e1.getErrorCode();
        }

    }


    // borra una canción de la playlist
    @Override
    public boolean deleteSongPlaylist(Playlist playlist, Song song) {
        String query = "DELETE FROM lista_cancion WHERE id_lista = " + playlist.getId() + " and id_cancion = " + song.getIdSong();

        SQLConnector.getInstance().deleteQuery(query);

        return true;

    }

    // cambia el orden de la canción por el orden de la canción posterior
    // seguimos dando por hecho que no se puede repetir el id_playlist + id_song , sino no funcionará
    // esta parte podría estar toda en bussines y aquí solo updatar el campo orden

    @Override //todo mirar lo de la posicion
    public boolean moveSongUp(Playlist playlist, Song song) {

        List<Song> songPlaylist = new LinkedList<>();
        songPlaylist = loadMusicPlaylist(playlist);


        int c = 0;
        while (songPlaylist.get(c).getIdSong() != song.getIdSong() && c < songPlaylist.size()) {
            c++;
        }

        if (c == 0) return false; // esta condición se da si se quiere mover la canción de la posición 0;

        int orderNow = songPlaylist.get(c).getOrden();
        int orderNew = songPlaylist.get(c-1).getOrden();

        String query = "UPDATE lista_cancion SET orden = " + orderNew + " WHERE id_lista = " + playlist.getId() + " and id_cancion = " + song.getIdSong();
        SQLConnector.getInstance().updateQuery(query);

        String query1 = "UPDATE lista_cancion SET orden = " + orderNow + " WHERE id_lista = " + playlist.getId() + " and id_cancion = " + songPlaylist.get(c-1).getIdSong();
        SQLConnector.getInstance().updateQuery(query1);

        return true;
    }


    // cambia el orden de la canción por el orden de la canción posterior
    // seguimos dando por hecho que no se puede repetir el id_playlist + id_song , sino no funcionará
    // esta parte podría estar toda en bussines y aquí solo updatar el campo orden

    @Override //todo mirar lo de la posicion
    public boolean moveSongDown(Playlist playlist, Song song) {

        List<Song> songPlaylist = new LinkedList<>();
        songPlaylist = loadMusicPlaylist(playlist);


        int c = 0;
        while (songPlaylist.get(c).getIdSong() != song.getIdSong() && c < songPlaylist.size()) {
            c++;
        }

        if (c == songPlaylist.size()) return false; // esta condición se da si se quiere mover la canción de la posición última;

        int orderNow = songPlaylist.get(c).getOrden();
        int orderNew = songPlaylist.get(c+1).getOrden();

        // updato la canción que quiero cambiar de posición
        String query = "UPDATE lista_cancion SET orden = " + orderNew + " WHERE id_lista = " + playlist.getId() + " and id_cancion = " + song.getIdSong();
        SQLConnector.getInstance().updateQuery(query);

        //updato la canción que intercambio la posición
        String query1 = "UPDATE lista_cancion SET orden = " + orderNow + " WHERE id_lista = " + playlist.getId() + " and id_cancion = " + songPlaylist.get(c+1).getIdSong();
        SQLConnector.getInstance().updateQuery(query1);

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

   /* private String singerName(long idAlbum) {

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
   /* private String singerNameInCantantes(long idSinger){
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
   /* private String albumName(long idAlbum) {
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
  /*  private String owneName(long idUser) {
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
    /*private String genre(long idGenre) {
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

    */

}
