package business;

import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import presentation.listeners.PlayBarListener;
import presentation.UIController;


/**
 * Class capable of managing information between controller and business classes
 */
public class BusinessFacadelmpl implements BusinessFacade{

    private LoginManager loginManager;
    private MusicListManager musicListManager;
    private MusicManager musicManager;

    public BusinessFacadelmpl (PlayBarListener playBarListener) {
        loginManager = new LoginManager();
        musicListManager = new MusicListManager();
        musicManager = new MusicManager(playBarListener);
    }

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
        if (loginManager.loginRequest(login, password)) {
            startingThread();
            return true;
        }
        return false;

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
        if (!musicManager.getFinishedSong()) {
            musicManager.stopSong();
        }
    }

    @Override
    public void deleteAccountRequest(){

        for (Song song: loadAllMusic()){
            if ( song.getIdOwne() == loginManager.getCurrentUSer().getId()){
                deleteUserAddedSong(song.getName());
            }
        }
        if (!musicManager.getFinishedSong()) {
            musicManager.stopSong();
        }
        loginManager.deleteAccountRequest();

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
    public  void addSongPlaylist(String playlistName, List<String> songNameList){
        musicListManager.addSongPlaylist(playlistName, songNameList, loginManager.getCurrentUSer().getId());
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
    public List<Song> loadMusicPlaylist(String playlistName){
        return musicListManager.loadMusicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
    }

    @Override
    public List<Song> loadSearchMusic(String filterName){
        return musicListManager.loadSearchMusic(filterName);
    }

    @Override
    public void createSong(String name, String artist, String album, String genre, String filePath){

        int[] time = musicManager.songTime(filePath);
        String lyrics = getLyrics(name, artist).replaceAll("'", "");
        Song song = new Song(name, artist, album, genre, filePath, lyrics, time[0], time[1]);
        song.setIdOwne(loginManager.getCurrentUSer().getId());
        song.setOwne(loginManager.getCurrentUSer().getName());
        musicManager.createSong(song);
    }


    @Override
    public List<Genre> loadStadistic(){
        return musicManager.loadStadistc();
    }

    @Override
    public void deleteUserAddedSong(String songName){
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

    public void playMusic(){
        String playlistName = musicListManager.getCurrentPlaylist();
        String currentSongName = musicManager.getSelectedSongName();
        List<Song> songs = musicListManager.loadMusicPlaylist(playlistName, loginManager.getCurrentUSer().getId());
        int i = 0;
        int j = 0;

        for(Song song: songs){
            if(currentSongName.equals(song.getName())){
                j = i;
            }
            i++;
        }

        if (playlistName.equals("AllSongs") || playlistName.equals("MySongs")){
            musicManager.playSong(false, songs, j);

        } else {
            musicManager.playSong(true, songs, j);
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

    public boolean findSongName(String songName) {
        return musicManager.findSongName(songName);
    }

    public boolean findPath(String path) {
        return musicManager.findPath(path);
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
        musicListManager.setCurrentPlaylist(playlistName);
    }



    public void moveSongsInPlaylist(String playlistName, int position, int upDown){
        musicListManager.moveSongInPlaylist(playlistName, position ,upDown, loginManager.getCurrentUSer().getId());
    }


    public void deletePersonalSong(List<String> songNameList){
        for (String song: songNameList){
            deleteUserAddedSong(song);
        }
    }

    public void loop(){
        musicManager.loop();
    }

    public List<Song> loadAllNotAlreadyAddedSong(String playlistName){
        return musicListManager.loadAllNotAlreadyAddedSong(playlistName, loginManager.getCurrentUSer().getId());
    }

    /**
     *  method to get the lyrics of a song through the API
     * @param songName String with the name of the song
     * @param artist Sting with the name of the artist
     * @return String with the lyrics of the song
     */
    private String getLyrics(String songName, String artist){
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        HttpURLConnection conn = null;

        try{
            String lyric;


            URL url = new URL("https://api.lyrics.ovh/v1/" + artist + "/" + songName);

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int status = conn.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();

                String myjson = responseContent.toString();
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(myjson);
                JSONObject configjson = (JSONObject) obj;

                lyric = (String) configjson.get("lyrics");
                return lyric;
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return "This song does not have lyrics";
    }

    public String getSelectedSongName() {
        return musicManager.getSelectedSongName();
    }

    public void setSelectedSongName(String selectedSongName) {
        musicManager.setSelectedSongName(selectedSongName);
    }

    public boolean songExistsInPlaylist (String playlistName, String songName) {
        return musicListManager.songExistsInPlaylist(playlistName, songName, loginManager.getCurrentUSer().getId());
    }

    public void startingThread(){

        Song initialSong = loadAllMusic().get(0);
        setCurrentPlaylist("AllSongs");
        setSelectedSongName(initialSong.getName());

        playMusic();
    }


    public void playlistLoop(){
        musicManager.playlistLoop();
    }

    public Song getCurrentSong() {
        return musicManager.getCurrentSong();
    }

}
