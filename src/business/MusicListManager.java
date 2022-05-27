package business;

import business.entities.Playlist;
import business.entities.Song;
import persistence.DAO.MusicListDatabaseDAO;
import persistence.MusicListDAO;

import java.util.LinkedList;
import java.util.List;


public class MusicListManager {
    private MusicListDAO  musicListDAO = new MusicListDatabaseDAO();

    private Playlist currentPlaylist;
    private String currentPlaylistName;

    public LinkedList<String> loadPublicPlaylist(int idUser){
        LinkedList<String> publicPlaylistNames = new LinkedList<>();
        for (Playlist playlist: musicListDAO.loadPublicPlaylist(idUser)) {
            publicPlaylistNames.add(playlist.getName());
        }
        return  publicPlaylistNames;
    }

    public LinkedList<String> loadUserPlaylist(int idUser){
        LinkedList<String> userPlaylistNames = new LinkedList<>();
        for (Playlist playlist: musicListDAO.loadUserPlaylist(idUser)) {
            userPlaylistNames.add(playlist.getName());
        }
        return userPlaylistNames;
    }

    public List<Song> loadAllMusic(){
        return musicListDAO.loadAllMusic();
    }

    public void addSongPlaylist(String playlistName, List<String> songNameList, int idUser){
        Playlist playlist = findUserPlaylist(playlistName, idUser);
        for (int i = 0; i < songNameList.size(); i++) {
            String songName = songNameList.get(i);
            int positon = musicListDAO.idSongInPlaylist(playlist.getId());
            positon++;
            System.out.println("cancion añadida = " + songName);
            System.out.println("en el manager la posicion es = " + positon);
            List<Song> songs = loadAllMusic();
            for(Song newSong: songs){
                if (songName.equals(newSong.getName())){
                    musicListDAO.addSongPlaylist(playlist.getId(), newSong.getIdSong(), positon);
                }
            }
        }

    }

    public void deleteSongPlaylist(String playlistName, List<String> songName, int idUser){
        Playlist playlist = findUserPlaylist(playlistName, idUser);
        List<Song> songs = findSong(songName);

        if (songs.size() > 0){
            for (Song song: songs){
                musicListDAO.deleteSongPlaylist(playlist, song);
            }
        }
    }

    public Playlist findPlaylist(String playlistName, int idUser){
        Playlist playlist = findUserPlaylist(playlistName, idUser);

        if (playlist != null) {
            return playlist;
        } else {
            playlist = findOnePublicPlaylist(playlistName, idUser);
            if(playlist != null){
                return playlist;
            }
        }
        return null;
    }

    public void deletePlaylist(String playlistName){
            musicListDAO.deletePlaylist(playlistName);
    }

    public void createPlaylist(String playlistName, int idUser){
            musicListDAO.createPlaylist(playlistName, idUser);
    }

    public List<Song> loadMusicPlaylist(String playlistName, int ideUser){
        Playlist playlist = findPlaylist(playlistName, ideUser);
        if(playlist != null){
            return musicListDAO.loadMusicPlaylist(playlist);
        } else {
            switch (playlistName) {
                case "MySongs":
                    return loadAllUserSongs(ideUser);
                case "AllSongs":
                    return loadAllMusic();
            }
        }
        return null;
    }

    public List<Song> loadSearchMusic(String filterName){
        List<Song> songs = musicListDAO.loadAllMusic();
        List<Song> searchSong = new LinkedList<>();

        if (songs.size() != 0 ){
            for(Song song: songs){
                if(filterName.equals(song.getName()) || filterName.equals(song.getGenre())
                        || filterName.equals(song.getAlbum()) || filterName.equals(song.getSinger())) {
                    searchSong.add(song);
                }
            }
            return searchSong;
        }
        return null;
    }


    private Playlist findUserPlaylist(String playlistName, int idUser){
        System.out.println(("id user = " + idUser));
        List<Playlist> playlists = new LinkedList<>();
         playlists =  musicListDAO.loadUserPlaylist(idUser);
         for (Playlist p: playlists){
             System.out.println( " nombe = " + p.getName());
         }

            for (Playlist playlist: playlists) {
                if (playlistName.equals(playlist.getName())) {
                    return playlist;
                }
            }

        return null;
    }

    private List<Song> findSong(List<String> songName){
        List<Song> songs = musicListDAO.loadAllMusic();
        List<Song> newSong = new LinkedList<>();

        if (songs.size() != 0 && songName.size() != 0) {
            for (String name: songName) {
                for (Song song : songs) {
                    if (name.equals(song.getName())) {
                        newSong.add(song);
                    }
                }
            }
        } else {
            return null;
        }

        return newSong;
    }

    public boolean findPlaylistName(String playlistName, int idUser){

        if(findPlaylist(playlistName, idUser) != null){
            return true;
        }
        return false;
    }

    private Playlist findOnePublicPlaylist(String playlistName , int idUser){
        List<Playlist> playlistList = musicListDAO.loadPublicPlaylist(idUser);

        if (playlistList.size() != 0) {
            for (Playlist playlist: playlistList) {
                if (playlistName.equals(playlist.getName())) {
                    return playlist;
                }
            }
        }
        return null;
    }

    public void changePlaylistName(String currentName, String newName){
        musicListDAO.changePlaylistName(currentName, newName);
    }

    public boolean isPublicPlaylist(String playlistName, int idUser) {
       Playlist playlist = findPlaylist(playlistName, idUser);
       if (playlist.getUserId() == idUser) {
           return false;
       } else {
           return true;
       }
    }

    private List<Song> loadAllUserSongs (int idUser) {
        List<Song> userSongs = new LinkedList<>();
        List<Song> allSongs = loadAllMusic();
            for(Song song: allSongs) {
                if(song.getIdOwne() == idUser) {
                    userSongs.add(song);
                }
            }
        return userSongs;
    }

    public String getCurrentPlaylist () {
        //return currentPlaylist.getName();
        return currentPlaylistName;
    }

    public void setCurrentPlaylist (String playlistName, int idUser) {
        //currentPlaylist = findPlaylist(playlistName, idUser);
        currentPlaylistName = playlistName;
    }

    public List<Song> loadMusicOnePlaylist(String playlistName, int ideUser){

        Playlist playlist = findUserPlaylist(playlistName, ideUser);
        System.out.println(playlist.getId());

        return musicListDAO.loadMusicOnePlaylist(playlist.getId());
    }

    public void moveSongInPlaylist(String playlistName, int position, int upDown, int ideUser){
        Playlist playlist = findUserPlaylist(playlistName, ideUser);
        List<Song> songs = musicListDAO.loadMusicOnePlaylist(playlist.getId());
        int idSong1 = songs.get(position).getIdSong();
        int moveSong1 = songs.get(position).getOrden();
        int moveSong2 = songs.get(position - upDown).getOrden();
        int idSong2 = songs.get(position - upDown).getIdSong();

        musicListDAO.moveSongsInPlaylist(playlist.getId(), idSong1, idSong2, moveSong1,moveSong2);

    }

    public void deleteSongAllPlaylist(String songName){
        List<Song> songs = loadAllMusic();
        for (Song song: songs){
            if (songName.equals(song.getName())){
                musicListDAO.deleteSongAllPlaylist(song.getIdSong());
            }
        }
    }
}
