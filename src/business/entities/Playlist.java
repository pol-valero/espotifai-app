package business.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase encargada de gestionar la informacion de los objetos playlist
 */
public class Playlist {
    private long id;
    private String name;
    private long id_user;
    private String owner;
    //private List<Song> songList;

    public Playlist(long id, String name, long id_user, String owner){
        this.id = id;
        this.name = name;
        this.id_user = id_user;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    //public List<Song> getSongList() {
    //    return songList;
    //}
}
