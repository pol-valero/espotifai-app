package business.entities;

/**
 * Class in charged of managing information related to Song objects
 */
public class Song {

    private int idSong;
    private String name;
    private int idGenre;
    private String genre;
    private int idAlbum;
    private String album;
    private int idSinger;
    private String singer;
    private int idOwne;
    private String owne;
    private String filePath;
    private int orden;
    private int minutes;
    private int seconds;
    private String lyrics;
    private String Lyrics;
    private int minutos;
    private int segundos;


    public Song(String name, String artist, String album, String genre, String filePath, String lyrics, int minutos, int segundos){
        this.name = name;
        this.singer = artist;
        this.album = album;
        this.genre = genre;
        this.filePath = filePath;
        this.lyrics = lyrics;
        this.minutes = minutos;
        this.seconds = segundos;

    }
    public Song(String name, String artist, String album, String genre,String owne, String filePath, String lyrics, int minutos, int segundos){
        this.name = name;
        this.singer = artist;
        this.album = album;
        this.genre = genre;
        this.owne = owne;
        this.filePath = filePath;
        this.lyrics = lyrics;
        this.minutes = minutos;
        this.seconds = segundos;

    }

    public Song(int idSong, String name, int idGenre, String genre, int idAlbum,
                String album, int idSinger, String singer, int idOwne, String owne, String filePath, int orden, int minutes, int seconds, String lyrics) {

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
    this.orden = orden;
    this.minutes = minutes;
    this.seconds = seconds;
    this.lyrics = lyrics;

    }

    /**
     * Method to get the name of a song
     * @return String corresponding to the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the id of the object song
     * @return Integer corresponding to the song's id
     */
    public int getIdSong() {
        return idSong;
    }

    /**
     * Method the get the name of the genre were the song is classified in
     * @return String corresponding to the name of the genre were the song belongs to
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Method to get the name of the album were the song belongs to.
     * @return String corresponding to the name of the album were the songs belongs to
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Method to get the name of the singer of the song
     * @return String corresponding to the name of the singer of the song
     */
    public String getSinger() {
        return singer;
    }

    /**
     * Method to get the filepath of the song
     * @return String corresponding to the filepath of the song
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Method to get the owner of the song (user who has added the song into the system)
     * @return String corresponding to the user who had added the song into de system
     */
    public String getOwne() {
        return owne;
    }

    /**
     * Method the get the id of the genre of the song
     * @return Integer corresponding to the genre's song id
     */
    public int getIdGenre() {
        return idGenre;
    }

    /**
     * Method to get the id of the album of the song
     * @return Integer corresponding to the album's id
     */
    public int getIdAlbum() {
        return idAlbum;
    }

    /**
     * Method to get the id of the singer of the song
     * @return Integer corresponding to the singer's id
     */
    public int getIdSinger() {
        return idSinger;
    }

    /**
     * Method the get the id of the owner.
     * @return Integer corresponding to the owner's id
     */
    public int getIdOwne() {
        return idOwne;
    }

    /**
     * Method the get the position of the song regards the playlist where it belongs
     * @return Integer corresponding to the position of the song regarding its playlist
     */
    public int getOrden() {
        return orden;
    }

    /**
     * Sets the owner's Id
     * @param idOwne Integer corresponding to the onwer's id
     */
    public void setIdOwne(int idOwne) {
        this.idOwne = idOwne;
    }

    /**
     * Sets the genre's id
     * @param idGenre Integer corresponding to the genre's id
     */
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    /**
     * Sets the singer's id
     * @param idSinger Integer corresponding to the singer's id
     */
    public void setIdSinger(int idSinger) {
        this.idSinger = idSinger;
    }

    /**
     * Sets the album's id
     * @param idAlbum Integer corresponding to the album's id
     */
    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    /**
     * Method to get the duration of the song (minutes)
     * @return Integer corresponding to the duration (minutes)
     */
    public int getMinutes() {
        return minutes;
    }
    /**
     * Method to get the duration of the song (seconds)
     * @return Integer corresponding to the duration (seconds)
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Method to get the lyrics of a song
     * @return Method to get the lyrics of the song
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * Method the set the owner of the song
     * @param owne String corresponding to the owner
     */
    public void setOwne(String owne) {
        this.owne = owne;
    }

    /**
     * Method to set the position of the song in its playlist
     * @param orden Integer corresponding to the position of the song to be set.
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
}
