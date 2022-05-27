package presentation.views;

import presentation.controllers.HomescreenViewController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Class representing the HomeScreenView. This class contains all the methods and attributes
 * needed to use and show the HomeScreen View
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class HomeScreenView {
    private final JPanel mainViewCenter;
    private final CardLayout cardManager;

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    JPanel panel;
    JPanel centralPanel;

    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_ACCOUNTMANAGER = "BTN_ACCOUNTMANAGER";
    public static final String BTN_CREATEPLAYLIST = "BTN_CREATEPLAYLIST";

    private JButton stadisticsBtn;
    private JButton accManBtn;
    private JButton jbNewPlaylist  = createButton("New Playlist");
    private ActionListener actionListener;


    /**
     * Constructor to create the HomeScreenView
     * Creates the HomeScreenView linking it to the UIController. This function
     * initializes the HomeScreenView.
     *
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public HomeScreenView (JPanel mainViewCenter, CardLayout cardManager){
        this.mainViewCenter = mainViewCenter;
        this.cardManager = cardManager;
        configureView();
        //topContainer.pack();
    }

    /**
     * configureView is the main method of this class , creating the view and
     * adding the view to the JPanel main view Center in order to be displayed.
     *
     */
    private void configureView() {

        //Creation of main panels
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        centralPanel = centralPanelConfiguration(new LinkedList<>(), new LinkedList<>());
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(centralPanel,BorderLayout.CENTER);

        mainViewCenter.add(panel, "homescreenCard");
    }

    /**
     * centralPanelConfiguration configures the panel located at the center of the main panel
     * of this view.
     *
     * @param usersPlaylists In order to configure this dynamic view this function receives some of
     *                       the information to show each time is called. These are the playlist from the users.
     *
     * @param publicPlaylists In order to configure this dynamic view this function receives some of
     *                        the information to show each time is called. These are the playlist that are public
     *
     * @return JPanel with the elements that will be in the center of this view added
     */
    private JPanel centralPanelConfiguration(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {

        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);

        JPanel centralPanel = new JPanel();
        BoxLayout centralLayout = new BoxLayout(centralPanel,BoxLayout.Y_AXIS);
        centralPanel.setLayout(centralLayout);
        centralPanel.setBackground(negre);
        centralPanel.setBorder(new EmptyBorder(new Insets(25,50,40,80)));


        //Panel "Your Playlist" + New Playlist Button
        JPanel urPlaylistP = new JPanel();
        BoxLayout urPlaylistLayout = new BoxLayout(urPlaylistP,BoxLayout.X_AXIS);
        urPlaylistP.setLayout(urPlaylistLayout);
        urPlaylistP.setBackground(negre);


        JLabel urPlaylistLab = new JLabel("Your Playlist");
        urPlaylistLab.setFont(titols);
        urPlaylistLab.setForeground(Color.white);


        jbNewPlaylist.setActionCommand(BTN_CREATEPLAYLIST);

        urPlaylistP.add(urPlaylistLab);
        urPlaylistP.add(Box.createRigidArea(new Dimension(680,0)));
        urPlaylistP.add(jbNewPlaylist);

        //Panel ScrollPane Your Playlists
        usersPlaylists.addFirst("MySongs");
        JPanel urPlaylistScrollP = new JPanel();
        urPlaylistScrollP.setBackground(negre);
        urPlaylistScrollP.add(createScrollPane(usersPlaylists)); //Todo


        //Panel Explore
        JPanel exploreP = new JPanel();
        JLabel exploreLab = new JLabel("Explore");
        exploreLab.setFont(titols);
        exploreLab.setForeground(Color.white);

        exploreP.add(exploreLab);
        exploreP.add(Box.createRigidArea(new Dimension(900,0)));
        exploreP.setBackground(negre);

        //Panel ScrollPane Explore
        publicPlaylists.addFirst("AllSongs");
        JPanel exploreScrollP = new JPanel();
        exploreScrollP.setBackground(negre);
        exploreScrollP.add(createScrollPane(publicPlaylists)); //todo

        centralPanel.add(urPlaylistP);
        centralPanel.add(urPlaylistScrollP);
        centralPanel.add(exploreP);
        centralPanel.add(exploreScrollP);

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
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 65);

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(negre);
        northernPanel.setBorder(new EmptyBorder(new Insets(50, 250, 0, 50)));

        JLabel homeLabel = new JLabel("Home");
        homeLabel.setFont(titols);
        homeLabel.setForeground(Color.white);

        northernPanel.add(homeLabel);
        return northernPanel;
    }

    /**
     * westernPanelConfiguration configures the panel located at the west of the main panel
     * of this view.
     *
     * @return JPanel with the elements that will be in the west of this view added
     */
    private JPanel westernPanelConfiguration() {
        //Fonts, colours and sizes
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);

        JPanel westernPanel = new JPanel();
        BoxLayout westernLayout = new BoxLayout(westernPanel,BoxLayout.Y_AXIS);
        westernPanel.setLayout(westernLayout);
        westernPanel.setBorder(new EmptyBorder(new Insets(50, 30, 40, 30)));
        westernPanel.setMinimumSize(new Dimension(200,900));
        westernPanel.setBackground(negre);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(negre);

        GridBagConstraints constraints = new GridBagConstraints();

        stadisticsBtn = createButton("Stadistics");
        stadisticsBtn.setActionCommand(BTN_STATISTICS);

        accManBtn = createButton("Account Manager");
        accManBtn.setActionCommand(BTN_ACCOUNTMANAGER);

        constraints.gridx=0;
        constraints.gridy=0;
        buttonsPanel.add(stadisticsBtn,constraints);

        constraints.gridx=0;
        constraints.gridy=1;
        buttonsPanel.add(Box.createRigidArea(new Dimension(0,400)),constraints);

        constraints.gridx=0;
        constraints.gridy=2;
        buttonsPanel.add(accManBtn,constraints);


        //westernPanel.add(searchPanel);
        westernPanel.add(Box.createRigidArea(new Dimension(0,40)));
        westernPanel.add(buttonsPanel);


        return westernPanel;

    }

    /**
     * createButton is a method that generic creates a JButton
     *
     * @param name the name of the button to create
     * @return A generic JButton with the correct name
     */

    private JButton createButton(String name) {

        Dimension button_shape = new Dimension(200,40);
        Font text = new Font("Gulim", Font.PLAIN, 19);

        JButton btn = new JButton(name);

        btn.setPreferredSize(button_shape);
        btn.setBackground(vermell);
        btn.setForeground(Color.white);
        btn.setFocusable(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFont(text);

        return btn;
    }

    /**
     * getScaledImage is a method that receives an image with a certain length and height
     * and resizes the image to mach the length and height.
     * @param img Image to be resized
     * @param wt Width of the image to resize
     * @param ht Height of the image to resize
     * @return The image introduced in this method but resized to mach the Width and Height
     */
    private Image getScaledImage(Image img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    /**
     * createScrollPane receives a LinkedList with the name of the playlist and creates a specific scroll panel
     * with the correct size to fit all playlist.
     *
     * @param playListNames Linked list with the name of all playlist to be shown on this view.
     *
     * @return the specific ScrollPanel in which each playlist fits
     */
    private Component createScrollPane(LinkedList<String> playListNames){

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,getIdealHeight(playListNames.size()))); // calcular la height
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,40,5));
        panel.setBackground(negre);

        for(int i=0; i < playListNames.size(); i++){

            String playListName = playListNames.get(i);

            //Panel which contains button and label
            JPanel miniPanel = new JPanel();
            BoxLayout miniPanelLayout = new BoxLayout(miniPanel,BoxLayout.Y_AXIS);
            miniPanel.setLayout(miniPanelLayout);

            //Label with the playlist name
            JLabel playlistTitle = new JLabel(playListName);
            playlistTitle.setForeground(Color.white);
            playlistTitle.setAlignmentX(Component.CENTER_ALIGNMENT);


            ImageIcon defaultCover = new ImageIcon("images/albumCoverDefault.png");
            Image defaultCoverIcon = defaultCover.getImage();
            Image defaultCoverIconScaled = getScaledImage(defaultCoverIcon, 160, 160);
            ImageIcon albumCoverDefault = new ImageIcon(defaultCoverIconScaled);

            //Button config
            JButton jbPlaylist = new JButton();
            jbPlaylist.setPreferredSize(new Dimension(160,160));
            jbPlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);
            jbPlaylist.setIcon(albumCoverDefault);
            jbPlaylist.setActionCommand(playListName);
            jbPlaylist.addActionListener(actionListener);

            //Adding components to panel
            miniPanel.add(jbPlaylist);
            miniPanel.add(Box.createRigidArea(new Dimension(0,5)));
            miniPanel.add(playlistTitle);
            miniPanel.setBackground(negre);

            panel.add(miniPanel);
            //buttons.get(i).setActionCommand(); todo
        }

        JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(panel);
        scrollPane.setPreferredSize(new Dimension(1060,280));
        scrollPane.setBackground(negre);

        return scrollPane;

    }

    /**
     * getIdealHeight receives an integer with the size of the linked list of playlist
     * to show and returns the height the JPanel should have in order to fit all of them.
     *
     * @param size size of the linked list of playlist to show
     *
     * @return the specific size that the JPanel should have.
     */
    private int getIdealHeight(int size) {
        double residu = size%5;


        if (residu == 1) {
            size += 4;
        }
        else if (residu == 2) {
            size += 3;

        }
        else if (residu == 3) {
            size += 2;

        }
        else if (residu == 4) {
            size += 1;
        }
        return size*38;
    }

    /**
     * registerController as it's name implies registers the three controllers
     * of the AddSongView in order to be accessed from the homescreenViewController class
     * @param homescreenViewController Is the parameter that will receive this class in order to
     *                              link the listeners of this view with the Controller
     */
    public void registerController(HomescreenViewController homescreenViewController) {
        stadisticsBtn.addActionListener(homescreenViewController);
        accManBtn.addActionListener(homescreenViewController);
        jbNewPlaylist.addActionListener(homescreenViewController);
        actionListener = homescreenViewController;
    }

    /**
     * showCard  callas a method to set the error visibility no false
     * and introduces the current state of the mainViewCenter Frame in the cardManager
     * and shows to the user the screen.
     *
     * @param usersPlaylists These are the playlist from the users.
     * @param publicPlaylists These are the playlist that are public.
     *
     */
    public void showCard(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {
        panel.remove(centralPanel);
        centralPanel = centralPanelConfiguration(usersPlaylists, publicPlaylists);
        panel.add(centralPanel,BorderLayout.CENTER);
        mainViewCenter.revalidate();
        cardManager.show(mainViewCenter,"homescreenCard");
    }
}
