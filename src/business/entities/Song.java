package business.entities;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 * Clase encargada de gestionar la informacion de los objetos Song
 */
public class Song extends PlaybackListener implements Runnable{

    private int idSong;
    private String name;
    private int idGenre;
    private String genre;
    private int idAlbum;
    private String album;
    private int idSinger;
    private String singer;
    private int idOwne; //user
    private String owne;
    private String filePath;

    private AdvancedPlayer music; //audio
    private Thread musicThread; //para reproducir
    private boolean stop;


    public Song(int idSong, String name, int idGenre, String genre, int idAlbum,
                String album, int idSinger, String singer, int idOwne, String owne, String filePath) {

    this.idSong = idSong;
    this.name = name;
    this.idGenre = idGenre;
    this.genre = genre;
    this.idAlbum = idAlbum;
    this.album = album;
    this.idSinger = idSinger;
    this.singer = singer;
    this.idOwne = idOwne;
    this.owne = owne;
    this.filePath = filePath;

    }

    public String getName() {
        return name;
    }

    public int getIdSong() {
        return idSong;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getSinger() {
        return singer;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getOwne() {
        return owne;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public int getIdSinger() {
        return idSinger;
    }

    public int getIdOwne() {
        return idOwne;
    }

    public void setIdOwne(int idOwne) {
        this.idOwne = idOwne;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public void setIdSinger(int idSinger) {
        this.idSinger = idSinger;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }
    //parte de la reproduccion

    //empezar a reproducir
    public void playMusic() {
        try
        {
            String urlAsString =
                    "file:///"
                            + new java.io.File(".").getCanonicalPath()
                            + "/"
                            + this.filePath;

            this.music = new AdvancedPlayer (
                    new java.net.URL(urlAsString).openStream(),
                    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
            );

            this.music.setPlayBackListener(this);

            this.musicThread = new Thread(this, "AudioThread");

            this.stop = false;
            this.musicThread.start();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void stopSong(){
        music.close();
    }

    @Override
    public void run() {
        try {
            this.music.play();
        }
        catch (javazoom.jl.decoder.JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
