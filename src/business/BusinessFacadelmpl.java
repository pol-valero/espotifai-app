package business;

import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase para gestionar la informacion entre el controller y las clases de Business
 */
public class BusinessFacadelmpl implements BusinessFacade{

    private LoginManager loginManager = new LoginManager();
    private MusicListManager musicListManager = new MusicListManager();
    private MusicManager musicManager = new MusicManager();
    private final String NOTPLAYLIST = "All songs";
    private final int loginManagergetCurrentUSergetId = 8;  //loginManager.getCurrentUSer()..getId() //todo CAMBIAR

    @Override
    public boolean checkEmail(String email){
         return loginManager.checkEmail(email);
    }

    @Override
    public boolean sameString(String generalString1, String compareString) {
        return generalString1.equals(compareString);
    }

    @Override
    public boolean checkPassword(String password) {
        return loginManager.checkPassword(password);
    }

    @Override
    public boolean loginRequest(String login, String password){
        return loginManager.loginRequest(login, password);
    }

    @Override
    public boolean findUsernameMach(String name){
        return loginManager.findUsernameMach(name);
    }

    @Override
    public boolean findEmailMach(String email){
        return loginManager.findEmailMach(email);
    }

    @Override
    public void singUpRequest(User user){
        loginManager.singUpRequest(user);
    }

    @Override
    public void logoutRequest(){
        loginManager.logoutRequest();
    }

    @Override
    public void deleteAccountRequest(){
        loginManager.deleteAccountRequest();
    }

    @Override
    public User getCurrentUser(){
        return loginManager.getCurrentUSer();
    }

    @Override
    public LinkedList<String> loadPublicPlaylist(){
       return musicListManager.loadPublicPlaylist(loginManagergetCurrentUSergetId);
    }

    @Override
    public LinkedList<String> loadUserPlaylist(){
        return musicListManager.loadUserPlaylist(loginManagergetCurrentUSergetId);
    }

    @Override
    public  void addSongPlaylist(String playlistName, Song song, int position){
        musicListManager.addSongPlaylist(playlistName, song, position, loginManagergetCurrentUSergetId);
    }

    @Override
    public List<Song> loadAllMusic(){
        return musicListManager.loadAllMusic();
    }

    @Override
    public void deleteSongPlaylist(String playlistName, List<String> songName){

        musicListManager.deleteSongPlaylist(playlistName, songName,  loginManagergetCurrentUSergetId);
    }

    @Override
    public void deletePlaylist(String playlistName){
        musicListManager.deletePlaylist(playlistName, loginManagergetCurrentUSergetId);
    }

    @Override
    public void createPlaylist(String playlistName){
        musicListManager.createPlaylist(playlistName, loginManagergetCurrentUSergetId);
    }

    @Override
    public Playlist findPlaylist(String playlistName){
        return musicListManager.findPlaylist(playlistName, loginManagergetCurrentUSergetId);
    }

    @Override
    public List<Song> loadMusicPlaylist(String playlistName){
        return musicListManager.loadMusicPlaylist(playlistName, loginManagergetCurrentUSergetId);
    }

    @Override
    public List<Song> loadSearchMusic(String filterName){
        return musicListManager.loadSearchMusic(filterName);
    }

    @Override
    public void createSong(Song song){
        song.setIdOwne(loginManagergetCurrentUSergetId);
        musicManager.createSong(song);
    }

    @Override
    public List<Genre> loadStadistic(){
        return musicManager.loadStadistc();
    }

    @Override
    public void deleteUserAddedSong(String songName){ //TODO llamar a las funciones de eliminar cancion de playlist quizas n hace falta
        List<Song> songs = musicListManager.loadAllMusic();
        for (Song song: songs){
            if (songName.equals(song.getName())) {
                musicManager.deleteUserAddedSong(song);
                break;
            }
        }
    }

    @Override
    public Song findSong(String songName){
        return musicManager.loadSongInformation(songName);
    }

    public void playMusic(String playlistName, int position){
        List<Song> songs = new LinkedList<>();

        if (playlistName.equals(NOTPLAYLIST)){
            List<Song> oneSong= musicListManager.loadAllMusic();
            songs.add(oneSong.get(position));
            musicManager.playSong(false, songs, 0);

        } else {
            songs = musicListManager.loadMusicPlaylist(playlistName, loginManagergetCurrentUSergetId);
            musicManager.playSong(true, songs, position);
        }
    }

    public void previusNextSong(int next){
        musicManager.previusNextSong(next);
    }

    public void pausedSong(){
        musicManager.pausedSong();
    }


}
