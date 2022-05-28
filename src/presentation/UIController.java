package presentation;

import business.BusinessFacade;
import business.BusinessFacadelmpl;
import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import presentation.Components.ReproductionBar;
import presentation.controllers.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que gestiona el funcionamiento del programa
 */
public class UIController {

    private MainViewController mainViewController;
    private LoginViewController loginViewController;
    private LogoutViewController logoutViewController;
    private SignupViewController signupViewController;
    private HomescreenViewController homescreenViewController;
    private BusinessFacade businessFacade = new BusinessFacadelmpl();
    private MusicListController musicListController;
    private AddSongViewController addSongViewController;
    private CreatePlaylistViewController createPlaylistViewController;
    private RemovePlaylistController removePlaylistController;
    private RenamePlaylistViewController renamePlaylistViewController;
    private SongDetailsViewController songDetailsViewController;
    private AddToPlaylistViewController addToPlaylistViewController;

    public void run () {
        JFrame topContainer = new JFrame();
        JPanel mainViewCenter = new JPanel();
        CardLayout jFrameCardManager = new CardLayout();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, jFrameCardManager);
        signupViewController = new SignupViewController(this, topContainer, jFrameCardManager);

        mainViewController = new MainViewController(topContainer, jFrameCardManager, cardManager, mainViewCenter, new ReproductionBar());

        homescreenViewController = new HomescreenViewController(this,mainViewCenter,cardManager);
        logoutViewController = new LogoutViewController(this, mainViewCenter, cardManager);
        musicListController = new MusicListController(this, mainViewCenter, cardManager);
        addSongViewController = new AddSongViewController(this, mainViewCenter, cardManager);
        createPlaylistViewController = new CreatePlaylistViewController(this, mainViewCenter, cardManager);
        removePlaylistController = new RemovePlaylistController(this, mainViewCenter, cardManager);
        renamePlaylistViewController = new RenamePlaylistViewController(this, mainViewCenter, cardManager);
        songDetailsViewController = new SongDetailsViewController(this, mainViewCenter, cardManager);
        addToPlaylistViewController = new AddToPlaylistViewController(this, mainViewCenter, cardManager);
        //fer el mateix amb tots els altres controllers
        //showMusicListCard();
        //showSignUpCard();
        //showHomescreenCard();
        //showLoginCard();
        //showSignUpCard();
        showHomescreenCard();
        //showMusicListCard();
        //showAddSongCard();

        //showSongDetailsCard(findSong("CançoV2"));
        showAddToPlaylistCard();
        /*LinkedList<String> prueva = new LinkedList<>();
        prueva.add("prettySong");
        addSongPlaylist("provanova", prueva);*/


    }

    private void showMainViewCard() {
        mainViewController.showMainViewCard();
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
        showMainViewCard();
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

    public void showCreatePlaylistCard() {
        createPlaylistViewController.showCreatePlaylistCard();
    }

    public void showRenamePlaylistCard () {
        renamePlaylistViewController.showRenamePlaylistCard();
    }

    public void showSongDetailsCard (Song song) {
        songDetailsViewController.showSongDetailsCard(song);
    }

    public void showAddToPlaylistCard() {
        addToPlaylistViewController.showAddToPlaylistView();
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

    public void createSong(String name, String artist, String album, String genre, String filePath){
        businessFacade.createSong(name, artist, album, genre, filePath);
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

    public void addSongPlaylist(String playlistName, List<String> songNameList){
        businessFacade.addSongPlaylist(playlistName, songNameList);
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

    public boolean findSongName(String songName) {
        return businessFacade.findSongName(songName);
    }

    public boolean findPath(String path) {
        return businessFacade.findPath(path);
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

    public void deletePersonalSong(List<String> songNameList){
        businessFacade.deletePersonalSong(songNameList);
    }

    public List<Song> loadMusicOnePlaylist(String playlistName){
        return businessFacade.loadMusicOnePlaylist(playlistName);
    }

}
