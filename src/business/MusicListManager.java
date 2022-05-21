package business;

import business.entities.Playlist;
import business.entities.Song;
import persistence.DAO.MusicListDatabaseDAO;
import persistence.MusicListDAO;

import java.util.LinkedList;
import java.util.List;

public class MusicListManager {
    private MusicListDAO  musicListDAO = new MusicListDatabaseDAO();

    public List<Playlist> loadPublicPlaylist(int idUser){
        return  musicListDAO.loadPublicPlaylist(idUser);
    }

    public List<Playlist> loadUserPlaylist(int idUser){
        return musicListDAO.loadUserPlaylist(idUser);
    }

    public List<Song> loadAllMusic(){
        return musicListDAO.loadAllMusic();
    }

    public void addSongPlaylist(String playlistName, Song song, int position, int idUser){ //todo falta mirar lo de la posicion
        Playlist playlist = findUserPlaylist(playlistName, idUser);

        if (playlist != null){
            musicListDAO.addSongPlaylist(playlist, song, position);
        }
    }

    public void deleteSongPlaylist(String playlistName, List<String> songName, int idUser){
        Playlist playlist = findUserPlaylist(playlistName, idUser);
        List<Song> songs = findSong(songName);

        if (playlist != null && songs != null){
            for (Song song: songs){
                musicListDAO.deleteSongPlaylist(playlist, song);
            }
        }
    }

    public Playlist findPlaylist(String playlistName){ //todo hacer una funcion para cargar todas las playlist del dao


        return null;
    }


    private Playlist findUserPlaylist(String playlistName, int idUser){
        List<Playlist> playlists =  musicListDAO.loadUserPlaylist(idUser);

        if (playlists.size() != 0) {
            for (Playlist playlist: playlists) {
                if (playlistName.equals(playlist.getName())) {
                    return playlist;
                }
            }
        }
        return null;
    }

    private List<Song> findSong(List<String> songName){
        List<Song> songs = musicListDAO.loadAllMusic();
        List<Song> newSong = new LinkedList<>();

        if (songs.size() != 0 && songName.size() != 0) {
            for (String name: songName) {
                for (Song song : songs) {
                    if (name.equals(song.getName())) {
                        newSong.add(song);
                    }
                }
            }
        } else {
            return null;
        }

        return newSong;
    }


}
