package business.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Class in charged of managing information related to Playlist objects
 */
public class Playlist {
    /**
     * Integer id of the playlist
     */
    private int id;
    /**
     * String name of the playlist
     */
    private String name;
    /**
     * Integer id of the user and owner of the song
     */
    private int id_user;
    /**
     * String name of the owner
     */
    private String owner;

    public Playlist(int id, String name, int id_user, String owner){
        this.id = id;
        this.name = name;
        this.id_user = id_user;
        this.owner = owner;
    }

    /**
     * Method to get the Playlist ID
     * @return Interger Id of the playlist
     */
    public int getId() {
        return id;
    }

    /**
     * Method to get playlist name
     * @return String name of playlist
     */
    public String getName() {
        return name;
    }



    /**
     * Method to get the id of the owner
     * @return Integer corresponding to the id of the user
     */
    public int getUserId() {
        return id_user;
    }

}
