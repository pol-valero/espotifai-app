package business;

import business.entities.Genre;
import business.entities.Song;
import persistence.DAO.MusicDatabaseDAO;
import persistence.DAO.MusicListDatabaseDAO;
import persistence.MusicDAO;
import persistence.MusicListDAO;

import java.util.Comparator;
import java.util.LinkedList;
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
        LinkedList<Genre> stadistic = (LinkedList<Genre>) musicDAO.loadStadistic();
        stadistic.sort(Comparator.reverseOrder());

        LinkedList<Genre> topGenres = new LinkedList<>();
        for (Genre genre: stadistic) {
            if (stadistic.indexOf(genre) < 10) {
                topGenres.add(genre);
            }
        }

        return topGenres;
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

        List<Genre> stadistic = musicDAO.loadStadistic();
        for(Genre genre: stadistic) {
            if (genre.getGenre().equalsIgnoreCase(song.getGenre())) {
                genre.incrementAmount();
            }
        }
        musicDAO.updateStadistic(stadistic);
    }

    public void deleteUserAddedSong(String songName){
        musicDAO.deleteSong(songName);

        List<Genre> stadistic = loadStadistic();
        for (Genre genre: stadistic) {
            if (genre.getGenre().equalsIgnoreCase(songName)){
                genre.decrementAmount();
            }
        }
        musicDAO.updateStadistic(stadistic);
    }

    public void playMusic(){
        currentSong.playMusic();
    }

    public void stopMusic(){
        currentSong.stopSong();
    }



}
