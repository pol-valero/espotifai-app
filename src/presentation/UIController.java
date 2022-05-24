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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.LinkedList;

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
    private SetPlaylistNameController setPlaylistNameController;

    public void run () {
        JFrame topContainer = new JFrame();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, cardManager);
        signupViewController = new SignupViewController(this, topContainer, cardManager);
        logoutViewController = new LogoutViewController(this, topContainer, cardManager);
        homescreenViewController = new HomescreenViewController(this,topContainer,cardManager);
        musicListController = new MusicListController(this, topContainer, cardManager);
        addSongViewController = new AddSongViewController(this, topContainer, cardManager);
        setPlaylistNameController = new SetPlaylistNameController(this, topContainer, cardManager);
        //fer el mateix amb tots els altres controllers
        //showMusicListCard();
        //showSignUpCard();
        //showHomescreenCard();
        //showLoginCard();
        //showSignUpCard();
        //showHomescreenCard();
        //showMusicListCard();
        //showAddSongCard();
        //showSetPlaylistNameCard();
        //Les seguents linies son simplement una prova per a fer el "popup" que utilitzarem per a la barra de reproduccio
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("This is a popup");
        jPanel.add(jLabel);
        JWindow jWindow = new JWindow(topContainer);
        jWindow.add(jPanel);
        jWindow.setVisible(true);
        jWindow.setSize(200,200);
        jWindow.setAlwaysOnTop(true);
        jWindow.addMouseMotionListener(new MouseMotionListener() {
            private int mx, my;

            @Override
            public void mouseMoved(MouseEvent e) {
                mx = e.getXOnScreen();
                my = e.getYOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = jWindow.getLocation();
                p.x += e.getXOnScreen() - mx;
                p.y += e.getYOnScreen() - my;
                mx = e.getXOnScreen();
                my = e.getYOnScreen();
                jWindow.setLocation(p);
            }
        });
    }

    public void showMusicListCard() {
        //todo cal fer un mètode per obtenir una string (songname)
        musicListController.showMusicListCard(new Playlist(122,"Chill out testing",2323,"You"));
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
        setPlaylistNameController.showSetPlaylistNameCard();
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



    public void deleteAccountRequest() {
    }

    public LinkedList<Song> loadPlaylistMusic(String playlistName) {
        return (LinkedList<Song>) businessFacade.loadMusicPlaylist(playlistName);
    }


}
