package presentation;

import business.BusinessFacade;
import business.BusinessFacadelmpl;
import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import presentation.controllers.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que gestiona el funcionamiento del programa
 */
public class UIController {

    private LoginViewController loginViewController;
    private LogoutViewController logoutViewController;
    private SignupViewController signupViewController;
    private HomescreenViewController homescreenViewController;
    private BusinessFacade businessFacade = new BusinessFacadelmpl();
    private MusicListController musicListController;
    private AddSongViewController addSongViewController;
    private CreatePlaylistViewController createPlaylistViewController;
    private RemovePlaylistController removePlaylistController;

    public void run () {
        JFrame topContainer = new JFrame();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, cardManager);
        signupViewController = new SignupViewController(this, topContainer, cardManager);
        logoutViewController = new LogoutViewController(this, topContainer, cardManager);
        homescreenViewController = new HomescreenViewController(this,topContainer,cardManager);
        musicListController = new MusicListController(this, topContainer, cardManager);
        addSongViewController = new AddSongViewController(this, topContainer, cardManager);
        createPlaylistViewController = new CreatePlaylistViewController(this, topContainer, cardManager);
        removePlaylistController = new RemovePlaylistController(this, topContainer, cardManager);

        //fer el mateix amb tots els altres controllers
        //showMusicListCard();
        //showSignUpCard();
        showHomescreenCard();
        //showLoginCard();
        //showSignUpCard();
        //showHomescreenCard();
        //showMusicListCard();
        //showAddSongCard();
    }

    public void showRemovePlaylistCard(String playlistName, int songNumber) {
        removePlaylistController.showRemovePlaylistCard(playlistName, songNumber);
    }

    public void showMusicListCard(LinkedList<Song> songList, String songListName) {
        musicListController.showMusicListCard(songList, songListName);
    }

    public LinkedList<String> loadPublicPlaylists() {
        LinkedList<String> publicPlaylists = new LinkedList<String>();

        return publicPlaylists;
    }

    public LinkedList<String> loadUsersPlaylists() {
        LinkedList<String> usersPlaylists = new LinkedList<String>();

        return usersPlaylists;
    }

    public void showHomescreenCard() {
        homescreenViewController.showHomescreenCard(businessFacade.loadUserPlaylist(),businessFacade.loadPublicPlaylist());
    }

    public void showLoginCard() {
        loginViewController.showLoginCard();
    }

    public void showSignUpCard() {
        signupViewController.showSignupCard();
    }

    public void showLogoutCard () {
        logoutViewController.showLogoutCard();
    }

    public void showAddSongCard () {
        addSongViewController.showAddSongCard();
    }

    public void showSetPlaylistNameCard () {
        createPlaylistViewController.showSetPlaylistNameCard();
    }

    public boolean findUserNameMatch(String username){//todo

        return businessFacade.findUsernameMach(username);
    }

    public boolean findEmailMatch(String email){ //todo

        return businessFacade.findEmailMach(email);
    }

    public boolean checkPasswordFormat(String password){ //todo

        return !businessFacade.checkPassword(password);
    }

    public boolean checkEmailFormat (String email){

        return businessFacade.checkEmail(email);
    }

    public boolean checkEqualPassword (String password, String rewritedPassword){

        return !businessFacade.sameString(password, rewritedPassword);
    }

    public boolean loginRequest(String login, String password) { //todo

        return businessFacade.loginRequest(login, password);
    }

    public void signUpRequest (User user) {
        businessFacade.singUpRequest(user);
    }

    public void logoutRequest(){
        businessFacade.logoutRequest();
    }

    public void deleteAccountRequest() {
        businessFacade.deleteAccountRequest();
    }

    public LinkedList<Song> loadPlaylistMusic(String playlistName) {
        return (LinkedList<Song>) businessFacade.loadMusicPlaylist(playlistName);
    }

    public List<Song> loadAllMusic(){
        return businessFacade.loadAllMusic();
    }

    public void deleteSongPlaylist(String playlistName, List<String> songName){
        businessFacade.deleteSongPlaylist(playlistName, songName);
    }

    public void deletePlaylist(String playlistName){
        businessFacade.deletePlaylist(playlistName);
    }

    public void createPlaylist(String playlistName){
        businessFacade.createPlaylist(playlistName);
    }

    public Playlist findPlaylist(String playlistName){ //Todo examinar
       return businessFacade.findPlaylist(playlistName);
    }

    public List<Song> loadSearchMusic(String filterName){
        return businessFacade.loadSearchMusic(filterName);
    }

    public void createSong(Song song){
        businessFacade.createSong(song);
    }

    public List<Genre> loadStadistic(){
        return businessFacade.loadStadistic();
    }

    public void deleteUserAddedSong(String songName){
        businessFacade.deleteUserAddedSong(songName);
    }

    public Song findSong(String songName){
        return businessFacade.findSong(songName);
    }

    public void addSongPlaylist(String playlistName, Song song, int position){
        businessFacade.addSongPlaylist(playlistName,song,position);
    }

    public void playMusic(String playlistName, int position){
        businessFacade.playMusic(playlistName, position);
    }

    public void previusNextSong(int next){ //todo para la barra de reproduccion alante o atras
        businessFacade.previusNextSong(next);
    }

    public void pausedSong(){
        businessFacade.pausedSong();
    }

    public boolean findPlaylistName(String playlistName){
        return businessFacade.findPlaylistName(playlistName);
    }

    public void changePlaylistName(String currentName, String newName){
        businessFacade.changePlaylistName(currentName, newName);
    }

    public boolean isPublicPlaylist(String playlistName) {
        return businessFacade.isPublicPlaylist(playlistName);
    }

    public String getCurrentPlaylist () {
        return businessFacade.getCurrentPlaylist();
    }

    public void setCurrentPlaylist (String playlistName) {
        businessFacade.setCurrentPlaylist(playlistName);
    }
}
