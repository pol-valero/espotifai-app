package presentation.views;

import presentation.controllers.RemovePlaylistController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class representing the RemovePlaylistView. This class contains all the methods and attributes
 * needed to use and show the  RemovePlaylist View
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class RemovePlaylistView {

    public static final String BTN_MANAGEMENT = "BTN_MANAGEMENT";
    public static final String BTN_REMOVE = "BTN_REMOVE";
    public static final String  BTN_CANCEL = "BTN_CANCEL";


    private final JPanel mainViewCenter;
    private final CardLayout cardManager;
    private JButton jbManagement = new JButton();
    private JButton jbRemovePlaylist = new JButton();
    private JButton jbCancel = new JButton();

    private JLabel playListNameLabel;

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    JPanel panel;
    JPanel centralPanel;

    /**
     * Constructor to create the RemovePlaylistView
     * Creates the RemovePlaylistView linking it to the UIController. This function
     * initializes the RemovePlaylistView.
     *
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public RemovePlaylistView (JPanel mainViewCenter, CardLayout cardManager){
        this.mainViewCenter = mainViewCenter;
        this.cardManager = cardManager;
        configureView();
        //this.mainViewCenter.pack();
    }

    /**
     * configureView is the main method of this class , creating the view and
     * adding the view to the JPanel main view Center in order to be displayed.
     *
     */
    private void configureView() {

        //Colors, fonts and sizes
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel southernPanel = southernPanelConfiguration();

        centralPanel = centralPanelConfiguration("Temporal", 0);
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(southernPanel,BorderLayout.SOUTH);
        panel.add(centralPanel,BorderLayout.CENTER);

        mainViewCenter.add(panel, "removePlaylistCard");
    }

    /**
     * centralPanelConfiguration configures the panel located at the center of the main panel
     * of this view.
     *
     * @param playlistName the name of the playlist that can be deleted
     *
     * @param songNumber The number of songs of the playlist
     *
     * @return JPanel with the elements that will be in the center of this view added
     */
    private JPanel centralPanelConfiguration(String playlistName, int songNumber) {


        Font subtitle = new Font("Trebuchet MS", Font.PLAIN, 30);
        Font titol = new Font("Trebuchet MS", Font.PLAIN, 38);
        Font button = new Font("Trebuchet MS", Font.PLAIN, 20);
        Font text = new Font("Gulim", Font.PLAIN, 14);
        Font information = new Font("Gulim", Font.PLAIN, 14);

        Dimension button_shape = new Dimension(245,40);
        Dimension CentralPanel = new Dimension(500,500);
        //   Dimension button_shape_2 = new Dimension(245,40);


        // Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(negre);

        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setBackground(negre);
        centralPanel.setMaximumSize(CentralPanel);


        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints d = new GridBagConstraints();


        c.insets = new Insets(10,30,10,30); //Space between components
        d.insets = new Insets(0,30,0,30); //Space between components


        JPanel info_panel = new JPanel();
        info_panel.setMaximumSize(CentralPanel);


        // tittle

        JPanel  title_panel = new JPanel();
        title_panel.setLayout(new BoxLayout( title_panel, BoxLayout.Y_AXIS));
        title_panel.setBackground(negre);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Remove playlist");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titol);

        JLabel explanation = new JLabel();
        explanation.setText("<html>You are about to remove the following playlist. Are you sure?</html>" );
        explanation.setForeground(Color.white);
        explanation.setFont(information);

        title_panel.add(titleLabel);
        //title_panel.add(Box.createRigidArea(new Dimension(50, 15)));

        title_panel.add(explanation);
        //  subtitle_panel.add(Box.createRigidArea(new Dimension(50, 15)));

        info_panel.add(title_panel);
        info_panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Playlist label

        JPanel label_panel = new JPanel();
        label_panel.setLayout(new BoxLayout(label_panel, BoxLayout.LINE_AXIS));
        label_panel.setBackground(negre);

        playListNameLabel = new JLabel();
        playListNameLabel.setText(playlistName);
        playListNameLabel.setForeground(Color.white);
        playListNameLabel.setFont(subtitle);

        label_panel.add(playListNameLabel);
        label_panel.add(Box.createRigidArea(new Dimension(50, 0)));

        JLabel numberSongsLabel = new JLabel("Number of songs: ");
        numberSongsLabel.setForeground(Color.white);
        numberSongsLabel.setFont(subtitle);
        label_panel.add(numberSongsLabel);

        JLabel songNumberLabel = new JLabel();
        songNumberLabel.setText(String.valueOf(songNumber));
        songNumberLabel.setForeground(Color.white);
        songNumberLabel.setFont(subtitle);
        label_panel.add(songNumberLabel);
        info_panel.add(label_panel);
        info_panel.add(Box.createRigidArea(new Dimension(0, 10)));

        //Remove and cancel playlist button

        JPanel button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.LINE_AXIS));
        button_panel.setBackground(negre);

        jbRemovePlaylist.setBackground(vermell);
        jbRemovePlaylist.setForeground(Color.white);
        jbRemovePlaylist.setText("Remove Playlist ");
        jbRemovePlaylist.setFont(subtitle);
        jbRemovePlaylist.setPreferredSize(button_shape);
        jbRemovePlaylist.setFocusable(false);
        jbRemovePlaylist.setOpaque(true);
        jbRemovePlaylist.setBorderPainted(false);
        jbRemovePlaylist.setActionCommand(BTN_REMOVE);

        button_panel.add( jbRemovePlaylist);
        button_panel.add(Box.createRigidArea(new Dimension(15, 0)));

        jbCancel.setBackground(vermell);
        jbCancel.setForeground(Color.white);
        jbCancel.setText("Cancel ");
        jbCancel.setFont(subtitle);
        jbCancel.setPreferredSize(button_shape);
        jbCancel.setFocusable(false);
        jbCancel.setOpaque(true);
        jbCancel.setBorderPainted(false);
        jbCancel.setActionCommand(BTN_CANCEL);

        button_panel.add(jbCancel);
        info_panel.add(button_panel);

        //Position and addition
        info_panel.setOpaque(false);
        info_panel.setLayout(new GridLayout(4,1));
        info_panel.setBackground(negre);


        //Position and addition

        c.gridx = 0;
        c.gridy = 0;
        centralPanel.add(info_panel,c);

        return centralPanel;
}

    /**
     * northernPanelConfiguration configures the panel located at the north of the main panel
     * of this view.
     *
     * @return JPanel with the elements that will be in the north of this view added
     */
    private JPanel northernPanelConfiguration() {
        //Fonts, colours and sizes

        JPanel BorderAdjustment = new JPanel(new BorderLayout());

        JPanel FillNORTH = new JPanel();
        FillNORTH.setBackground(negre);
        FillNORTH.setSize(20, 10);

        JPanel FillWEST = new JPanel();
        FillWEST.setBackground(negre);
        FillWEST.setSize(10, 20);

        BorderAdjustment.add(FillNORTH, BorderLayout.NORTH);
        BorderAdjustment.add(FillWEST, BorderLayout.WEST);

        JPanel northernPanel = new JPanel(new BorderLayout());

        JPanel FillPanel = new JPanel();
        FillPanel.setBackground(negre);
        northernPanel.add(FillPanel, BorderLayout.CENTER);
        northernPanel.add(BorderAdjustment, BorderLayout.WEST);

        return northernPanel;
    }

    /**
     *  southernPanelConfiguration configures the panel located at the south of the main panel
     * of this view.
     *
     * @return JPanel with the elements that will be in the south of this view added
     */
    private JPanel southernPanelConfiguration() {

        //Fonts, colours and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font button = new Font("Trebuchet MS", Font.PLAIN, 20);
        Dimension button_shape_2 = new Dimension(245,40);


        jbManagement.setBackground(vermell);
        jbManagement.setForeground(Color.white);
        jbManagement.setText("Acount Management");
        jbManagement.setFont(button);
        jbManagement.setPreferredSize(button_shape_2);
        jbManagement.setFocusable(false);
        jbManagement.setOpaque(true);
        jbManagement.setBorderPainted(false);
        jbManagement.setActionCommand(BTN_MANAGEMENT);

        JPanel BorderAdjustment_2 = new JPanel(new BorderLayout());



        JPanel FillSOUTH = new JPanel();
        FillSOUTH.setBackground(negre);


        JPanel FillWEST_2 = new JPanel();
        FillWEST_2.setBackground(negre);


        BorderAdjustment_2.add(jbManagement, BorderLayout.CENTER);
        BorderAdjustment_2.add(FillSOUTH, BorderLayout.SOUTH);
        BorderAdjustment_2.add(FillWEST_2, BorderLayout.WEST);

        JPanel southernPanel = new JPanel(new BorderLayout());

        JPanel FillPanel_2 = new JPanel();
        FillPanel_2.setBackground(negre);
        southernPanel.add(FillPanel_2, BorderLayout.CENTER);
        southernPanel.add(BorderAdjustment_2, BorderLayout.WEST);


        return southernPanel;

    }

    /**
     * registerController as it's name implies registers the three controllers
     * of the AddSongView in order to be accessed from the removePlaylistController class
     * @param removePlaylistController Is the parameter that will receive this class in order to
     *                              link the listeners of this view with the Controller
     */
    public void registerController (RemovePlaylistController removePlaylistController) {
        jbCancel.addActionListener(removePlaylistController);
        jbRemovePlaylist.addActionListener(removePlaylistController);
        jbManagement.addActionListener(removePlaylistController);
    }


    /**
     * showCard  callas a method to
     * introduces the current state of the mainViewCenter Frame in the cardManager
     * and shows to the user the screen.
     *
     * @param playlistName Name of the playlist.
     * @param songNumber Number of songs of the playlist.
     *
     */
    public void showCard(String playlistName, int songNumber) {
        panel.remove(centralPanel);
        centralPanel = centralPanelConfiguration(playlistName, songNumber);
        panel.add(centralPanel,BorderLayout.CENTER);
        mainViewCenter.revalidate();
        cardManager.show(mainViewCenter,"removePlaylistCard");
    }

    public String getPlaylistName() {
        return playListNameLabel.getText();
    }
}
