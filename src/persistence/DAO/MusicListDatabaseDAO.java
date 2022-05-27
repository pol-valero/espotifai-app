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
                    int id = resultSet.getInt("id_playlist");
                    String name = resultSet.getString("playlist_name");
                    int id_user = resultSet.getInt("id_usuario");
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
        String query = "select * from v_playlist where id_usuario = '" + user_id + "' order by playlist_name asc;";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

            if (resultSet != null) {
                resultSet.next();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_playlist");
                    String name = resultSet.getString("playlist_name");
                    int id_user = resultSet.getInt("id_usuario");
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


    @Override
    public void deletePlaylist(String playlistName) {
        String query1 = "DELETE FROM listas_reproduccion WHERE nombre = \"" + playlistName + "\";";

        SQLConnector.getInstance().deleteQuery(query1);
    }


    // HOME / PLAYLIST
    // es la lista de todas las canciones distintas que tiene en las playlist el usuario
    @Override
    public List<Song> loadUserAddedSongs(int id_user) {
        List<Song> song = new LinkedList<>();
        String query = "select distinct name, listas_reproduccion.id_usuario as idOwnerLista, v_songs.* from lista_cancion " +
                "            inner join v_songs on lista_cancion.id_cancion = v_songs.idSong " +
                "            inner join listas_reproduccion on lista_cancion.id_lista = listas_reproduccion.id " +
                " where listas_reproduccion.id_usuario = \""+ id_user +
                "\" order by name asc";

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
        String query = "select lista_cancion.id as idSongPlaylist, name, listas_reproduccion.id_usuario as idOwnerLista, orden, v_songs.* from lista_cancion" +
                "            inner join v_songs on lista_cancion.id_cancion = v_songs.idSong" +
                "            inner join listas_reproduccion on lista_cancion.id_lista = listas_reproduccion.id" +
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
            exception.printStackTrace();
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
        String query = "SELECT * FROM v_songs;";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

            while (resultSet.next()) {
                int idSong = resultSet.getInt("idSong");
                String name = resultSet.getString("name");
                int idGenere = resultSet.getInt("idGenere");
                String genere = resultSet.getString("genere");
                int idAlbum = resultSet.getInt("idAlbum");
                String album = resultSet.getString("album");
                int idSinger = resultSet.getInt("idSinger");
                String singer = resultSet.getString("singer");
                int idOwner = resultSet.getInt("idOwner");
                String owner = resultSet.getString("owner");
                int minutes = resultSet.getInt("timeMinutes");
                int seconds = resultSet.getInt("timeSec");
                String lyrics = resultSet.getString("lyrics");
                String filePath = resultSet.getString("filepath");
                int orden = 0; //ponemos 0 porque el orden nos da igual, las canciones se ordenan por nombre y no se requiere

                song.add(new Song(idSong, name, idGenere, genere, idAlbum, album, idSinger, singer, idOwner, owner, filePath,orden, minutes, seconds, lyrics));
            }
        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return song;
    }


    @Override
    public void addSongPlaylist(int idPlaylist, int idSong, int position) {

            String query = "INSERT INTO lista_cancion(id_lista, id_cancion, orden) VALUES ('" +
                    idPlaylist + "', '" +
                   idSong + "', '" +
                    position + "');";

            SQLConnector.getInstance().insertQuery(query);

    }



    @Override
    public boolean deleteSongPlaylist(Playlist playlist, Song song) {
        String query = "DELETE FROM lista_cancion WHERE id_lista = '" + playlist.getId() + "' AND id_cancion = '" + song.getIdSong() + "';";

        SQLConnector.getInstance().deleteQuery(query);

        return true;

    }


    public void changePlaylistName(String currentName, String newName){
        String query = "UPDATE listas_reproduccion SET nombre = \"" + newName + "\" WHERE  nombre = \"" + currentName + "\";";
        SQLConnector.getInstance().updateQuery(query);

    }

    @Override
    public int idSongInPlaylist(int idPlaylist) {
        String query = "select id_cancion from lista_cancion where id_lista = '" + idPlaylist + "';";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            int sizeId = 0;
            while (resultSet.next()){
                int id = resultSet.getInt("id_cancion");
                System.out.println("id de la lista es = " + id);
                sizeId++;
            }
            return sizeId;
        } catch (SQLException e) {
            //e.printStackTrace();
            return 1;
        }

    }

    @Override
    public List<Song>  loadMusicOnePlaylist(int idPlaylist){
        String query = "select id_cancion, orden from lista_cancion where id_lista = '" + idPlaylist + "' order by orden;";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        List<Song> allSong = loadAllMusic();
        List<Song> songList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_cancion");
                int position = resultSet.getInt("orden");
                for(Song song: allSong){
                    if (id == song.getIdSong()){
                        song.setOrden(position);
                        songList.add(song);
                    }
                }
            }
            return songList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void moveSongsInPlaylist(int idPlaylist, int idSong1, int idSong2, int idOrder1, int idOrder2){
        String query1 = "UPDATE lista_cancion SET orden = '"+ idOrder2 +"' Where id_lista = '" + idPlaylist
                        + "' AND id_cancion = '" + idSong1 + "';";

        String query2 = "UPDATE lista_cancion SET orden = '"+ idOrder1 +"' Where id_lista = '" + idPlaylist
                + "' AND id_cancion = '" + idSong2 + "';";

        SQLConnector.getInstance().updateQuery(query1);
        SQLConnector.getInstance().updateQuery(query2);

    }

    @Override
    public void deleteSongAllPlaylist(int idSong){
        String query = "Delete From lista_cancion Where id_cancion = '" + idSong+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }



}
