package presentation;

import business.BusinessFacade;
import business.BusinessFacadelmpl;
import business.entities.Genre;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import presentation.Components.RoundButton;
import presentation.Components.RoundPanel;
import presentation.Components.RowIcon;
import presentation.controllers.*;
import presentation.views.HomeScreenView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;
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
    private StadisticViewController stadisticViewController;
    private AddToPlaylistViewController addToPlaylistViewController;

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
        stadisticViewController = new StadisticViewController(this, topContainer,cardManager);
        addToPlaylistViewController = new AddToPlaylistViewController(this,topContainer,cardManager);




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
        //showStadisticsCard();
        //showAddToPLaylistCard();

        //Les seguents linies son simplement una prova per a fer el "popup" que utilitzarem per a la barra de reproduccio
        JPanel barRepro =barReproConfiguration();
        JWindow jWindow = new JWindow(topContainer);
        jWindow.add(barRepro);
        jWindow.setVisible(true);
        jWindow.setAlwaysOnTop(true);
        jWindow.setSize(400,80);
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

    private void showAddToPLaylistCard() {
        addToPlaylistViewController.showAddToPlaylistView();
    }

    private JPanel barReproConfiguration() {
        JPanel barRepro = new RoundPanel();
        barRepro.setLayout(new BorderLayout());
        barRepro.setBorder(new EmptyBorder(new Insets(10,10,10,10)));

        //Western Panel config
        JPanel westernPanel = new JPanel();
        westernPanel.setLayout(new BoxLayout(westernPanel,BoxLayout.Y_AXIS));
        westernPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        westernPanel.setBackground(Color.red);
        String song = "Zorra";
        String artist = "BadGyal";

        JLabel songName = new JLabel(song);
        JLabel artistName = new JLabel(artist);

        westernPanel.add(songName);
        westernPanel.add(artistName);

        //CentralPanel config
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel,BoxLayout.Y_AXIS));

        //Main buttons Panel config
        JPanel mainBtns = new JPanel();

        for(int i=0; i < 3; i++){
            JButton button = new RoundButton("Play");
            button.setBackground(Color.red);
            button.setPreferredSize(new Dimension(25,25));
            mainBtns.add(button);
        }

        //Bar reproduction Panel config
        JPanel barPanel = new JPanel();
        barPanel.setLayout(new BoxLayout(barPanel,BoxLayout.X_AXIS));

        String actualTime = "2:22";
        String endTime = "4:45";

        JLabel actualTimeLabel = new JLabel(actualTime);
        JLabel endTimeLabel = new JLabel(endTime);
        /*Icon barIcon = new RowIcon(160,10,Color.red);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(barIcon);*/
        JProgressBar bar = new JProgressBar(SwingConstants.HORIZONTAL);
        bar.setBackground(Color.lightGray);


        barPanel.add(actualTimeLabel);
        barPanel.add(bar);
        barPanel.add(endTimeLabel);

        centralPanel.add(mainBtns);
        centralPanel.add(barPanel);

        //Eastern Panel config
        JPanel easternPanel = new JPanel();
        easternPanel.setLayout(new BoxLayout(easternPanel,BoxLayout.X_AXIS));

        JButton repetedButton = new JButton("1");
        repetedButton.setOpaque(false);
        repetedButton.setPreferredSize(new Dimension(30,30));

        JButton repetedButton2 = new JButton("2");
        repetedButton2.setOpaque(false);
        repetedButton2.setPreferredSize(new Dimension(30,30));

        easternPanel.add(repetedButton);
        easternPanel.add(repetedButton2);

        barRepro.add(easternPanel,BorderLayout.EAST);
        barRepro.add(westernPanel,BorderLayout.WEST);
        barRepro.add(centralPanel,BorderLayout.CENTER);


        return barRepro;
    }

    private void showStadisticsCard() {
        stadisticViewController.showStadisticsView();
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
