package business;

import business.entities.Genre;
import business.entities.MusicPlayer;
import business.entities.Song;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
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

        insertIdSingerAlbum(song);
        song.setIdSinger(createSong.getIdSinger());
        song.setIdAlbum(createSong.getIdAlbum());

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
            createSong.setIdAlbum(musicDAO.loadIdAlbum(createSong.getAlbum()));
            System.out.println("managerMusic id albu al crearse = " + musicDAO.loadIdSinger(createSong.getSinger()) +
                    "current song = " + createSong.getIdSinger());
        }
    }

    public boolean findSongName(String songName) {
        if (loadSongInformation(songName) != null) {
            return true;
        }
        return false;
    }

    boolean findPath(String path) {
        File file = new File(path);

        if (file.exists()) {
            return true;
        }
        return false;
    }

    //todo para la reproduccion de musica

    public void pausedSong(){ //para cuando se le da al boton de pausar  reproducir
        paused = !paused;
        if (paused) {
            musicPlayer.resume();
        } else {
            musicPlayer.pause();
        }
    }


    public int[] songTime(String filePath){
        String filename = "SongsunVeranoSinTi.mp3";
        Header h = null;
        FileInputStream file = null;
        int[] time = new int[2];
        time[0] = 0;
        time[1] = 0;
        try {
            file = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {

            return time;
        }
        Bitstream bitstream = new Bitstream(file);
        try {
            h = bitstream.readFrame();
        } catch (BitstreamException ex) {
            return time;
        }
        long fileChannelSize = 0;
        try {
            fileChannelSize = file.getChannel().size();
        }  catch (IOException e) {
            return time;
        }

        int total = (int) (h.total_ms((int) fileChannelSize)/1000);
        int minutes = total/60;
        int seconds = total - minutes*60;
        time[0] = minutes;
        time[1] = seconds;
        return time;
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
