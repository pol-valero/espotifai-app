package business;

import business.entities.Genre;
import business.entities.MusicPlayer;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import persistence.DAO.MusicDatabaseDAO;
import persistence.DAO.MusicListDatabaseDAO;
import persistence.MusicDAO;
import persistence.MusicListDAO;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.beans.Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.temporal.TemporalAdjuster;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de gestionar la informacion de Song y Playlist
 */
public class MusicManager {

    private MusicDAO musicDAO = new MusicDatabaseDAO();
    private MusicListDAO musicListDAO = new MusicListDatabaseDAO();

    private Song currentSong;
    private MusicPlayer musicPlayer;
    private boolean playlist = false;
    private List<Song> songs;
    private boolean paused = false;
    private int position;
    private Song createSong;

    public MusicManager () {
        //System.out.println(getSongLenght());
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void changeCurrentSong(Song song){
        this.currentSong = song;
    }

    public List<Genre> loadStadistc() {
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
    //ahora


    public Song loadSongInformation(String songName){
        List<Song> songs = musicListDAO.loadAllMusic();

        for (Song song: songs) {
            if(song.getName().equals(songName)) {
                return song;
            }
        }
        return null;
    }

    public void createSong(Song song) {
        this.createSong = song;
        boolean existGenre = false;
        List<Genre> stadistic = musicDAO.loadStadistic();
        System.out.println("la lista de generos tiene = " + stadistic.size());
        insertIdSingerAlbum(song);
        song.setIdSinger(createSong.getIdSinger());
        song.setIdAlbum(createSong.getIdAlbum());
        System.out.println("id album song = "+ song.getIdAlbum() +"album = "+ createSong.getIdAlbum());
        System.out.println("id artista song = "+ song.getIdSinger());

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
                    musicDAO.createStadistic(song.getGenre());
                    stadistic = musicDAO.loadStadistic();
                    song.setIdGenre(stadistic.get(stadistic.size() - 1).getId());
                    musicDAO.createSong(song);
            }
        } else {
            musicDAO.createStadistic(song.getGenre());
            stadistic = musicDAO.loadStadistic();
            song.setIdGenre(stadistic.get(stadistic.size() - 1).getId());
            musicDAO.createSong(song);
        }
        createSong = null;
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
            createSong.setIdSinger(idSinger);
        } else {
            musicDAO.createSinger(song.getSinger());
            idSinger = musicDAO.loadIdSinger(song.getSinger());
            createSong.setIdSinger(idSinger);
        }
        insertIdAlbum();
    }

    private void insertIdAlbum(){
        System.out.println("el nombre del album es = " + createSong.getAlbum());
        int idAlbum = musicDAO.loadIdAlbum(createSong.getAlbum());
        System.out.println("managerMusic id albu = " + idAlbum);
        if (idAlbum != 0) {
            createSong.setIdAlbum(idAlbum);

        } else  {
            musicDAO.createAlbum(createSong.getAlbum(), createSong.getIdSinger());
            createSong.setIdAlbum(musicDAO.loadIdSinger(createSong.getSinger()));
            System.out.println("managerMusic id albu al crearse = " + musicDAO.loadIdSinger(createSong.getSinger()) +
                    "current song = " + createSong.getIdSinger());
        }
    }

    //todo para la reproduccion de musica

    public void pausedSong(){ //para cuando se le da al boton de pausar  reproducir
        paused = !paused;
        if (paused) {
            musicPlayer.resume();
        } else {
            musicPlayer.stop();
        }
    }

    //podria ser un boolean y retorna false si falla en el path
    public void previusNextSong(int next){ //para la parte de la barra de reproduccion las fleechas
        musicPlayer.stop();
        int position = this.position + next; //next debe ser uno o menos uno

        if ( position >= songs.size() || position < 0) {
            position = 0;
            changeCurrentSong(position);
            this.position = position;
        } else {
            changeCurrentSong(position);
        }
        playNewSong();
    }

    public void playSong(boolean playlist, List<Song> songs, int position){
        this.playlist = playlist;
        this.songs = songs;
        this.position = position;
        changeCurrentSong(position);
        playNewSong();
    }

    private void changeCurrentSong(int position){
        currentSong = songs.get(position);
    }

    private boolean playNewSong() { //podria ser void si no queremos mirar si el path falla
        try {
            musicPlayer.stop();
            FileInputStream inputStream = new FileInputStream(currentSong.getFilePath());
            paused = false;
            musicPlayer = new MusicPlayer(inputStream);
            musicPlayer.play();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (JavaLayerException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void automaticSongChange(){ //depende donde este el Thread sera publico o priv
        if (!playlist){
            previusNextSong(0);
        } else {
            previusNextSong(1);
        }
    }


    /*
    public int getSongLenght () {
        AudioFileFormat fileFormat = null;
        try {
            fileFormat = AudioSystem.getAudioFileFormat(new File("songs/Clash Royal.mp3"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long microseconds = (long) fileFormat.properties().get("duration");
        int seconds = (int) (microseconds / 10^6);
        return seconds;

    }*/


}
