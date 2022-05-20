package business;

import business.entities.Playlist;
import business.entities.Song;
import persistence.DAO.MusicListDatabaseDAO;
import persistence.MusicListDAO;

import java.util.LinkedList;
import java.util.List;

public class MusicListManager {
    private MusicListDAO  musicListDAO= new MusicListDatabaseDAO();
    private String currentPlaylist;

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
        List<Playlist> playlists =  musicListDAO.loadUserPlaylist(idUser);
        for (Playlist playlist: playlists){
            if(playlistName.equals(playlist.getName()))
                musicListDAO.addSongPlaylist(playlist, song, position);
        }

    }


}
