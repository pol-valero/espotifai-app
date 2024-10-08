package presentation;

import business.BusinessFacade;
import business.BusinessFacadelmpl;
import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import presentation.Components.ReproductionBar;
import presentation.controllers.*;
import presentation.listeners.PlayBarListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que gestiona el funcionamiento del programa
 */
public class UIController implements PlayBarListener {

    private MainViewController mainViewController;
    private LoginViewController loginViewController;
    private LogoutViewController logoutViewController;
    private SignupViewController signupViewController;
    private HomescreenViewController homescreenViewController;
    private BusinessFacade businessFacade;
    private MusicListController musicListController;
    private AddSongViewController addSongViewController;
    private CreatePlaylistViewController createPlaylistViewController;
    private RemovePlaylistController removePlaylistController;
    private RenamePlaylistViewController renamePlaylistViewController;
    private SongDetailsViewController songDetailsViewController;
    private AddToPlaylistViewController addToPlaylistViewController;
    private StadisticViewController stadisticViewController;
    private PlayBarController playBarController;
    private RemoveSelectedSongsController removeSelectedSongsController;

    public void run () {

        JFrame topContainer = new JFrame();
        JPanel mainViewCenter = new JPanel();
        CardLayout jFrameCardManager = new CardLayout();
        CardLayout cardManager = new CardLayout();

        ReproductionBar reproductionBar = new ReproductionBar();

        businessFacade = new BusinessFacadelmpl(this);

        loginViewController = new LoginViewController(this, topContainer, jFrameCardManager);
        signupViewController = new SignupViewController(this, topContainer, jFrameCardManager);

        playBarController = new PlayBarController(this, reproductionBar);

        mainViewController = new MainViewController(topContainer, jFrameCardManager, cardManager, mainViewCenter, reproductionBar);

        homescreenViewController = new HomescreenViewController(this,mainViewCenter,cardManager);
        logoutViewController = new LogoutViewController(this, mainViewCenter, cardManager);
        musicListController = new MusicListController(this, mainViewCenter, cardManager);
        addSongViewController = new AddSongViewController(this, mainViewCenter, cardManager);
        createPlaylistViewController = new CreatePlaylistViewController(this, mainViewCenter, cardManager);
        removePlaylistController = new RemovePlaylistController(this, mainViewCenter, cardManager);
        renamePlaylistViewController = new RenamePlaylistViewController(this, mainViewCenter, cardManager);
        songDetailsViewController = new SongDetailsViewController(this, mainViewCenter, cardManager);
        addToPlaylistViewController = new AddToPlaylistViewController(this, mainViewCenter, cardManager);
        stadisticViewController = new StadisticViewController(this, mainViewCenter, cardManager);
        removeSelectedSongsController = new RemoveSelectedSongsController(this, mainViewCenter, cardManager);

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
        LinkedList<String> userPlaylist = businessFacade.loadUserPlaylist();
        addToPlaylistViewController.showAddToPlaylistView(userPlaylist);
    }

    public void showStadisticsCard() {
        stadisticViewController.showStadisticsView((LinkedList<Genre>) businessFacade.loadStadistic());
    }

    public void showRemoveSelectedSongsCard(LinkedList<String> songsToRemove) {
        removeSelectedSongsController.showRemoveSelectedSongsCard(songsToRemove);
    }

    public boolean findUserNameMatch(String username){

        return businessFacade.findUsernameMach(username);
    }

    public boolean findEmailMatch(String email){

        return businessFacade.findEmailMach(email);
    }

    public boolean checkPasswordFormat(String password){

        return !businessFacade.checkPassword(password);
    }

    public boolean checkEmailFormat (String email){

        return businessFacade.checkEmail(email);
    }

    public boolean checkEqualPassword (String password, String rewritedPassword){

        return !businessFacade.sameString(password, rewritedPassword);
    }

    public boolean loginRequest(String login, String password) {
        if (businessFacade.loginRequest(login, password)) {
            playBarController.initialSongConfiguration();
            return true;
        }
        return false;
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


    public List<Song> loadSearchMusic(String filterName){
        return businessFacade.loadSearchMusic(filterName);
    }

    public void createSong(String name, String artist, String album, String genre, String filePath){
        businessFacade.createSong(name, artist, album, genre, filePath);
    }

    public Song findSong(String songName){
        return businessFacade.findSong(songName);
    }

    public void addSongPlaylist(String playlistName, List<String> songNameList){
        businessFacade.addSongPlaylist(playlistName, songNameList);
    }

    public void playMusic() {
        String selectedSongName = getSelectedSongName();
        Song songToBeReproduced = findSong(selectedSongName);
        int totalMinutes = songToBeReproduced.getMinutes();
        int totalSeconds = songToBeReproduced.getSeconds();
        playBarController.reproduceNewSong(selectedSongName, totalMinutes, totalSeconds);
        businessFacade.playMusic();
    }

    public void previusNextSong(int next){

        int totalMinutes;
        int totalSeconds;
        Song songToBeReproduced;

        businessFacade.previusNextSong(next);

        songToBeReproduced = getCurrentSong();
        totalSeconds = songToBeReproduced.getSeconds();
        totalMinutes = songToBeReproduced.getMinutes();
        playBarController.reproduceNewSong(songToBeReproduced.getName(), totalMinutes, totalSeconds);
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

    public LinkedList<Song> loadAllNotAlreadyAddedSongs (String playlistName) {
        return (LinkedList<Song>) businessFacade.loadAllNotAlreadyAddedSong(playlistName);
    }

    public String getSelectedSongName() {
        return businessFacade.getSelectedSongName();
    }

    public void setSelectedSongName(String selectedSongName) {
        businessFacade.setSelectedSongName(selectedSongName);
    }

    public boolean songExistsInPlaylist (String playlistName, String songName) {
        return businessFacade.songExistsInPlaylist(playlistName, songName);
    }

    public void moveSongInPlaylist (String playlistName, int songPosition, int upDown) {
        businessFacade.moveSongsInPlaylist(playlistName, songPosition, upDown);
    }

    public void loopSong () {
        businessFacade.loop();
    }

    public void loopPlaylist () {
        businessFacade.playlistLoop();
    }

    public Song getCurrentSong() {
        return businessFacade.getCurrentSong();
    }

    @Override
    public void updateBar(int currentMinutes, int currentSeconds) {
        playBarController.update(currentMinutes, currentSeconds);
    }
}
