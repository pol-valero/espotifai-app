package persistence;

import business.entities.Genre;
import business.entities.Song;

import java.util.List;

public interface MusicDAO {

    public List<Genre> loadStadistic();

    public void createSong(Song song);

    public void deleteSong(String songName);

    public void createStadistic(List<Genre> stadistic);

}
