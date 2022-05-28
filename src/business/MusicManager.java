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
 * Class responsable for the management of information of songs and playlist
 */
public class MusicManager implements Runnable{
    /**
     * Object musicDAO, used for managing information related to songs and capable of communicate between
     * persistence layer with upper layers
     */
    private MusicDAO musicDAO = new MusicDatabaseDAO();

    /**
     * Object MusiclistDAO, used for managing information related to playlist and capable of
     * communicate persistence with upper layers.
     */
    private MusicListDAO musicListDAO = new MusicListDatabaseDAO();

    /**
     * Object Song which corresponds to the current song playing
     */
    private Song currentSong;
    /**
     * Object Music player which will be facilitating functionalities of reproduction of files
     */
    private MusicPlayer musicPlayer;
    /**
     * Boolean true when the user is currently using a playlist
     */
    private boolean playlist;
    /**
     * List of songs which the user will be listening to if clicking previous or next song
     */
    private List<Song> songs;
    /**
     * Boolean true means that the user asked for the song to pause
     */
    private boolean paused;

    /**
     * Integer position meaning what current position of the song is being played
     */
    private int position;

    /**
     * Object song for the creation of new songs
     */
    private Song createSong;

    /**
     * Boolean true meaning the song, went ending; must continue playing
     */
    private boolean loop;
    /**
     * Thread of the reproduction of the song
     */
    private Thread thread;
    /**
     * boolean indicating the state of the thread
     */
    private boolean threadStatus;
    /**
     * String of song names used for various purposes.
     */
    private String selectedSongName;

    private boolean playlistLop;

    /**
     * Constructor
     */
    public MusicManager () {
        //System.out.println(getSongLenght());
        this.loop = false;
        this.playlist = false;
        this.paused = true;
        songs = null;
        currentSong = null;
        threadStatus = true;
        playlistLop = false;

    }

    /**
     * Getter for current song
     * @return Object song
     */
    public Song getCurrentSong() {
        return currentSong;
    }

    /**
     * Method to change the current song to a given one
     * @param song Object song which will be the new current one
     */
    public void changeCurrentSong(Song song){
        this.currentSong = song;
    }

    /**
     * Method to load stadistics from persistence
     * @return List of objects genres used for generating stadistics
     */
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

    /**
     * Method to load the information related to a song
     * @param songName String name of the song which the method will look for
     * @return Song with matching names. In case it was not found, returns null
     */
    public Song loadSongInformation(String songName){
        List<Song> songs = musicListDAO.loadAllMusic();

        for (Song song: songs) {
            if(song.getName().equals(songName)) {
                return song;
            }
        }
        return null;
    }

    /**
     * Method to create a song and save it into persistence
     * @param song Object song
     */
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

    /**
     * Method to delete a song a user had added
     * @param song Song to delete from persistence
     */
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

    /**
     * Method to insert an ID to a singer
     * @param song Song object
     */
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

    /**
     * Method to insert an ID to an album
     */
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

    /**
     * Method to find in persistence the existence of a song
     * @param songName String name of the song used for finding it in persistence
     * @return boolean true in case there's a match, false for opposite case
     */
    public boolean findSongName(String songName) {
        if (loadSongInformation(songName) != null) {
            return true;
        }
        return false;
    }

    /**
     * Method to check if a file path exists or not
     * @param path String corresponding to the file path
     * @return boolean true in case the file path exsists. False for the opposite case
     */
    boolean findPath(String path) {
        File file = new File(path);

        if (file.exists()) {
            return true;
        }
        return false;
    }

    //todo para la reproduccion de musica

    /**
     * Method used when the user wants to pause the song
     */
    public void pausedSong(){ //para cuando se le da al boton de pausar  reproducir
        System.out.println("pausa vale = " + paused);
        paused = !paused;
        System.out.println("pausa vale = " + paused);
        if (paused) {
            musicPlayer.resume();
        } else {
            musicPlayer.pause();
        }
    }

    /**
     * Method to know the duration of a song
     * @param filePath String corresponding to the file path of the song file
     * @return array of interger where [0] corresponds to the minutes and [1] to the seconds of duration
     */
    public int[] songTime(String filePath){
        String filename = filePath;
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


    /**
     * Method to start playing the previous or next song in the music list
     * @param next Integer corresponding to the difference from final to original position of the song to be played. Should be 1 or -1,
     *  respectively to next and previous position
     */
    public void previusNextSong(int next){

        if(!musicPlayer.getfinisehedSong()){
            musicPlayer.stop();
        }

        int position  = this.position + next;

        if ( (position >= songs.size() && playlistLop) || position < 0) {
            position = 0;
            changeCurrentSong(position);
            this.position = position;
        } else {
            if (position >= songs.size() && playlist){
                position = this.position;
            }
            changeCurrentSong(position);
            this.position = position;

        }
        playNewSong();
    }

    /**
     * Method to start playing a song.
     * @param playlist boolean that for true means that the user is currently using a playlist
     * @param songs List of song objects which the user will be able to play
     * @param position Integer corresponding to the position of the song in the music list which the user want to play
     */
    public void playSong(boolean playlist, List<Song> songs, int position){
        this.playlist = playlist;
        this.songs = songs;
        this.position = position;

        currentSong = loadSongInformation(selectedSongName);
        System.out.println(currentSong.getName());
        playNewSong();
    }

    /**
     * Method to change the current song
     * @param position Integer position of the new current song
     */
    private void changeCurrentSong(int position){
        currentSong = songs.get(position);
    }

    /**
     * Method to play the song
     * @return boolean true in case there were no problems, false for opposite case
     */
    private boolean playNewSong() { //podria ser void si no queremos mirar si el path falla
        try {
            if (musicPlayer != null){
                if(!musicPlayer.getfinisehedSong()){
                    musicPlayer.stop();
                }
            }
            FileInputStream inputStream = new FileInputStream(currentSong.getFilePath());
            musicPlayer = new MusicPlayer(inputStream);
            musicPlayer.play();
            if (thread == null){
                startingThread();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (JavaLayerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to start playing next song when the current one has finished
     */
    public void automaticSongChange(){ //depende donde este el Thread sera publico o priv
        if(!musicPlayer.getfinisehedSong()){
            musicPlayer.stop();
        }
        paused = true;
        if (!playlist || loop){
            previusNextSong(0);
        } else {
            previusNextSong(1);
        }
    }

    @Override
    public void run() {
        while(threadStatus){
            if(musicPlayer.getfinisehedSong()){
                automaticSongChange();
            }
            try {
                thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to set the current song in a loop
     */
    public void loop(){
        System.out.println("activo loop");
        this.loop = !loop;
    }

    public void playlistLoop(){
        this.playlistLop = !playlistLop;
    }

    /**
     * Getter for selected song names
     * @return
     */
    public String getSelectedSongName() {
        return selectedSongName;
    }

    /**
     * Setter for selected song names
     * @param selectedSongName String of song names that have been selected
     */
    public void setSelectedSongName(String selectedSongName) {
        this.selectedSongName = selectedSongName;
    }

    /**
     * Method to start the thread
     */
    public void startingThread(){
        thread = new Thread(this);
        thread.start();

    }

    /**
     * Method to stop the thread
     */
    public void stopThread(){
        musicPlayer.close();
        this.threadStatus = false;

    }

    public void setCurrentSong (Song currentSong) {
        this.currentSong = currentSong;
    }

}
