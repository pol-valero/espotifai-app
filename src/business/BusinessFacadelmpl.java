package business;

import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Class capable of managing information between controller and business classes
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
        startingThread();
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
        startingThread();
    }


    @Override
    public void logoutRequest(){
        musicManager.stopThread();
        loginManager.logoutRequest();

    }

    @Override
    public void deleteAccountRequest(){

        for (Song song: loadAllMusic()){
            if ( song.getIdOwne() == loginManager.getCurrentUSer().getId()){
                deleteUserAddedSong(song.getName());
            }
        }
        loginManager.deleteAccountRequest();
        musicManager.stopThread();
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
    public void createSong(String name, String artist, String album, String genre, String filePath){ //todo añadir los lyrics

        int[] time = musicManager.songTime(filePath);
        String lyrics = getLyrics();
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
        System.out.println(playlistName);
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
            System.out.println("la posicion vale =" + j);
            musicManager.playSong(false, songs, j);

        } else {
            System.out.println("la posicion vale =" + j);
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

    private String getLyrics(){
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        HttpURLConnection conn = null;

        try{
            String lyric = "";

            URL url = new URL("https://api.lyrics.ovh/v1/melendi/saraluna");
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

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
            e.printStackTrace();
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

        //playMusic(NOTPLAYLIST, 0);
        playMusic();
        musicManager.startingThread();
        musicManager.pausedSong();
    }

    public void setCurrentSong (Song song) {
        musicManager.setCurrentSong(song);
    }

}
