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

    public Song findSong(String songName){
        List<Song> songs = musicListDAO.loadAllMusic();

        for (Song song: songs) {
            if(song.getName().equals(songName)) {
                return song;
            }
        }
        return null;
    }

    public void createSong(Song song) {
        boolean existGenre = false;
        List<Genre> stadistic = musicDAO.loadStadistic();

        insertIdSingerAlbum(song);

        if (stadistic.size() != 0) {
            for(Genre genre: stadistic) {
                if (genre.getGenre().equals(song.getGenre())) {
                    genre.incrementAmount();
                    song.setIdGenre(genre.getId());
                    musicDAO.updateStadistic(song.getGenre(), genre.getAmount());
                    existGenre = true;
                }
            }
            if (existGenre) {
                musicDAO.createSong(song);

            } else {
                if (stadistic.size() < 10){
                    musicDAO.createStadistic(song.getGenre());
                    stadistic = musicDAO.loadStadistic();
                    song.setIdGenre(stadistic.get(stadistic.size() - 1).getId());
                    musicDAO.createSong(song);
                }
            }
        } else {
            musicDAO.createStadistic(song.getGenre());
            stadistic = musicDAO.loadStadistic();
            song.setIdGenre(stadistic.get(stadistic.size() - 1).getId());
            musicDAO.createSong(song);
        }
    }

    public void deleteUserAddedSong(Song song){
        List<Genre> stadistic = musicDAO.loadStadistic();

        if (stadistic.size() != 0) {
            for(Genre genre: stadistic) {
                if (genre.getGenre().equals(song.getGenre())) {
                    genre.decrementAmount();

                    if (genre.getAmount() < 1) {
                        musicDAO.deleteGenre(genre.getGenre());

                    } else {
                        musicDAO.updateStadistic(song.getGenre(), genre.getAmount());

                    }
                    musicDAO.deleteSong(song);
                }
            }
        }
    }

    private void insertIdSingerAlbum(Song song){
        int idSinger = musicDAO.loadIdSinger(song.getSinger());
        if (idSinger != 0){
             song.setIdSinger(idSinger);
        } else {
            musicDAO.createSinger(song.getSinger());
            idSinger = musicDAO.loadIdSinger(song.getSinger());
            song.setIdSinger(idSinger);
        }
        insertIdAlbum(song);
    }

    private void insertIdAlbum(Song song){
        int idAlbum = musicDAO.loadIdAlbum(song.getAlbum());
        if (idAlbum != 0) {
            song.setIdAlbum(idAlbum);

        } else  {
            musicDAO.createAlbum(song.getAlbum(), song.getIdSinger());
            song.setIdSinger(musicDAO.loadIdSinger(song.getSinger()));
        }
    }

    //todo para la reproduccion de musica
    public void playMusic(){
        currentSong.playMusic();
    }

    public void stopMusic(){
        currentSong.stopSong();
    }



}
