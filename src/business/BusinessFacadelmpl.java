package business;

import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Clase para gestionar la informacion entre el controller y las clases de Business
 */
public class BusinessFacadelmpl implements BusinessFacade{

    private LoginManager loginManager = new LoginManager();
    private MusicListManager musicListManager = new MusicListManager();

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

    public boolean loginRequest(String login, String password){
        return loginManager.loginRequest(login, password);
    }

    public boolean findUsernameMach(String name){
        return loginManager.findUsernameMach(name);
    }

    public boolean findEmailMach(String email){
        return loginManager.findEmailMach(email);
    }

    public void singUpRequest(User user){
        loginManager.singUpRequest(user);
    }

    public void logoutRequest(){
        loginManager.logoutRequest();
    }

    public void deleteAccountRequest(){
        loginManager.deleteAccountRequest();
    }

    public User getCurrentUser(){
        return loginManager.getCurrentUSer();
    }

    public List<Playlist> loadPublicPlaylist(){
       return musicListManager.loadPublicPlaylist(loginManager.getCurrentUSer().getId());
    }

    public List<Playlist> loadUserPlaylist(){
        return musicListManager.loadUserPlaylist(loginManager.getCurrentUSer().getId());
    }
    public  void addSongPlaylist(String playlistName, Song song, int position){
        musicListManager.addSongPlaylist(playlistName, song, position, loginManager.getCurrentUSer().getId());
    }

    public List<Song> loadAllMusic(){
        return musicListManager.loadAllMusic();
    }

}
