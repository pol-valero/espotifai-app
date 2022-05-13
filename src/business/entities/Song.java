package business.entities;

/**
 * Clase encargada de gestionar la informacion de los objetos Song
 */
public class Song {

    private String name;
    private int idSong;
    private String genre;
    private int idGenre;
    private String album;
    private String singer;
    private String filePath;
    private String owne;
    private int idOwne; //user
    private String path;

    public Song(String name, int idSong, String genre,
                String album, String singer, String owne, int idUser) {

    this.name = name;
    this.idSong = idSong;
    this.genre = genre;
    this.album = album;
    this.singer = singer;
    this.owne = owne;
    this.idOwne = idUser;
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
}
