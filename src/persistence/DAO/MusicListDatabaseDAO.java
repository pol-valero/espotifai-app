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

    /***
     * Method to load other users playlists from a query that is going to select us from a view
     * @param user_id we receive the user id
     */
    @Override
    public List<Playlist> loadPublicPlaylist(int user_id) {
        List<Playlist> playlist = new LinkedList<>();
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

    }

    /***
     * Method for loading user-created Playlists from a query that is going to select us from a view
     * @param user_id we receive the user id
     */
    @Override
    public List<Playlist> loadUserPlaylist (int user_id) {
        List<Playlist> playlist = new LinkedList<>();
        String query = "select * from v_playlist where id_usuario = '" + user_id + "' order by playlist_name asc;";

        try {
            ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id_playlist");
                    String name = resultSet.getString("playlist_name");
                    int id_user = resultSet.getInt("id_usuario");
                    String owner = resultSet.getString("owner");

                    playlist.add(new Playlist(id, name, id_user, owner));
                }

        } catch (SQLException exception){
            exception.getErrorCode();
        }
        return playlist;
    }

    /***
     * Method to create a user's playlist
     * @param playlistName we receive the String with the playlist name
     * @param id_user we receive the user id
     */
    @Override
    public void createPlaylist(String playlistName, int id_user) {
        String query = "INSERT INTO listas_reproduccion(nombre, id_usuario) VALUES ('" +
                playlistName + "', '" +
                id_user + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    /***
     * Method to delete a user's playlist
     * @param playlistName we receive the String with the playlist name
     */
    @Override
    public void deletePlaylist(String playlistName) {
        String query1 = "DELETE FROM listas_reproduccion WHERE nombre = \"" + playlistName + "\";";

        SQLConnector.getInstance().deleteQuery(query1);
    }


    /***
     * Method that shows the list of all the different songs that the user
     * has in the playlist in the Home view.
     * @param id_user we receive the user id
     */
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

    /***
     * Method to list the songs in a playlist and this will be displayed in the Home view in the EXPLORE section.
     * @param playlist we receive the playlist
     */
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
                int idGenere = resultSet.getInt("idGenere");
                String genere = resultSet.getString("genere");
                int idAlbum = resultSet.getInt("idAlbum");
                String album = resultSet.getString("album");
                int idSinger = resultSet.getInt("idSinger");
                String singer = resultSet.getString("singer");
                int idOwner = resultSet.getInt("idOwner");
                String owner = resultSet.getString("owner");
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

    /***
     * method that will allow to load all the songs by selecting them from the songs view.
     */
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

    /***
     * Method to add songs to a playlist
     * @param idPlaylist we receive the id of the playlist we want to work with
     * @param idSong we receive the id of the song
     * @param position song position
     */
    @Override
    public void addSongPlaylist(int idPlaylist, int idSong, int position) {
            String query = "INSERT INTO lista_cancion(id_lista, id_cancion, orden) VALUES ('" +
                    idPlaylist + "', '" +
                   idSong + "', '" +
                    position + "');";

            SQLConnector.getInstance().insertQuery(query);
    }

    /***
     * Method to delete songs from a playlist
     * @param playlist we receive the id of the playlist we want to work with
     * @param song we received the song
     */
    @Override
    public boolean deleteSongPlaylist(Playlist playlist, Song song) {
        String query = "DELETE FROM lista_cancion WHERE id_lista = '" + playlist.getId() + "' AND id_cancion = '" + song.getIdSong() + "';";

        SQLConnector.getInstance().deleteQuery(query);

        return true;

    }

    /***
     * Method to change the name of the active user
     * @param currentName: current user name
     * @param newName: new name the user will receive
     */
    public void changePlaylistName(String currentName, String newName){
        String query = "UPDATE listas_reproduccion SET nombre = \"" + newName + "\" WHERE  nombre = \"" + currentName + "\";";
        SQLConnector.getInstance().updateQuery(query);

    }

    /***
     * Method to get the id of a song belonging to a Playlist
     * @param idPlaylist we receive the id of the playlist we want to work with
     */
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
            return 1;
        }

    }

    /***
     * Method to load music from a specific playlist
     * @param idPlaylist we receive the id of the playlist we want to work with
     */
    @Override
    public List<Song>  loadMusicOnePlaylist(int idPlaylist) {
        String query = "select id_cancion, orden from lista_cancion where id_lista = '" + idPlaylist + "' order by orden;";

        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        List<Song> allSong = loadAllMusic();
        List<Song> songList = new LinkedList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_cancion");
                int position = resultSet.getInt("orden");
                for(Song song: allSong) {
                    if (id == song.getIdSong()) {
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

    /***
     * Method to move the songs to a different position
     * @param idPlaylist we receive the id of the playlist we want to work with
     * @param idSong1 id of the first song to move
     * @param idSong2 id of the second song to move
     * @param idOrder1 order of the first song
     * @param idOrder2 order of the second song
     */
    @Override
    public void moveSongsInPlaylist(int idPlaylist, int idSong1, int idSong2, int idOrder1, int idOrder2){
        String query1 = "UPDATE lista_cancion SET orden = '"+ idOrder2 +"' Where id_lista = '" + idPlaylist
                        + "' AND id_cancion = '" + idSong1 + "';";

        String query2 = "UPDATE lista_cancion SET orden = '"+ idOrder1 +"' Where id_lista = '" + idPlaylist
                + "' AND id_cancion = '" + idSong2 + "';";

        SQLConnector.getInstance().updateQuery(query1);
        SQLConnector.getInstance().updateQuery(query2);

    }

    /***
     * Method to delete from all playlists
     * @param idSong we receive the id of the song
     */
    @Override
    public void deleteSongAllPlaylist(int idSong){
        String query = "Delete From lista_cancion Where id_cancion = '" + idSong+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }

}
