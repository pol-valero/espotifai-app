package business;

import business.entities.Genre;
import business.entities.Song;
import persistence.DAO.MusicDatabaseDAO;
import persistence.DAO.MusicListDatabaseDAO;
import persistence.MusicDAO;
import persistence.MusicListDAO;
import java.util.List;

/**
 * Clase encargada de gestionar la informacion de Song y Playlist
 */
public class MusicManager {

    private MusicDAO musicDAO = new MusicDatabaseDAO();
    private MusicListDAO musicListDAO = new MusicListDatabaseDAO();

    private Song currentSong;

    public Song getCurrentSong() {
        return currentSong;
    }

    public void changeCurrentSong(Song song){
        this.currentSong = song;
    }

    public List<Genre> loadStadistic(){
        return musicDAO.loadStadistic();
    }

    public Song loadSongInformation(String songName){
        List<Song> songs = musicListDAO.loadAllMusic();
        for (Song song: songs){
            if(song.getName().equals(songName)){
                return song;
            }
        }
        return null;
    }

    public void createSong(Song song){
        musicDAO.createSong(song);
    }

    public void deleteUserAddedSong(String songName){
        musicDAO.deleteSong(songName);
    }

    public void playMusic(){
        currentSong.playMusic();
    }

    public void stopMusic(){
        currentSong.stopSong();
    }

}
