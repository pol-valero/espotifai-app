package business.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Class in charged of managing information related to Playlist objects
 */
public class Playlist {
    private int id;
    private String name;
    private int id_user;
    private String owner;
    //private List<Song> songList;

    public Playlist(int id, String name, int id_user, String owner){
        this.id = id;
        this.name = name;
        this.id_user = id_user;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public int getUserId() {
        return id_user;
    }

    //public List<Song> getSongList() {
    //    return songList;
    //}
}
