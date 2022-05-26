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
    private final String NOTPLAYLIST = "AllSongs";

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
       return musicListManager.loadPublicPlaylist(loginManager.getCurrentUSer().getId());
    }

    @Override
    public LinkedList<String> loadUserPlaylist(){
        return musicListManager.loadUserPlaylist(loginManager.getCurrentUSer().getId());
    }

    @Override
    public  void addSongPlaylist(String playlistName, String  songName){

        musicListManager.addSongPlaylist(playlistName, songName, loginManager.getCurrentUSer().getId());
    }

    @Override
    public List<Song> loadAllMusic(){
        return musicListManager.loadAllMusic();
    }

    @Override
    public void deleteSongPlaylist(String playlistName, List<String> songName){

        musicListManager.deleteSongPlaylist(playlistName, songName,  loginManager.getCurrentUSer().getId());
    }

    @Override
    public void deletePlaylist(String playlistName){
        musicListManager.deletePlaylist(playlistName);
    }

    @Override
    public void createPlaylist(String playlistName){
        musicListManager.createPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    @Override
    public Playlist findPlaylist(String playlistName){
        return musicListManager.findPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    @Override
    public List<Song> loadMusicPlaylist(String playlistName){
        return musicListManager.loadMusicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    @Override
    public List<Song> loadSearchMusic(String filterName){
        return musicListManager.loadSearchMusic(filterName);
    }

    @Override
    public void createSong(Song song){
        song.setIdOwne(loginManager.getCurrentUSer().getId());
        song.setOwne(loginManager.getCurrentUSer().getName());
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
            songs = musicListManager.loadMusicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
            musicManager.playSong(true, songs, position);
        }
    }

    public void previusNextSong(int next){
        musicManager.previusNextSong(next);
    }

    public void pausedSong(){
        musicManager.pausedSong();
    }

    public boolean findPlaylistName(String playlistName){
        return musicListManager.findPlaylistName(playlistName, loginManager.getCurrentUSer().getId());
    }

    public void changePlaylistName(String currentName, String newName){
        musicListManager.changePlaylistName(currentName, newName);
    }

    public boolean isPublicPlaylist(String playlistName) {
        return musicListManager.isPublicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    public String getCurrentPlaylist () {
        return musicListManager.getCurrentPlaylist();
    }

    public void setCurrentPlaylist (String playlistName) {
        musicListManager.setCurrentPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    public List<Song> loadMusicOnePlaylist(String playlistName){
        return musicListManager.loadMusicOnePlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    public void moveSongsInPlaylist(String playlistName, int position, int upDown){
        musicListManager.moveSongInPlaylist(playlistName, position ,upDown, loginManager.getCurrentUSer().getId());
    }

    public void deleteSongAllPlaylist(String songName){
        musicListManager.deleteSongAllPlaylist(songName);
    }
}
