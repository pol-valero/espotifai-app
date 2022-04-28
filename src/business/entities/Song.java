package business.entities;

/**
 * Clase encargada de gestionar la informacion de los objetos Song
 */
public class Song {

    private String name;
    private long idSong;
    private String genre;
    private String album;
    private String singer;
    private String filePath;
    private String owne;
    private long idOwne;

    public Song(String name, long idSong, String genre,
                String album, String singer, String owne, long idUser) {

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

    public long getIdSong() {
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
